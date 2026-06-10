<template>
  <div class="group-buy-activity">
    <el-container>
      <el-header class="page-header">
        <el-page-header @back="$router.back()" :title="activity?.title || '活动详情'" />
      </el-header>
      <el-main class="page-main">
        <el-card v-if="activity" class="activity-detail-card">
          <template #header>
            <div class="card-header">
              <h2>{{ activity.title }}</h2>
              <el-tag :type="activity.status === 1 ? 'success' : 'info'">
                {{ activity.status === 1 ? '进行中' : '已结束' }}
              </el-tag>
            </div>
          </template>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="团购价">
              <span class="price">¥{{ ((activity.groupPrice || 0) / 100).toFixed(2) }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="原价">
              <span class="original-price">¥{{ ((activity.originalPrice || 0) / 100).toFixed(2) }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="成团人数">{{ activity.groupSize || 0 }} 人</el-descriptions-item>
            <el-descriptions-item label="已参团">{{ activity.memberCount || 0 }} 人</el-descriptions-item>
            <el-descriptions-item label="开始时间">{{ activity.startTime }}</el-descriptions-item>
            <el-descriptions-item label="结束时间">{{ activity.endTime }}</el-descriptions-item>
          </el-descriptions>
          <p class="description" v-if="activity.description">{{ activity.description }}</p>
        </el-card>
        <el-empty v-else description="活动不存在或已结束" />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getGroupBuyActivityDetail } from '@/api/group-buy'

const route = useRoute()
const activity = ref<any>(null)

onMounted(async () => {
  try {
    const id = Number(route.params.id)
    activity.value = await getGroupBuyActivityDetail(id)
  } catch (err) {
    console.error('Failed to load activity:', err)
  }
})
</script>

<style scoped lang="scss">
.page-header {
  padding: 16px 0;
}
.page-main {
  max-width: 800px;
  margin: 0 auto;
}
.activity-detail-card {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    h2 { margin: 0; font-size: 20px; }
  }
}
.price { color: #f56c6c; font-size: 20px; font-weight: bold; }
.original-price { text-decoration: line-through; color: #999; }
.description { margin-top: 16px; color: #666; line-height: 1.8; }
</style>
