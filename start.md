# 社区团购系统 - 启动指南

> 基于 ruoyi-vue-pro 改造的西北师大社区团购管理系统
> - 管理后台：Vue3 + Element Plus
> - 用户端：uni-app（H5 / 小程序 / App）
> - 后端：Spring Boot + MyBatis-Plus + MySQL 8 + Redis
> - 核心业务：拼团（团长制 + 自提 + 多租户）

## 前置条件

- podman 已安装
- Java 17+ 已安装（直接运行 JAR）或可拉取 `maven:3.9-eclipse-temurin-17` 镜像
- Node.js 20+（如需在宿主机直接跑前端）

---

## 一、启动 MySQL + Redis（容器）

```bash
# MySQL 8（首次运行需要，数据持久化在 volume 中）
podman run -d --name ruoyi-mysql \
  -e MYSQL_DATABASE=ruoyi-vue-pro \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  -v ruoyi-mysql-data:/var/lib/mysql \
  docker.io/library/mysql:8

# Redis 7
podman run -d --name ruoyi-redis \
  -p 6379:6379 \
  docker.io/library/redis:7-alpine
```

> 如果容器已存在但未运行：`podman start ruoyi-mysql ruoyi-redis`

---

## 二、初始化数据库（仅首次）

```bash
# 1. 导入 yudao 原始 schema（必需）
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/ruoyi-vue-pro.sql

# 2. 改造迁移：菜单清理 + 多租户字段 + 测试数据
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-migration.sql

podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-test-data.sql

# 3. 拼团 DDL（3 张新表 + trade_order 扩展 + 菜单/权限）
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-ddl.sql

# 4. 拼团测试数据（6 活动 + 5 团单 + 12 成员，多状态覆盖）
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-test-data-v2.sql

# 5. 菜单清理（删除无用菜单：部门/岗位/短信/文件/审计等）
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-menu-cleanup.sql

# 6. 修复 uniapp H5 启动：DIY 模板字段 + 默认模板数据
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-uniapp-fix.sql

# 7. Quartz 定时任务表（拼团过期任务需要）
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/quartz.sql

# 8. 运行时数据修复：商品配送方式、自提门店、演示账号密码、mock 支付渠道、拼团过期任务
podman exec -i ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  --default-character-set=utf8mb4 < \
  /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/sql/mysql/group-buy-runtime-fix.sql
```

> 所有 SQL 文件都是**幂等**的（多次执行安全）。
> 如果 `promotion_diy_template.property` 字段仍是 `varchar(255)`，需要先执行第 6 步再添加模板内容。

---

## 三、构建并启动后端

```bash
# 1. 编译打包（首次需下载依赖，约 3-5 分钟）
#    注意：不用 clean，避免 podman FUSE overlay 残留文件导致删除失败
#    将宿主机 ~/.m2/repository 挂载进去，可复用已下载依赖并避免网络问题
podman run --rm --network host \
  -v /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro:/workspace \
  -v "$HOME/.m2/repository:/root/.m2/repository" \
  -v "$HOME/.m2/settings-docker.xml:/root/.m2/settings.xml" \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn install -pl yudao-server -am -DskipTests -T 1C

# 2. 启动后端（指定 local profile）
nohup java -jar /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar \
  --spring.profiles.active=local > /tmp/yudao.log 2>&1 &
echo "Backend PID: $!"
```

后端启动成功标志：控制台输出 `项目启动成功！` 或访问 `http://localhost:48080/` 返回 `{"code":401,"msg":"账号未登录"}`

---

## 四、启动管理端前端

### 方式 A：容器方式（推荐）

```bash
podman run -d --name ruoyi-admin-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5174:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && pnpm dev --host 0.0.0.0 --port 5173"
```

### 方式 B：宿主机方式

```bash
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3
npm install -g pnpm
pnpm install
nohup pnpm dev --host 0.0.0.0 --port 5173 > /tmp/admin-frontend.log 2>&1 &
```

---

## 五、启动用户端前端（uni-app H5）

### 方式 A：容器方式（推荐）

```bash
podman run -d --name ruoyi-user-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-mall-uniapp:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5175:3000 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && UNI_INPUT_DIR=/workspace pnpm dev:h5"
```

> `UNI_INPUT_DIR=/workspace` 必须显式传入，否则 uni CLI 默认会去 `cwd/src/` 找 `manifest.json`，导致启动失败。

### 方式 B：宿主机方式

```bash
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-mall-uniapp
npm install -g pnpm
pnpm install
nohup env UNI_INPUT_DIR=$(pwd) pnpm dev:h5 > /tmp/uni-frontend.log 2>&1 &
```

