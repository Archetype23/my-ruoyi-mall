# 🚀 社区团购系统 - 最终启动步骤

> **项目状态**: ✅ 所有组件就绪 | ✅ 生产部署 | ✅ 所有文档完备

---

## 📌 快速启动 (一行命令)

```bash
cd /mnt/data_d/Projects/ruoyi && ./START.sh
```

**完成后访问**:
- 管理端: http://127.0.0.1:5174 (账号: admin / 密码: admin123)
- 用户端: http://127.0.0.1:5175
- 后端 API: http://127.0.0.1:48080/doc.html

---

## 🔧 分步启动 (需要 5 个终端)

### 终端 1: 启动 MySQL

```bash
podman run -d --name ruoyi-mysql \
  -e MYSQL_DATABASE=ruoyi-vue-pro \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  -v ruoyi-mysql-data:/var/lib/mysql \
  docker.io/library/mysql:8
```

### 终端 2: 启动 Redis

```bash
podman run -d --name ruoyi-redis \
  -p 6379:6379 \
  docker.io/library/redis:7 \
  redis-server --appendonly yes
```

### 终端 3: 启动后端

```bash
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local
```

**预期输出**: `项目启动成功！` (约 12 秒后出现)

### 终端 4: 启动管理端前端

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
```

### 终端 5: 启动用户端前端

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
```

---

## ✅ 验证系统运行

```bash
# 检查所有容器
podman ps | grep -E "mysql|redis|admin-frontend|user-frontend"

# 检查后端进程
pgrep -f yudao-server.jar

# 测试后端 API
curl http://127.0.0.1:48080/health

# 测试前端
curl -I http://127.0.0.1:5174/
curl -I http://127.0.0.1:5175/
```

---

## 📍 系统访问地址

| 组件 | 地址 | 说明 |
|------|------|------|
| 后端 API | http://127.0.0.1:48080 | Spring Boot 服务 |
| API 文档 | http://127.0.0.1:48080/doc.html | Swagger UI |
| 管理端 | http://127.0.0.1:5174 | 平台管理员界面 |
| 用户端 | http://127.0.0.1:5175 | 普通用户界面 |

---

## 🔑 默认登陆信息

**管理端** (http://127.0.0.1:5174):
```
用户名: admin
密码:   admin123
```

**用户端** (http://127.0.0.1:5175):
- 暂无默认账号，可通过管理端创建

---

## 🛑 关闭所有服务

```bash
# 方法 1: 停止所有容器
podman stop ruoyi-mysql ruoyi-redis ruoyi-admin-frontend ruoyi-user-frontend

# 方法 2: 停止后端进程
kill $(pgrep -f yudao-server.jar)

# 方法 3: 完全清空 (包括数据)
podman stop -a && podman rm -f $(podman ps -aq)
podman volume rm ruoyi-mysql-data ruoyi-pnpm-store ruoyi-m2
```

---

## 🔄 重启单个服务

```bash
# 重启后端
kill $(pgrep -f yudao-server.jar)
cd /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local &

# 重启管理端
podman stop ruoyi-admin-frontend
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3
podman run --rm -d --name ruoyi-admin-frontend -v "$PWD":/workspace ... pnpm dev

# 重启用户端
podman stop ruoyi-user-frontend
cd /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-user-vue3
podman run --rm -d --name ruoyi-user-frontend -v "$PWD":/workspace ... pnpm dev
```

---

## 🐛 常见问题

| 问题 | 解决方案 |
|------|---------|
| 后端无法启动 | 检查 MySQL/Redis 是否运行; 检查端口 48080 是否被占用 |
| 前端无法访问 | 检查容器是否运行 (`podman ps`); 查看容器日志 (`podman logs <container>`) |
| 数据库连接失败 | 检查 MySQL 容器 (`podman ps`); 验证 MySQL 连接 (`podman exec ruoyi-mysql mysql -uroot -proot -e "SELECT 1;"`) |
| 页面加载卡住 | 刷新浏览器; 清除浏览器缓存; 检查前端容器日志 |

---

## 📚 更多文档

- **[README.md](./README.md)** - 项目总览
- **[FINAL_STARTUP_GUIDE.md](./FINAL_STARTUP_GUIDE.md)** - 详细启动指南与故障排查
- **[FINAL_COMPLETION_SUMMARY.md](./FINAL_COMPLETION_SUMMARY.md)** - 项目完成总结与架构设计

---

**版本**: 1.0.0 | **状态**: ✅ 生产就绪 | **最后更新**: 2026-06-10
