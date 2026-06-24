<template>
  <ContentWrap>
    <el-form
      ref="queryFormRef"
      :inline="true"
      :model="queryParams"
      class="-mb-15px"
      label-width="68px"
    >
      <el-form-item label="活动ID" prop="activityId">
        <el-input v-model="queryParams.activityId" class="!w-240px" clearable placeholder="活动ID" />
      </el-form-item>
      <el-form-item label="团长ID" prop="leaderUserId">
        <el-input v-model="queryParams.leaderUserId" class="!w-240px" clearable placeholder="团长ID" />
      </el-form-item>
      <el-form-item label="团单状态" prop="status">
        <el-select v-model="queryParams.status" class="!w-240px" clearable placeholder="团单状态">
          <el-option label="拼团中" :value="0" />
          <el-option label="已成团" :value="1" />
          <el-option label="已失败" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon class="mr-5px" icon="ep:search" />搜索</el-button>
        <el-button @click="resetQuery"><Icon class="mr-5px" icon="ep:refresh" />重置</el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <ContentWrap>
    <el-alert
      class="mb-16px"
      :closable="false"
      show-icon
      title="团单成团后，对应交易订单会进入待发货；未成团的社区拼团订单不允许发货。"
      type="success"
    />
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="团单ID" min-width="60" prop="id" />
      <el-table-column label="活动" min-width="220">
        <template #default="scope">
          <div class="flex items-center gap-8px">
            <el-image
              v-if="scope.row.activityPicUrl"
              :src="scope.row.activityPicUrl"
              class="h-36px w-36px flex-none rounded"
              fit="cover"
              preview-teleported
            />
            <div class="min-w-0">
              <div class="truncate">{{ scope.row.activityName || '-' }}</div>
              <div class="text-xs text-gray-400">#{{ scope.row.activityId }}</div>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="团长" min-width="120">
        <template #default="scope">
          {{ scope.row.leaderNickname }} <span class="text-gray-400">#{{ scope.row.leaderUserId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人数" min-width="100">
        <template #default="scope">
          {{ scope.row.currentCount }} / {{ scope.row.userSize }}
        </template>
      </el-table-column>
      <el-table-column label="状态" min-width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 0" type="warning">拼团中</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">已成团</el-tag>
          <el-tag v-else type="danger">已失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="截止时间" min-width="160">
        <template #default="scope">{{ formatDate(scope.row.expireTime) }}</template>
      </el-table-column>
      <el-table-column label="成团时间" min-width="160">
        <template #default="scope">
          {{ scope.row.successTime ? formatDate(scope.row.successTime) : '-' }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" min-width="160">
        <template #default="scope">{{ formatDate(scope.row.createTime) }}</template>
      </el-table-column>
      <el-table-column label="操作" min-width="80" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Pagination
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      :total="total"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 详情弹窗 -->
  <Dialog v-model="detailVisible" title="团单详情" width="700px">
    <div v-loading="detailLoading">
      <el-descriptions v-if="detail" :column="2" border>
        <el-descriptions-item label="团单ID">{{ detail.id }}</el-descriptions-item>
        <el-descriptions-item label="活动ID">{{ detail.activityId }}</el-descriptions-item>
        <el-descriptions-item label="活动名称">{{ detail.activityName }}</el-descriptions-item>
        <el-descriptions-item label="拼团价">¥{{ ((detail.groupPrice || 0) / 100).toFixed(2) }}</el-descriptions-item>
        <el-descriptions-item label="团长">{{ detail.leaderNickname }} (#{{ detail.leaderUserId }})</el-descriptions-item>
        <el-descriptions-item label="人数">{{ detail.currentCount }} / {{ detail.userSize }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag v-if="detail.status === 0" type="warning">拼团中</el-tag>
          <el-tag v-else-if="detail.status === 1" type="success">已成团</el-tag>
          <el-tag v-else type="danger">已失败</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="截止时间">{{ formatDate(detail.expireTime) }}</el-descriptions-item>
        <el-descriptions-item label="成团时间">{{ detail.successTime ? formatDate(detail.successTime) : '-' }}</el-descriptions-item>
        <el-descriptions-item label="失败原因">{{ detail.failReason || '-' }}</el-descriptions-item>
      </el-descriptions>
      <h3 class="mt-4 mb-2">团成员 ({{ detail?.members?.length || 0 }})</h3>
      <el-table v-if="detail?.members" :data="detail.members" :stripe="true" size="small">
        <el-table-column label="用户ID" prop="userId" />
        <el-table-column label="昵称" prop="nickname" />
        <el-table-column label="团长">
          <template #default="scope">
            <el-tag v-if="scope.row.isLeader" type="success" size="small">团长</el-tag>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="订单ID">
          <template #default="scope">
            <el-button v-if="scope.row.orderId" link type="primary" @click="openOrder(scope.row.orderId)">
              {{ scope.row.orderId }}
            </el-button>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="状态">
          <template #default="scope">
            <el-tag v-if="scope.row.status === 0" type="info" size="small">待支付</el-tag>
            <el-tag v-else-if="scope.row.status === 1" type="success" size="small">已支付</el-tag>
            <el-tag v-else-if="scope.row.status === 2" size="small">已取消</el-tag>
            <el-tag v-else-if="scope.row.status === 3" type="warning" size="small">已退款</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="参团时间">
          <template #default="scope">{{ formatDate(scope.row.joinTime) }}</template>
        </el-table-column>
      </el-table>
    </div>
  </Dialog>
</template>

<script setup lang="ts">
import { formatDate as formatTime } from '@/utils/formatTime'
import { getGroupBuyHeadPage, getGroupBuyHeadDetail } from '@/api/mall/promotion/groupbuy/head'

defineOptions({ name: 'GroupBuyHeadList' })

const { push } = useRouter()
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  activityId: undefined,
  leaderUserId: undefined,
  status: undefined
})
const total = ref(0)
const loading = ref(false)
const list = ref([])
const queryFormRef = ref()

const detailVisible = ref(false)
const detailLoading = ref(false)
const detail = ref<any>(null)

const getList = async () => {
  loading.value = true
  try {
    const data = await getGroupBuyHeadPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  handleQuery()
}
const openDetail = async (id: number) => {
  detailVisible.value = true
  detailLoading.value = true
  try {
    detail.value = await getGroupBuyHeadDetail(id)
  } finally {
    detailLoading.value = false
  }
}
const openOrder = (id: number) => {
  push({ name: 'TradeOrderDetail', params: { id } })
}
const formatDate = (d: any) => {
  if (!d) return ''
  return formatTime(d, 'YYYY-MM-DD HH:mm:ss')
}

onMounted(() => getList())
</script>
