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
      <el-form-item label="核销码" prop="keyword">
        <el-input v-model="queryParams.keyword" class="!w-240px" clearable placeholder="订单号" />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon class="mr-5px" icon="ep:search" />搜索</el-button>
        <el-button @click="resetQuery"><Icon class="mr-5px" icon="ep:refresh" />重置</el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="成员ID" min-width="80" prop="memberId" />
      <el-table-column label="团单ID" min-width="80" prop="headId" />
      <el-table-column label="用户" min-width="120">
        <template #default="scope">
          {{ scope.row.userNickname || '匿名' }} <span class="text-gray-400">#{{ scope.row.userId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单ID" min-width="100" prop="orderId" />
      <el-table-column label="核销码" min-width="160" prop="pickUpVerifyCode" />
      <el-table-column label="订单状态" min-width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.orderStatus === 0" type="info" size="small">待支付</el-tag>
          <el-tag v-else-if="scope.row.orderStatus === 1" type="success" size="small">已支付</el-tag>
          <el-tag v-else size="small">{{ scope.row.orderStatus }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="120" fixed="right">
        <template #default="scope">
          <el-button
            v-if="scope.row.orderStatus === 1"
            v-hasPermi="['group-buy:verify:operate']"
            link
            type="primary"
            @click="handleVerify(scope.row)"
          >
            核销
          </el-button>
          <span v-else class="text-gray-400">-</span>
        </template>
      </el-table-column>
    </el-table>
  </ContentWrap>

  <!-- 核销弹窗 -->
  <Dialog v-model="verifyVisible" title="团单核销" width="400px">
    <p>确认核销订单 <strong>{{ verifyingItem?.orderId }}</strong>？</p>
    <p class="text-gray-500 text-sm">用户：{{ verifyingItem?.userNickname }} #{{ verifyingItem?.userId }}</p>
    <template #footer>
      <el-button @click="verifyVisible = false">取消</el-button>
      <el-button :loading="verifyLoading" type="primary" @click="confirmVerify">确认核销</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { getGroupBuyVerifyList, confirmGroupBuyVerify } from '@/api/mall/promotion/groupbuy/verify'

defineOptions({ name: 'GroupBuyVerifyList' })

const message = useMessage()
const queryParams = reactive({
  activityId: undefined,
  keyword: ''
})
const loading = ref(false)
const list = ref<any[]>([])
const queryFormRef = ref()

const verifyVisible = ref(false)
const verifyLoading = ref(false)
const verifyingItem = ref<any>(null)

const getList = async () => {
  loading.value = true
  try {
    list.value = await getGroupBuyVerifyList(queryParams)
  } finally {
    loading.value = false
  }
}
const handleQuery = () => getList()
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  getList()
}
const handleVerify = (item: any) => {
  verifyingItem.value = item
  verifyVisible.value = true
}
const confirmVerify = async () => {
  if (!verifyingItem.value) return
  verifyLoading.value = true
  try {
    await confirmGroupBuyVerify({ verifyCode: String(verifyingItem.value.orderId) })
    message.success('核销成功')
    verifyVisible.value = false
    await getList()
  } finally {
    verifyLoading.value = false
  }
}

onMounted(() => getList())
</script>
