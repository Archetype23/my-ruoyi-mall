#!/bin/bash

# Community Group-Buy System - Complete Startup Guide
# 社区团购系统 - 完整启动指南

set -e

PROJECT_ROOT="/mnt/data_d/Projects/ruoyi"
BACKEND_DIR="$PROJECT_ROOT/backend/ruoyi-vue-pro"
FRONTEND_ADMIN_DIR="$PROJECT_ROOT/frontend/yudao-ui-admin-vue3"
FRONTEND_USER_DIR="$PROJECT_ROOT/frontend/yudao-ui-user-vue3"

echo "=========================================="
echo "社区团购系统启动脚本"
echo "=========================================="
echo ""

# Check if containers are running
echo "📋 检查容器状态..."
podman ps --format 'table {{.Names}}\t{{.Status}}\t{{.Ports}}' | grep -E "ruoyi-(mysql|redis|frontend)" || true

echo ""
echo "=========================================="
echo "启动指令汇总"
echo "=========================================="
echo ""

echo "1️⃣  启动数据库（已运行则跳过）:"
echo "   podman run -d --name ruoyi-mysql \\"
echo "     -e MYSQL_DATABASE=ruoyi-vue-pro \\"
echo "     -e MYSQL_ROOT_PASSWORD=root \\"
echo "     -p 3306:3306 \\"
echo "     -v ruoyi-mysql-data:/var/lib/mysql \\"
echo "     -v '$PROJECT_ROOT/sql/mysql/ruoyi-vue-pro.sql':/docker-entrypoint-initdb.d/ruoyi-vue-pro.sql:ro \\"
echo "     docker.io/library/mysql:8"
echo ""

echo "2️⃣  启动缓存（已运行则跳过）:"
echo "   podman run -d --name ruoyi-redis \\"
echo "     -p 6379:6379 \\"
echo "     docker.io/library/redis:7-alpine"
echo ""

echo "3️⃣  启动后端（在终端1）:"
echo "   cd $BACKEND_DIR"
echo "   java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local"
echo ""

echo "4️⃣  启动管理端前端（在终端2）:"
echo "   cd $FRONTEND_ADMIN_DIR"
echo "   podman run --rm -it \\"
echo "     -v \"\$PWD\":/workspace \\"
echo "     -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \\"
echo "     -w /workspace \\"
echo "     -p 5173:5173 \\"
echo "     docker.io/library/node:20-alpine \\"
echo "     sh -c 'npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173'"
echo ""

echo "5️⃣  启动用户端前端（在终端3）:"
echo "   cd $FRONTEND_USER_DIR"
echo "   podman run --rm -it \\"
echo "     -v \"\$PWD\":/workspace \\"
echo "     -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \\"
echo "     -w /workspace \\"
echo "     -p 5174:5173 \\"
echo "     docker.io/library/node:20-alpine \\"
echo "     sh -c 'npm install -g pnpm && pnpm install && pnpm dev --host 0.0.0.0 --port 5173'"
echo ""

echo "=========================================="
echo "访问地址"
echo "=========================================="
echo ""
echo "📊 后端 API: http://127.0.0.1:48080"
echo "💼 管理端: http://localhost:5173  (Login: admin/admin123)"
echo "👤 用户端: http://localhost:5174  (Member login)"
echo ""

echo "=========================================="
echo "测试命令"
echo "=========================================="
echo ""

echo "✅ 检查后端健康:"
echo "   curl -sS http://127.0.0.1:48080/admin-api/system/dict-data/simple-list?dictType=system_user_sex | head -c 200"
echo ""

echo "✅ 检查团购模块:"
echo "   curl -sS -H 'tenant-id: 1' http://127.0.0.1:48080/app-api/promotion/combination-activity/page?pageNo=1&pageSize=1"
echo ""

echo "✅ 检查会员模块:"
echo "   curl -sS -H 'tenant-id: 1' http://127.0.0.1:48080/admin-api/member/tag/page?pageNo=1&pageSize=1"
echo ""

echo "=========================================="
echo "常见问题"
echo "=========================================="
echo ""
echo "Q: 前端无法连接后端？"
echo "A: 检查 .env 文件中的 VITE_API_BASE_URL 是否正确"
echo ""

echo "Q: MySQL 连接失败？"
echo "A: 检查容器是否运行: podman ps | grep mysql"
echo ""

echo "Q: 前端报 node_modules 错误？"
echo "A: 删除 node_modules 并重新安装: rm -rf node_modules && pnpm install"
echo ""

echo "✨ 系统就绪！"