> 用户端基于 uni-app，可编译为 H5 / 微信小程序 / App 多端。当前用 H5 模式。

---

## 六、访问地址

| 服务 | 地址 | 登录凭证 |
|------|------|----------|
| **管理后台** | http://localhost:5174 | admin / admin123（系统管理员）<br>zhangsan01 / admin123（社区A团长）<br>lisi02 / admin123（社区B团长）|
| **用户端 H5** | http://localhost:5175 | 手机号 13800000001 验证码 9999 |
| **后端 API** | http://localhost:48080 | - |
| **API 文档** | http://localhost:48080/doc.html | - |
| **MySQL** | localhost:3306 | root / root |
| **Redis** | localhost:6379 | 无密码 |

### 租户切换

请求头加 `tenant-id: 1/162/163` 切换租户：
- `tenant-id: 1` → 系统管理员（菜单齐全）
- `tenant-id: 162` → 社区 A 团长（zhangsan01）
- `tenant-id: 163` → 社区 B 团长（lisi02）

---

## 七、拼团核心业务测试

### 7.1 管理员视角

1. 访问 http://localhost:5174 用 `admin/admin123` 登录
2. 左侧菜单：**营销中心** → **社区拼团** → 三个子菜单
   - **拼团活动**：增删改查、关闭活动
   - **团单管理**：查看所有团单（多状态过滤）
   - **团长核销**：仅团长角色看到（zhangsan01）

### 7.2 团长视角

1. 用 `zhangsan01/admin123` 登录（tenant-id 162）
2. **社区拼团 / 团长核销**：输入订单号 → 核销
3. **社区拼团 / 团单管理**：查看本社区的团单

### 7.3 用户视角

1. 访问 http://localhost:5175 用手机号 13800000001 + 验证码 9999 登录
2. **社区拼团** → 选活动 → 发起拼团
3. 我的拼团 → 看到已开团
4. 拼团分享链接给其他用户 → 参团

### 7.4 API 冒烟测试

```bash
# 管理员登录
TA=$(curl -sS -X POST "http://127.0.0.1:48080/admin-api/system/auth/login" \
  -H "Content-Type: application/json" -H "tenant-id: 1" \
  -d '{"username":"admin","password":"admin123"}' | \
  python3 -c "import sys,json;print(json.load(sys.stdin)['data']['accessToken'])")

# 活动列表
curl -sS "http://127.0.0.1:48080/admin-api/group-buy/activity/page?pageNo=1&pageSize=3" \
  -H "tenant-id: 1" -H "Authorization: Bearer $TA"

# 团单列表
curl -sS "http://127.0.0.1:48080/admin-api/group-buy/head/page?pageNo=1&pageSize=3" \
  -H "tenant-id: 1" -H "Authorization: Bearer $TA"

# 用户开团/参团走标准 trade order，不再直接调用 /group-buy/head/open
U1=$(curl -sS -X POST "http://127.0.0.1:48080/app-api/member/auth/sms-login" \
  -H "Content-Type: application/json" -H "tenant-id: 1" \
  -d '{"mobile":"13900000001","code":"9999"}' | \
  python3 -c "import sys,json;print(json.load(sys.stdin)['data']['accessToken'])")

U2=$(curl -sS -X POST "http://127.0.0.1:48080/app-api/member/auth/sms-login" \
  -H "Content-Type: application/json" -H "tenant-id: 1" \
  -d '{"mobile":"13900000002","code":"9999"}' | \
  python3 -c "import sys,json;print(json.load(sys.stdin)['data']['accessToken'])")

# 1. 用户 1 创建开团订单。activity 2 对应 skuId 7，2 人成团
curl -sS -X POST "http://127.0.0.1:48080/app-api/trade/order/create" \
  -H "tenant-id: 1" -H "Authorization: $U1" \
  -H "Content-Type: application/json" \
  -d '{"items":[{"skuId":7,"count":1}],"pointStatus":false,"deliveryType":1,"addressId":1,"groupBuyActivityId":2}'

# 2. 用返回的 payOrderId 钱包支付
curl -sS -X POST "http://127.0.0.1:48080/app-api/pay/order/submit" \
  -H "tenant-id: 1" -H "Authorization: $U1" \
  -H "Content-Type: application/json" \
  -d '{"id":<payOrderId1>,"channelCode":"wallet"}'

# 3. 查询 trade_order.group_buy_head_id，作为用户 2 参团 groupBuyHeadId
podman exec ruoyi-mysql mysql -u root -proot ruoyi-vue-pro \
  -e "SELECT id,pay_status+0,status,group_buy_head_id FROM trade_order ORDER BY id DESC LIMIT 3;"

# 4. 用户 2 创建参团订单并支付
curl -sS -X POST "http://127.0.0.1:48080/app-api/trade/order/create" \
  -H "tenant-id: 1" -H "Authorization: $U2" \
  -H "Content-Type: application/json" \
  -d '{"items":[{"skuId":7,"count":1}],"pointStatus":false,"deliveryType":1,"addressId":2,"groupBuyActivityId":2,"groupBuyHeadId":<headId>}'

curl -sS -X POST "http://127.0.0.1:48080/app-api/pay/order/submit" \
  -H "tenant-id: 1" -H "Authorization: $U2" \
  -H "Content-Type: application/json" \
  -d '{"id":<payOrderId2>,"channelCode":"wallet"}'
```

