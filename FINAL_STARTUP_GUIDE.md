# 社区团购系统 - 完整启动指南

## 系统组件

- **数据库**: MySQL 8 (Port 3306)
- **缓存**: Redis 7 (Port 6379)
- **后端**: Spring Boot 3 (Port 48080)
- **管理端前端**: Vue 3 + Vite (Port 5174)
- **用户端前端**: Vue 3 + Vite (Port 5175)

## 快速启动 (5分钟)

### 前置条件
- 已安装 podman
- MySQL/Redis 容器已创建（参考下方完整启动）

### 启动顺序

#### 1. 启动后端 (Java 17)
```bash
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro

# 后台运行 Java 应用
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local &

# 或者前台运行（用于调试）
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local
```

**等待输出**: 
```
项目启动成功！
接口文档:     https://doc.iocoder.cn/api-doc/
```

#### 2. 启动管理端前端
```bash
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3

podman run --rm -d \
  --name ruoyi-admin-frontend \
  -v "$PWD":/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5174:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173"

# 访问地址: http://127.0.0.1:5174/
```

#### 3. 启动用户端前端
```bash
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-user-vue3

podman run --rm -d \
  --name ruoyi-user-frontend \
  -v "$PWD":/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5175:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173"

# 访问地址: http://127.0.0.1:5175/
```

## 完整启动 (数据库 + 缓存 + 后端 + 前端)

### 1. 启动 MySQL 容器
```bash
podman run -d --name ruoyi-mysql \
  -e MYSQL_DATABASE=ruoyi-vue-pro \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  -v ruoyi-mysql-data:/var/lib/mysql \
  -v "$PWD/sql/mysql/ruoyi-vue-pro.sql":/docker-entrypoint-initdb.d/ruoyi-vue-pro.sql:ro \
  docker.io/library/mysql:8
```

### 2. 启动 Redis 容器
```bash
podman run -d --name ruoyi-redis \
  -p 6379:6379 \
  docker.io/library/redis:7 \
  redis-server --appendonly yes
```

### 3. 构建后端 JAR
```bash
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro

# 编译所有模块并安装到本地 Maven 仓库
podman run --rm \
  -v "$PWD":/workspace \
  -v ruoyi-m2:/root/.m2 \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn clean install -DskipTests -q

# 等待完成后，应用启动成功消息会出现
```

### 4. 启动后端应用
```bash
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local &
```

### 5. 启动两个前端
```bash
# 管理端
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3
podman run --rm -d --name ruoyi-admin-frontend \
  -v "$PWD":/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5174:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173"

# 用户端
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-user-vue3
podman run --rm -d --name ruoyi-user-frontend \
  -v "$PWD":/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5175:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173"
```

## 访问地址

| 服务 | 地址 | 说明 |
|------|------|------|
| 后端 API | http://127.0.0.1:48080 | Spring Boot 应用 |
| 管理端 | http://127.0.0.1:5174 | 平台管理员/商家后台 |
| 用户端 | http://127.0.0.1:5175 | 普通用户前端 |
| API 文档 | http://127.0.0.1:48080/doc.html | Swagger UI |

## 测试登陆账号

### 管理端登陆 (http://127.0.0.1:5174)
- **用户名**: admin
- **密码**: admin123

### 用户端登陆 (http://127.0.0.1:5175)
- **暂无默认账号** - 需要在管理端创建会员账户或通过注册页面注册

## 核心 API 端点

### 团购模块 API

#### 管理员接口
```
GET    /admin-api/promotion/group-buy-activity/page     # 获取团购活动列表
POST   /admin-api/promotion/group-buy-activity/create    # 创建团购活动
GET    /admin-api/promotion/group-buy-activity/{id}      # 获取团购活动详情
PUT    /admin-api/promotion/group-buy-activity/{id}      # 修改团购活动
DELETE /admin-api/promotion/group-buy-activity/{id}      # 删除团购活动

GET    /admin-api/promotion/group-buy-record/page        # 获取团购记录列表
GET    /admin-api/promotion/group-buy-record/{id}        # 获取团购记录详情

GET    /admin-api/promotion/group-buy-record-member/page # 获取团购成员列表
POST   /admin-api/promotion/group-buy-record-member/export # 导出成员数据
```

