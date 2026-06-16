#!/bin/bash
# 后台重启社区团购系统全部服务（MySQL/Redis 保持运行，只重启前后端）
set -e

echo "=== 重启后端 ==="
kill $(pgrep -f yudao-server.jar) 2>/dev/null || true
sleep 2
nohup java -jar /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar \
  --spring.profiles.active=local > /tmp/yudao.log 2>&1 &
echo "后端 PID: $!"

echo "=== 重启管理后台 ==="
podman stop ruoyi-admin-frontend 2>/dev/null || true
podman rm ruoyi-admin-frontend 2>/dev/null || true
podman run -d --name ruoyi-admin-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-ui-admin-vue3:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5174:5173 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && pnpm dev --host 0.0.0.0 --port 5173" 2>&1 | tail -1

echo "=== 重启用户端 H5 ==="
podman stop ruoyi-user-frontend 2>/dev/null || true
podman rm ruoyi-user-frontend 2>/dev/null || true
podman run -d --name ruoyi-user-frontend \
  -v /mnt/data_d/Projects/ruoyi/frontend/yudao-mall-uniapp:/workspace \
  -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
  -w /workspace \
  -p 5175:3000 \
  docker.io/library/node:20-alpine \
  sh -c "npm install -g pnpm && CI=true pnpm install && UNI_INPUT_DIR=/workspace pnpm dev:h5" 2>&1 | tail -1

echo "=== 等待服务就绪 ==="
sleep 20
echo ""
echo "管理后台: http://localhost:5174"
echo "用户端 H5: http://localhost:5175"
echo "后端 API: http://localhost:48080"
