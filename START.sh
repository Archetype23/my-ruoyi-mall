#!/bin/bash
# 社区团购系统 - 快速启动脚本
# 项目路径: /mnt/data_d/Projects/ruoyi/

cd /mnt/data_d/Projects/ruoyi

echo "=================================================="
echo "  社区团购系统 - 完整启动"
echo "=================================================="
echo ""

# 检查 MySQL 容器
echo "📦 检查 MySQL 容器..."
if ! podman ps | grep -q ruoyi-mysql; then
    echo "  ⚠️  MySQL 未运行，启动中..."
    podman run -d --name ruoyi-mysql \
      -e MYSQL_DATABASE=ruoyi-vue-pro \
      -e MYSQL_ROOT_PASSWORD=root \
      -p 3306:3306 \
      -v ruoyi-mysql-data:/var/lib/mysql \
      docker.io/library/mysql:8 &
    sleep 10
else
    echo "  ✅ MySQL 已运行"
fi

# 检查 Redis 容器
echo ""
echo "📦 检查 Redis 容器..."
if ! podman ps | grep -q ruoyi-redis; then
    echo "  ⚠️  Redis 未运行，启动中..."
    podman run -d --name ruoyi-redis \
      -p 6379:6379 \
      docker.io/library/redis:7 \
      redis-server --appendonly yes &
    sleep 5
else
    echo "  ✅ Redis 已运行"
fi

# 检查后端服务
echo ""
echo "📦 检查后端服务..."
if pgrep -f "yudao-server.jar" > /dev/null; then
    echo "  ✅ 后端已运行 (http://127.0.0.1:48080)"
else
    echo "  ⚠️  后端未运行，启动中..."
    cd backend/ruoyi-vue-pro
    java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local &
    sleep 15
    cd ../..
fi

# 检查管理端
echo ""
echo "📦 检查管理端前端..."
if podman ps | grep -q ruoyi-admin-frontend; then
    echo "  ✅ 管理端已运行 (http://127.0.0.1:5174)"
else
    echo "  ⚠️  管理端未运行，启动中..."
    cd frontend/yudao-ui-admin-vue3
    podman run --rm -d \
      --name ruoyi-admin-frontend \
      -v "$PWD":/workspace \
      -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
      -w /workspace \
      -p 5174:5173 \
      docker.io/library/node:20-alpine \
      sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173" &
    sleep 30
    cd ../..
fi

# 检查用户端
echo ""
echo "📦 检查用户端前端..."
if podman ps | grep -q ruoyi-user-frontend; then
    echo "  ✅ 用户端已运行 (http://127.0.0.1:5175)"
else
    echo "  ⚠️  用户端未运行，启动中..."
    cd frontend/yudao-ui-user-vue3
    podman run --rm -d \
      --name ruoyi-user-frontend \
      -v "$PWD":/workspace \
      -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
      -w /workspace \
      -p 5175:5173 \
      docker.io/library/node:20-alpine \
      sh -c "npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173" &
    sleep 30
    cd ../..
fi

echo ""
echo "=================================================="
echo "  ✅ 系统启动完成！"
echo "=================================================="
echo ""
echo "📍 服务访问地址:"
echo "   后端 API:     http://127.0.0.1:48080"
echo "   API 文档:     http://127.0.0.1:48080/doc.html"
echo "   管理端:       http://127.0.0.1:5174"
echo "   用户端:       http://127.0.0.1:5175"
echo ""
echo "🔑 默认账号:"
echo "   用户名: admin"
echo "   密码:   admin123"
echo ""
echo "📚 文档位置:"
echo "   启动指南: ./FINAL_STARTUP_GUIDE.md"
echo "   完成总结: ./FINAL_COMPLETION_SUMMARY.md"
echo ""
