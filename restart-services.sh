#!/bin/bash
# 一键重启社区团购系统全部服务（MySQL / Redis / 后端 / 管理后台 / H5）

PROJECT=/mnt/data_d/Projects/ruoyi
JAR=$PROJECT/backend/ruoyi-vue-pro/yudao-server/target/yudao-server.jar

echo "=== 1/5  启动 MySQL & Redis ==="
podman start ruoyi-mysql ruoyi-redis 2>/dev/null || true
podman update --restart=always ruoyi-mysql 2>/dev/null || true
podman update --restart=always ruoyi-redis 2>/dev/null || true

# 等待 MySQL 就绪
echo -n "  等待 MySQL"
for i in $(seq 1 30); do
  if podman exec ruoyi-mysql mysqladmin ping -uroot -proot 2>/dev/null | grep -q alive; then
    echo " ✓"; break
  fi
  echo -n "."; sleep 1
done

# 等待 Redis 就绪
echo -n "  等待 Redis"
for i in $(seq 1 10); do
  if podman exec ruoyi-redis redis-cli ping 2>/dev/null | grep -q PONG; then
    echo " ✓"; break
  fi
  echo -n "."; sleep 1
done

echo ""
echo "=== 2/5  重启后端 ==="
fuser -k 48080/tcp 2>/dev/null || true
sleep 2
nohup java -jar "$JAR" \
  --spring.profiles.active=local \
  --yudao.security.mock-enable=false \
  > /tmp/yudao.log 2>&1 &
BACKEND_PID=$!
echo "  后端 PID: $BACKEND_PID"

echo -n "  等待后端"
for i in $(seq 1 60); do
  code=$(curl -4 -s -o /dev/null -w '%{http_code}' http://127.0.0.1:48080/ 2>/dev/null)
  if [ "$code" = "200" ] || [ "$code" = "401" ]; then
    echo " ✓ (HTTP $code)"; break
  fi
  if ! kill -0 $BACKEND_PID 2>/dev/null; then
    echo " ✗ 后端进程退出！"; tail -5 /tmp/yudao.log; exit 1
  fi
  echo -n "."; sleep 2
done

echo ""
echo "=== 3/5  重启管理后台 (端口 5174) ==="
if podman inspect ruoyi-admin-frontend >/dev/null 2>&1; then
  podman restart ruoyi-admin-frontend 2>/dev/null
else
  podman run -d --name ruoyi-admin-frontend --restart=always \
    -v "$PROJECT/frontend/yudao-ui-admin-vue3:/workspace" \
    -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
    -w /workspace -p 5174:5173 \
    docker.io/library/node:20-alpine \
    sh -c "npm install -g pnpm && CI=true pnpm install && pnpm dev --host 0.0.0.0 --port 5173"
fi

echo "=== 4/5  重启用户端 H5 (端口 5175) ==="
if podman inspect ruoyi-user-frontend >/dev/null 2>&1; then
  podman restart ruoyi-user-frontend 2>/dev/null
else
  podman run -d --name ruoyi-user-frontend --restart=always \
    -v "$PROJECT/frontend/yudao-mall-uniapp:/workspace" \
    -v ruoyi-pnpm-store:/root/.local/share/pnpm/store \
    -w /workspace -p 5175:3000 \
    docker.io/library/node:20-alpine \
    sh -c "npm install -g pnpm && CI=true pnpm install && UNI_INPUT_DIR=/workspace pnpm dev:h5"
fi

echo ""
echo "=== 5/5  等待前端就绪 ==="
echo -n "  管理后台"
for i in $(seq 1 60); do
  code=$(curl -4 -s -o /dev/null -w '%{http_code}' http://127.0.0.1:5174/ 2>/dev/null)
  if [ "$code" = "200" ]; then echo " ✓"; break; fi
  echo -n "."; sleep 2
done

echo -n "  H5"
for i in $(seq 1 60); do
  code=$(curl -4 -s -o /dev/null -w '%{http_code}' http://127.0.0.1:5175/ 2>/dev/null)
  if [ "$code" = "200" ]; then echo " ✓"; break; fi
  echo -n "."; sleep 2
done

echo ""
echo "========================================"
echo "  全部服务已就绪"
echo "========================================"
echo "  管理后台:  http://127.0.0.1:5174"
echo "  用户端 H5: http://127.0.0.1:5175"
echo "  后端 API:  http://127.0.0.1:48080"
echo ""
echo "  管理后台账号: admin / admin123 (租户: 西北师大团购)"
echo "  H5 测试账号: 13800000001 / admin123"
echo "========================================"
