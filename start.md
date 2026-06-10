# 社区团购系统 - 启动指南

## 前置条件

- podman 已安装
- Java 17+ 已安装（直接运行 JAR）或可拉取 `maven:3.9-eclipse-temurin-17` 镜像

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

## 二、构建并启动后端

```bash
# 1. 编译打包（使用 podman + Maven 容器，首次需下载依赖，约 3-5 分钟）
#    注意：不用 clean，避免 podman FUSE overlay 残留文件导致删除失败
podman run --rm \
  -v /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro:/workspace \
  -v ruoyi-m2:/root/.m2 \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn install -DskipTests -q

# 2. 启动后端
java -jar /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar \
  --spring.profiles.active=local
```

后端启动成功标志：控制台输出 `项目启动成功！`

---

## 三、启动管理端前端（容器）

```bash
podman run --rm -d \
  --name ruoyi-admin-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5174:5173 \
  -e VITE_BASE_URL=http://host.containers.internal:48080 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && pnpm dev --host 0.0.0.0 --port 5173"
```

---

## 四、启动用户端前端（容器）

```bash
podman run --rm -d \
  --name ruoyi-user-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-user-vue3:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5175:5173 \
  -e VITE_BASE_URL=http://host.containers.internal:48080 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && pnpm dev --host 0.0.0.0 --port 5173"
```

---

## 访问地址

| 服务 | 地址 | 登录凭证 |
|------|------|----------|
| 管理端 | http://localhost:5174 | admin / admin123 |
| 用户端 | http://localhost:5175 | 15601691300 / admin123 |
| 后端 API | http://localhost:48080 | - |
| API 文档 | http://localhost:48080/doc.html | - |

---

## 验证

```bash
# 检查端口
ss -tlnp | grep -E "3306|6379|48080|5174|5175"

# 检查容器
podman ps | grep ruoyi

# 测试后端 API
curl -sS "http://127.0.0.1:48080/app-api/group-buy/activity/list?pageNo=1&pageSize=10" -H "tenant-id: 1"

# 测试登录
curl -sS -X POST "http://127.0.0.1:48080/admin-api/system/auth/login" \
  -H "Content-Type: application/json" -H "tenant-id: 1" \
  -d '{"username":"admin","password":"admin123"}'
```

---

## 停止所有服务

```bash
# 停止前端容器
podman stop ruoyi-admin-frontend ruoyi-user-frontend

# 停止后端
kill $(pgrep -f yudao-server.jar)

# 停止数据库
podman stop ruoyi-mysql ruoyi-redis
```

---

## 重启单个服务

```bash
# 重启后端（代码修改后需重新编译）
kill $(pgrep -f yudao-server.jar)
java -jar /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar \
  --spring.profiles.active=local &

# 重启管理端
podman stop ruoyi-admin-frontend
# 然后重新执行步骤三的命令

# 重启用户端
podman stop ruoyi-user-frontend
# 然后重新执行步骤四的命令
```

---

## 后端修改后重新编译

```bash
# 不使用 clean，避免 podman FUSE 残留文件删除失败
podman run --rm \
  -v /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro:/workspace \
  -v ruoyi-m2:/root/.m2 \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn install -DskipTests -q
```

---

## 完全清理

```bash
# 删除所有容器和数据（慎用）
podman stop -a
podman rm -f $(podman ps -aq)
podman volume rm ruoyi-mysql-data ruoyi-pnpm-store ruoyi-m2
```
