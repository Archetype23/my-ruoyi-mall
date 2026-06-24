# 🛍️ 课程设计作业：社区团购系统

> 基于 ruoyi-vue-pro、yudao-ui-admin-vue3、yudao-mall-uniapp 改造的校园社区团购平台

[![Status](https://img.shields.io/badge/Status-Ready-brightgreen)]()
[![Backend](https://img.shields.io/badge/Backend-Spring%20Boot%203-blue)]()
[![Frontend](https://img.shields.io/badge/Frontend-Vue%203%20%2B%20uni--app-green)]()
[![Database](https://img.shields.io/badge/Database-MySQL%208%20%2B%20Redis%207-orange)]()
[![License](https://img.shields.io/badge/License-MIT-yellow)]()

---

## 目录

- [系统概述](#系统概述)
- [改编说明](#改编说明)
- [顶层设计](#顶层设计)
- [账号体系](#账号体系)
- [数据库改造](#数据库改造)
- [后端模块](#后端模块)
- [前端改造](#前端改造)
- [客服模块](#客服模块)
- [快速启动](#快速启动)
- [开源协议](#开源协议)

---

## 系统概述

面向西北师范大学的社区团购平台。商家发布拼团活动，用户开团/参团→支付→成团→自提或快递→售后。

| 服务 | 地址 | 说明 |
|------|------|------|
| 管理后台 | http://127.0.0.1:5174 | 商家管理 / 平台管理 |
| 用户端 H5 | http://127.0.0.1:5175 | 用户拼团 |
| 后端 API | http://127.0.0.1:48080 | REST API |
| API 文档 | http://127.0.0.1:48080/doc.html | Swagger |

---

## 改编说明

本项目基于以下三个开源项目进行专项改造：

| 原项目 | 协议 | 原作者 | 仓库 |
|--------|------|--------|------|
| ruoyi-vue-pro | MIT | YunaiV | https://github.com/YunaiV/ruoyi-vue-pro |
| yudao-ui-admin-vue3 | MIT | Archer | https://github.com/yudaocode/yudao-ui-admin-vue3 |
| yudao-mall-uniapp | MIT | lidongtony | https://github.com/yudaocode/yudao-mall-uniapp |

**主要改造方向**：

1. **租户策略**：从"多租户完全隔离"改为"单租户 + 部门数据权限"，用 `system_dept` 表模拟商家
2. **核心业务**：新增完整的拼团生命周期（活动→团单→成员→成团→退款），替换原 yudao 的组合促销模式
3. **前端重新设计**：管理后台菜单精简（5 个一级菜单）+ H5 完全重写（3 tab 导航 + 淘宝橙色主题）
4. **客服系统**：启用 yudao 内置客服模块，增加部门隔离和轮询分配
5. **权限模型**：平台管理员（系统配置）↔ 商家三角色（老板/经理/客服）

---

## 顶层设计

### 架构图

```
┌─────────────────────────────────────────────────┐
│                                                     │
│   管理后台 (Vue3+Element)        用户端 H5 (uni-app)  │
│   Port 5174                      Port 5175          │
│   · 平台管理 / 商家管理            · 拼团 / 我的 / 设置  │
│   · 商品 / 拼团 / 订单 / 核销      · 客服聊天           │
│   · 统计 / 会员                    · 订单 / 售后        │
│                                                     │
└──────────────┬──────────────┬──────────────────────┘
               │              │
       HTTP/REST API          │
               │              │
        ┌──────▼──────────────▼────────┐
        │    Spring Boot 3 后端         │
        │    Port 48080                │
        │                              │
        │  业务模块:                     │
        │  ├─ yudao-module-groupbuy     │  ← ★ 新增：拼团核心
        │  ├─ yudao-module-product      │  ← 改造：dept_id 隔离
        │  ├─ yudao-module-trade        │  ← 改造：拼团订单 handlers
        │  ├─ yudao-module-promotion    │  ← 改造：客服模块启用
        │  ├─ yudao-module-member       │  ← 改造：注册接口
        │  ├─ yudao-module-pay          │  ← 复用：mock 支付
        │  └─ yudao-module-system       │  ← 复用：RBAC + 部门
        └──────────┬────────────────────┘
                   │
      ┌────────────┼────────────┐
      │            │            │
      ▼            ▼            ▼
   MySQL 8      Redis 7     外部服务
   Port 3306    Port 6379    (支付/物流)
```

### 数据权限模型

```
tenant_id = 1 (西北师大团购)          ← 单租户，所有数据共用

├── dept_id = 200  商家001          ← system_dept 表的一行
│   ├── system_users: mall001 (老板, role=161)
│   ├── system_users: kefu001, kefu0012 (客服, role=170)
│   ├── product_spu.dept_id = 200   ← 数据权限自动过滤
│   ├── group_buy_activity.dept_id = 200
│   ├── trade_order.dept_id = 200
│   └── trade_delivery_pick_up_store.dept_id = 200
│
└── dept_id = 201  商家002
    ├── system_users: mall002 (老板, role=161)
    ├── system_users: kefu002 (客服, role=170)
    └── ...（同上）

H5 用户（MEMBER 类型）
├── 不经过 DeptDataPermissionRule 过滤
├── 看到所有商家的活动
└── 所有用户统一在 tenant_id=1

平台管理员 admin (role=180, dataScope=ALL)
├── 系统管理菜单（用户/角色/菜单/商家/字典）
└── 不显示商城系统
```

---

## 账号体系

### 平台管理员

| 账号 | 密码 | 菜单 |
|------|------|------|
| `admin` | `admin123` | 系统管理 + 支付 + 会员，**无商城系统** |

### 商家内部三角色

| 角色 | 权限范围 | 示例账号（商家001） | 示例账号（商家002） |
|------|----------|-------------------|-------------------|
| **老板** (161) | 商品/拼团/订单/售后/核销/统计，**无客服中心** | `mall001` | `mall002` |
| **经理** (171) | 与老板相同 | `manager001` | — |
| **客服** (170) | **客服中心** + 订单查询 + 售后退款，**无商品/拼团** | `kefu001`, `kefu0012` | `kefu002` |

### H5 用户

| 手机号 | 密码 | 昵称 |
|--------|------|------|
| `13800000001` | `admin123` | 张同学 |
| `13900000001` | `admin123` | 张小明 |
| `13900000002` | `admin123` | 李小红 |

---

## 数据库改造

### 新增表

| 表名 | 说明 | 关键字段 |
|------|------|----------|
| `group_buy_activity` | 拼团活动 | `dept_id`, `spu_id`, `group_price`, `user_size`, `start_time`, `end_time`, `status` |
| `group_buy_head` | 团单（一个团长发起的一次拼团） | `activity_id`, `leader_user_id`, `status`, `current_count`, `user_size`, `expire_time` |
| `group_buy_member` | 团单成员 | `head_id`, `user_id`, `order_id`, `is_leader`, `status` |
| `promotion_kefu_conversation` | 客服会话 | `dept_id`, `assigned_to`, `admin_unread_message_count` |
| `promotion_kefu_message` | 客服消息 | `conversation_id`, `sender_id/type`, `content_type` |
| `trade_cart` | 购物车（兼容 H5 请求） | `user_id`, `spu_id`, `sku_id` |

### 扩展现有表

| 表 | 新增列 | 说明 |
|----|--------|------|
| `product_spu` | `dept_id` | 商品归属的商家部门 |
| `group_buy_activity` | `dept_id` | 活动归属的商家部门 |
| `trade_order` | `dept_id`, `group_buy_head_id`, `group_buy_activity_id` | 数据权限 + 拼团关联 |
| `trade_delivery_pick_up_store` | `dept_id` | 自提门店归属的商家部门 |
| `trade_config` | `after_sale_refund_reasons`, `after_sale_return_reasons` | 售后原因配置 |

### 初始化 SQL

主脚本：`backend/ruoyi-vue-pro/sql/mysql/group-buy-single-tenant-init.sql`（幂等，可重复执行）。

---

## 后端模块

### 新增模块

#### yudao-module-groupbuy（拼团核心）

| 类 | 职责 |
|----|------|
| `GroupBuyActivityServiceImpl` | 活动 CRUD + 状态刷新（0→1, 2→1 恢复, →2 过期）+ 库存扣减 |
| `GroupBuyHeadServiceImpl` | 开团/参团 + 成团检测 + 过期退款 + 核销管理 |
| `GroupBuyActivityStatusJob` | 定时刷新活动状态（每分钟） |
| `GroupBuyHeadExpireJob` | 定时扫描过期团单并退款（每分钟） |
| `TradeGroupBuyOrderHandler` | 拼团订单生命周期：创建时创建待支付成员、支付时更新成员状态、发货前成团校验 |
| `GroupBuyVerifyController` | 核销列表（按 dept_id 过滤）+ 核销确认 |

### 改造模块

| 模块 | 改造内容 |
|------|----------|
| `yudao-module-product` | `ProductSpuDO` 增加 `deptId` 字段；`createSpu` 自动填充 |
| `yudao-module-trade` | `TradeOrderDO` 增加 `deptId`；`TradeGroupBuyOrderHandler` 从活动继承 deptId；订单列表接入核销按钮 |
| `yudao-module-member` | `MemberAuthService` 新增 `register()` 注册接口；`MemberUserService` 新增 `setUserPassword()` |
| `yudao-module-promotion` | 启用客服模块：`KeFuConversationDO` 增加 `deptId`/`assignedTo`；管理员消息列表解析头像；App 端消息发送 |
| `yudao-module-system` | 部门管理作为商家管理；角色权限重配 |
| `yudao-framework-web` | `GlobalExceptionHandler` 移除"商城系统已禁用"误报 |
| `yudao-framework-security` | `application-local.yaml` 关闭 `mock-enable`、关闭验证码 |

---

## 前端改造

### 管理后台 (yudao-ui-admin-vue3)

| 改造 | 说明 |
|------|------|
| 菜单精简 | 保留系统管理/支付/会员/商城系统，去掉基础设施/系统工具/系统监控等无用菜单 |
| 首页 | 去掉右上角"商品管理""拼团活动"按钮；分 API 静默捕获无权限错误 |
| 登录页 | 隐藏租户输入框 |
| 订单列表 | "更多"下拉增加"核销"按钮（自提+待发货订单） |
| 订单详情 | `v-if` 防止字段 undefined；修复 v-clipboard 未注册 |
| ToolHeader | 隐藏"选择租户"按钮 |
| 颜色主题 | 统一橙色 `#f37b1d` |

### 用户端 H5 (yudao-mall-uniapp)

| 改造 | 说明 |
|------|------|
| 导航结构 | 3 个 tab：拼团 / 我的 / 设置（去掉首页/分类/购物车） |
| 颜色主题 | 12 个文件 55 处颜色替换：`#176b52`→`#f37b1d`，统一淘宝橙色 |
| 我的页面 | 全屏布局 + 未登录提示 + 4 列功能网格 |
| 设置页面 | 未登录→登录按钮+注册入口；已登录→用户信息+退出登录 |
| 汉堡菜单 | 5 项 emoji 图标（拼团/我的拼团/我的订单/我的/设置） |
| 拼团进度 | 改为弹窗组件，不再跳转单独页面 |
| 联系客服 | 4 个入口（汉堡菜单/拼团详情/订单详情/拼团进度弹窗） |
| 注册页 | 手机号+密码+确认密码 |
| 导航栏 | 所有非 tabbar 页面统一 back+汉堡按钮 |

---

## 客服模块

### 数据模型

```
promotion_kefu_conversation (会话)
├── user_id     → 会员用户 ID
├── dept_id     → 商家部门 (200/201/...)
├── assigned_to → 分配的客服用户 ID（轮询）
└── admin_unread_message_count → 未读数

promotion_kefu_message (消息)
├── sender_id / sender_type   → 发送者（会员=1 / 管理员=2）
├── content_type              → 文本(1) / 商品卡片(10) / 订单卡片(11)
└── read_status               → 已读状态
```

### 轮询分配

新会话自动分配给当前有效会话数最少的客服：

```sql
SELECT u.id FROM system_users u
JOIN system_user_role ur ON u.id = ur.user_id
JOIN system_role r ON ur.role_id = r.id
WHERE r.code = 'kefu_staff' AND u.dept_id = ?
ORDER BY (SELECT COUNT(*) FROM promotion_kefu_conversation
          WHERE assigned_to = u.id AND admin_deleted = 0) ASC
LIMIT 1
```

管理员回复未分配会话时，自动归属给该管理员。

### 管理后台客服界面

三栏布局：会话列表（左）| 消息聊天（中）| 用户信息（右），支持文本/商品卡片/订单卡片三种消息类型。

---

## 快速启动

### 一键启动

```bash
cd /mnt/data_d/Projects/ruoyi
./restart-services.sh
```

脚本会自动：启动 MySQL+Redis → 编译/启动后端 → 启动两个前端。约 30 秒全部就绪。

### 初始化数据库（首次）

```bash
podman exec -i ruoyi-mysql mysql -uroot -proot ruoyi-vue-pro --default-character-set=utf8mb4 \
  < backend/ruoyi-vue-pro/sql/mysql/group-buy-single-tenant-init.sql
```

### 后端编译

```bash
podman run --rm --network host \
  -v /mnt/data_d/Projects/ruoyi/backend/ruoyi-vue-pro:/workspace \
  -v "$HOME/.m2/repository:/root/.m2/repository" \
  -v /tmp/opencode/settings.xml:/root/.m2/settings.xml \
  -w /workspace maven:3.9-eclipse-temurin-17 \
  mvn install -pl yudao-server -am -DskipTests -T 1C
```

### 技术栈

| 层 | 技术 |
|----|------|
| 后端框架 | Spring Boot 3 + MyBatis-Plus |
| 鉴权 | Spring Security + Token + RBAC |
| 定时任务 | Quartz |
| 管理前端 | Vue 3 + Vite + Element Plus + TypeScript |
| 用户前端 | uni-app (Vue 3) + Vite + Pinia |
| 数据库 | MySQL 8 + Redis 7 |
| 容器 | Podman |
| 构建 | Maven 3.9 + pnpm |

---

## 开源协议

### 本项目

[MIT License](LICENSE)

### 原项目协议声明

本项目基于以下 MIT 协议的开源项目改造，遵照 MIT 协议要求保留所有原始版权声明：

| 原项目 | 协议文件位置 | 版权声明 |
|--------|-------------|----------|
| ruoyi-vue-pro | `backend/ruoyi-vue-pro/LICENSE` | Copyright (c) 2021 ruoyi-vue-pro |
| yudao-ui-admin-vue3 | `frontend/yudao-ui-admin-vue3/LICENSE` | Copyright (c) 2021-present Archer |
| yudao-mall-uniapp | `frontend/yudao-mall-uniapp/LICENSE` | Copyright (c) 2022 lidongtony |

三个子模块的原始 LICENSE 文件均已保留在各自目录中，未经修改。本项目的改造工作（新增模块、前端重设计、数据权限模型调整等）同样以 MIT 协议开源。

---

## 文档

- 📄 [启动指南](./start.md)
- 📄 [改造文档](./Docs/社区团购系统改造文档.md)
- 📄 [重启脚本](./restart-services.sh)

---

**最后更新**: 2026-06-23

**贡献者**: Zi-Lang、GitHub Copilot