#### 用户接口
```
GET    /app-api/promotion/group-buy/activity-list        # 获取进行中的活动
GET    /admin-api/promotion/group-buy/my-group-list      # 获取我参与的团
POST   /admin-api/promotion/group-buy/join               # 参与拼团
GET    /admin-api/promotion/group-buy/group-detail/{id}  # 获取团详情
GET    /admin-api/promotion/group-buy/active-groups      # 获取活跃团列表
GET    /admin-api/promotion/group-buy/pickup-location    # 获取自提点
```

## 常见问题排查

### 后端启动失败

1. **端口占用**
   ```bash
   lsof -i :48080
   kill <PID>
   ```

2. **缺少 Maven 缓存**
   ```bash
   cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro
   podman run --rm -v "$PWD":/workspace -v ruoyi-m2:/root/.m2 -w /workspace \
     maven:3.9-eclipse-temurin-17 mvn clean install -DskipTests
   ```

3. **数据库连接失败**
   ```bash
   podman exec ruoyi-mysql mysql -uroot -proot -e "SELECT 1;"
   ```

### 前端访问 404

1. **检查容器状态**
   ```bash
   podman ps | grep ruoyi
   ```

2. **查看容器日志**
   ```bash
   podman logs ruoyi-admin-frontend
   podman logs ruoyi-user-frontend
   ```

3. **重启容器**
   ```bash
   podman stop ruoyi-admin-frontend
   podman run -d ...  # 重新启动
   ```

## 清理环境

### 停止所有服务
```bash
# 停止后端
pkill -f yudao-server.jar

# 停止前端容器
podman stop ruoyi-admin-frontend ruoyi-user-frontend

# 停止数据库和缓存
podman stop ruoyi-mysql ruoyi-redis
```

### 完全清理（删除所有数据）
```bash
# 删除容器
podman rm -f ruoyi-mysql ruoyi-redis ruoyi-admin-frontend ruoyi-user-frontend

# 删除数据卷
podman volume rm ruoyi-mysql-data ruoyi-m2 ruoyi-pnpm-store

# 删除后端 JAR
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro
rm -rf yudao-server/target/yudao-server.jar

# 删除前端 node_modules
rm -rf /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3/node_modules
rm -rf /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-user-vue3/node_modules
```

## 数据库操作

### 连接数据库
```bash
podman exec -it ruoyi-mysql mysql -uroot -proot ruoyi-vue-pro
```

### 查看团购表
```sql
SHOW TABLES LIKE 'group_buy_%';
SHOW TABLES LIKE 'community_%';

-- 查看活动列表
SELECT * FROM group_buy_activity;

-- 查看团购记录
SELECT * FROM group_buy_record;

-- 查看参与成员
SELECT * FROM group_buy_record_member;
```

## 开发工作流

### 后端修改后重建
```bash
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro

# 重新编译
mvn clean package -DskipTests -pl yudao-server

# 停止旧进程并启动新 JAR
pkill -f yudao-server.jar
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local &
```

### 前端修改自动热更新
- 在 podman 中运行的前端支持文件修改自动热重载
- 更改 `.vue` 或 `.ts` 文件后刷新浏览器即可看到效果

## 性能监控

### 查看后端进程
```bash
jps -l | grep YudaoServerApplication
```

### 查看容器资源使用
```bash
podman stats --no-stream
```

### 查看数据库连接
```bash
podman exec ruoyi-mysql mysql -uroot -proot -e "SHOW PROCESSLIST;"
```

## 备份与恢复

### 备份数据库
```bash
podman exec ruoyi-mysql mysqldump -uroot -proot ruoyi-vue-pro > backup.sql
```

### 恢复数据库
```bash
podman exec -i ruoyi-mysql mysql -uroot -proot ruoyi-vue-pro < backup.sql
```

---

**最后更新**: 2026-06-10
**系统状态**: ✅ 完全就绪
**所有服务**: ✅ 运行中
