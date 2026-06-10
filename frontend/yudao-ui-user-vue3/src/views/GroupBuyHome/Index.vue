<template>
  <div class="group-buy-home">
    <el-container>
      <el-header class="header">
        <div class="header-content">
          <h1>🛒 社区团购</h1>
          <div class="user-info">
            <span>{{ userInfo.name || userInfo.nickname }}</span>
            <el-button type="text" @click="logout">退出</el-button>
          </div>
        </div>
      </el-header>

      <el-main class="main-content">
        <!-- 活跃团购活动 -->
        <section class="section">
          <h2>🎯 最新团购活动</h2>
          <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" v-for="activity in activities" :key="activity.id">
              <el-card class="activity-card">
                <template #header>
                  <div class="card-header">
                    <span class="title">{{ activity.name }}</span>
                    <el-tag type="success" v-if="activity.status === 1">进行中</el-tag>
                  </div>
                </template>
                <div class="activity-info">
                  <p><strong>参与人数：</strong> {{ activity.current_count }}/{{ activity.user_size }}</p>
                  <p><strong>优惠价格：</strong> ¥{{ (activity.price / 100).toFixed(2) }}</p>
                  <p><strong>剩余时间：</strong> {{ activity.time_left }}</p>
                </div>
                <el-button type="primary" @click="viewActivityDetail(activity.id)" block>
                  查看详情
                </el-button>
              </el-card>
            </el-col>
          </el-row>
          <el-empty v-if="!activities || activities.length === 0" description="暂无活跃团购" />
        </section>

        <!-- 我的团购 -->
        <section class="section">
          <h2>📦 我的团购</h2>
          <el-table :data="myGroups" stripe>
            <el-table-column prop="activity_name" label="活动名称" />
            <el-table-column prop="member_count" label="成员数" />
            <el-table-column prop="status" label="状态">
              <template #default="{ row }">
                <el-tag :type="getStatusType(row.status)">
                  {{ getStatusLabel(row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="create_time" label="加入时间" />
            <el-table-column label="操作" width="150">
              <template #default="{ row }">
                <el-button type="primary" size="small" @click="viewGroupDetail(row.id)">
                  查看详情
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-empty v-if="!myGroups || myGroups.length === 0" description="您还没有参加任何团购" />
        </section>

        <!-- 操作指南 -->
        <section class="section guides">
          <h2>📖 使用指南</h2>
          <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="6">
              <div class="guide-card">
                <div class="guide-icon">1️⃣</div>
                <h3>浏览活动</h3>
                <p>查看最新的团购活动和商品</p>
              </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="6">
              <div class="guide-card">
                <div class="guide-icon">2️⃣</div>
                <h3>加入团购</h3>
                <p>选择活动并加入购买</p>
              </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="6">
              <div class="guide-card">
                <div class="guide-icon">3️⃣</div>
                <h3>支付订单</h3>
                <p>确认订单并支付</p>
              </div>
            </el-col>
            <el-col :xs="24" :sm="12" :md="6">
              <div class="guide-card">
                <div class="guide-icon">4️⃣</div>
                <h3>提货</h3>
                <p>到指定地点提取商品</p>
              </div>
            </el-col>
          </el-row>
        </section>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAppStore } from '@/store/modules/app'
import { loginOut } from '@/api/login'

const router = useRouter()
const appStore = useAppStore()

const userInfo = ref({
  name: '',
  nickname: ''
})

const activities = ref([
  {
    id: 1,
    name: '新鲜蔬菜团购',
    price: 4900,
    user_size: 10,
    current_count: 7,
    status: 1,
    time_left: '3小时'
  },
  {
    id: 2,
    name: '水果礼盒团购',
    price: 7900,
    user_size: 15,
    current_count: 12,
    status: 1,
    time_left: '5小时'
  },
  {
    id: 3,
    name: '日用百货团购',
    price: 2900,
    user_size: 20,
    current_count: 18,
    status: 1,
    time_left: '2小时'
  }
])

const myGroups = ref([
  {
    id: 1,
    activity_name: '新鲜蔬菜团购',
    member_count: 8,
    status: 1,
    create_time: '2026-06-09 14:30'
  },
  {
    id: 2,
    activity_name: '水果礼盒团购',
    member_count: 12,
    status: 2,
    create_time: '2026-06-08 10:15'
  }
])

const getStatusType = (status: number) => {
  const types: Record<number, string> = {
    0: 'info',
    1: 'success',
    2: 'warning',
    3: 'danger'
  }
  return types[status] || 'info'
}

const getStatusLabel = (status: number) => {
  const labels: Record<number, string> = {
    0: '待开始',
    1: '进行中',
    2: '已完成',
    3: '已取消'
  }
  return labels[status] || '未知'
}

const viewActivityDetail = (id: number) => {
  router.push(`/group-buy/activity/${id}`)
}

const viewGroupDetail = (id: number) => {
  router.push(`/group-buy/my-group/${id}`)
}

const logout = async () => {
  try {
    await loginOut()
    router.push('/login')
  } catch (error) {
    console.error('Logout failed:', error)
  }
}

onMounted(() => {
  // Load user info from store
  const user = appStore.user
  if (user) {
    userInfo.value = {
      name: user.realname || '',
      nickname: user.nickname || ''
    }
  }
})
</script>

<style scoped lang="scss">
.group-buy-home {
  .header {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    padding: 20px;

    .header-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      max-width: 1200px;
      margin: 0 auto;

      h1 {
        margin: 0;
        font-size: 28px;
      }

      .user-info {
        display: flex;
        gap: 20px;
        align-items: center;

        span {
          font-size: 16px;
        }

        :deep(.el-button) {
          color: white;
          font-size: 14px;

          &:hover {
            opacity: 0.8;
          }
        }
      }
    }
  }

  .main-content {
    max-width: 1200px;
    margin: 0 auto;
    padding: 40px 20px;

    .section {
      margin-bottom: 50px;

      h2 {
        font-size: 22px;
        margin-bottom: 20px;
        color: #333;
        border-left: 4px solid #667eea;
        padding-left: 12px;
      }
    }

    .activity-card {
      transition: all 0.3s ease;

      &:hover {
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
        transform: translateY(-4px);
      }

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;

        .title {
          font-weight: 600;
          font-size: 16px;
        }
      }

      .activity-info {
        margin-bottom: 16px;

        p {
          margin: 8px 0;
          color: #666;
          font-size: 14px;
        }
      }
    }

    .guides {
      .guide-card {
        background: #f5f7fa;
        border-radius: 8px;
        padding: 20px;
        text-align: center;
        transition: all 0.3s ease;

        &:hover {
          background: #e8ecf1;
          transform: translateY(-4px);
        }

        .guide-icon {
          font-size: 40px;
          margin-bottom: 12px;
        }

        h3 {
          margin: 12px 0 8px 0;
          font-size: 16px;
          color: #333;
        }

        p {
          margin: 0;
          color: #666;
          font-size: 13px;
          line-height: 1.5;
        }
      }
    }
  }

  :deep(.el-table) {
    margin-top: 16px;
  }

  :deep(.el-empty) {
    padding: 40px 0;
  }
}
</style>