---

## 八、一键后台重启全部服务

已提供脚本 `restart-services.sh`，会后台重启后端 + 管理端 + 用户端（MySQL/Redis 保持运行）：

```bash
cd /mnt/data_d/Projects/ruoyi
./restart-services.sh
```

执行后约 20 秒，三个服务即可访问：

- 管理后台：http://localhost:5174
- 用户端 H5：http://localhost:5175
- 后端 API：http://localhost:48080

---

## 九、停止所有服务

```bash
# 停止前端容器
podman stop ruoyi-admin-frontend ruoyi-user-frontend

# 停止后端
kill $(pgrep -f yudao-server.jar)

# 停止数据库
podman stop ruoyi-mysql ruoyi-redis
```

---

## 十、重启单个服务

```bash
# 重启后端（代码修改后需重新编译）
kill $(pgrep -f yudao-server.jar)
java -jar /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar \
  --spring.profiles.active=local &

# 重启管理端
podman restart ruoyi-admin-frontend

# 重启用户端
podman restart ruoyi-user-frontend
```

---

## 十一、后端修改后重新编译

```bash
# 不使用 clean，避免 podman FUSE 残留文件删除失败
podman run --rm \
  -v /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro:/workspace \
  -v ruoyi-m2:/root/.m2 \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn install -pl yudao-module-mall/yudao-module-groupbuy,yudao-server -am -DskipTests -q
```

---

## 十二、关键文件位置

| 模块 | 路径 |
|------|------|
| 后端拼团核心 | `backend/ruoyi-vue-pro/yudao-module-mall/yudao-module-groupbuy/` |
| DDL 迁移 | `backend/ruoyi-vue-pro/sql/mysql/group-buy-ddl.sql` |
| 拼团测试数据 | `backend/ruoyi-vue-pro/sql/mysql/group-buy-test-data-v2.sql` |
| 菜单清理 | `backend/ruoyi-vue-pro/sql/mysql/group-buy-menu-cleanup.sql` |
| 管理端拼团页面 | `frontend/yudao-ui-admin-vue3/src/views/mall/promotion/groupBuy/` |
| 管理端拼团 API | `frontend/yudao-ui-admin-vue3/src/api/mall/promotion/groupbuy/` |
| 用户端拼团页面 | `frontend/yudao-mall-uniapp/pages/groupbuy/` |
| 用户端拼团 API | `frontend/yudao-mall-uniapp/sheep/api/promotion/groupbuy.js` |
| 一键重启脚本 | `restart-services.sh` |
| 改造文档 | `Docs/社区团购系统改造文档.md` |
| 设计文档 | `Docs/软件工程项目设计说明书_陈小庆.pdf` |

---

## 十三、当前服务状态

```bash
# 检查端口
ss -tlnp | grep -E "3306|6379|48080|5174|5175"

# 检查容器
podman ps | grep ruoyi

# 检查后端日志
tail -f /tmp/yudao.log
```

---

## 十四、已知遗留

1. **退款链路**：拼团失败时 `refundFailedMember` 仍未完整接入 yudao pay refund，需要补自动退款和状态回写。
2. **团长核销码**：用 `orderId` 作为核销码（与设计文档的 6 位验证码有差异，可后续优化）。
3. **首页装修**：默认 DIY 模板内容不足，首页/我的页仍需要补运营组件。
4. **定时任务**：需要在容器环境里单独验证 `GroupBuyHeadExpireJob`、`PayNotifyJob` 的调度配置。
5. **砍价模块**：`promotion_bargain_activity` 表缺失（yudao 上游遗留，与拼团无关）。
