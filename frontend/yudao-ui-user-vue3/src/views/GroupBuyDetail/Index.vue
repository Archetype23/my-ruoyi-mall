<template>
  <div class="group-buy-detail">
    <el-container>
      <el-header class="page-header">
        <el-page-header @back="$router.back()" title="团购详情" />
      </el-header>
      <el-main class="page-main">
        <el-card v-if="group" class="detail-card">
          <template #header>
            <div class="card-header">
              <h2>{{ group.groupNumber || '团购' + group.id }}</h2>
              <el-tag :type="getStatusType(group.status)">{{ getStatusLabel(group.status) }}</el-tag>
            </div>
          </template>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="活动名称">{{ group.activityTitle || '-' }}</el-descriptions-item>
            <el-descriptions-item label="团号">{{ group.groupNumber || '-' }}</el-descriptions-item>
            <el-descriptions-item label="成员数">{{ group.totalMembers || 0 }} 人</el-descriptions-item>
            <el-descriptions-item label="团长">{{ group.leaderName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{ group.createdAt || '-' }}</el-descriptions-item>
            <el-descriptions-item label="自提点">{{ group.pickupLocation || '-' }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
        <el-empty v-else description="团购不存在或已结束" />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getGroupDetail } from '@/api/group-buy'

const route = useRoute()
const group = ref<any>(null)

const getStatusType = (s: number) => ({ 0: 'info', 1: 'success', 2: 'warning', 3: 'danger' } as any)[s] || 'info'
const getStatusLabel = (s: number) => ({ 0: '进行中', 1: '已成团', 2: '已失败', 3: '已取消' } as any)[s] || '未知'

onMounted(async () => {
  try {
    const id = Number(route.params.id)
    group.value = await getGroupDetail(id)
  } catch (err) {
    console.error('Failed to load group detail:', err)
  }
})
</script>

<style scoped lang="scss">
.page-header { padding: 16px 0; }
.page-main { max-width: 800px; margin: 0 auto; }
.detail-card {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    h2 { margin: 0; font-size: 20px; }
  }
}
</style>
