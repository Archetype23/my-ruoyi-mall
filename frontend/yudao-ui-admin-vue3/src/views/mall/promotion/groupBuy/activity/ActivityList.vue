<template>
  <ContentWrap>
    <!-- 搜索 -->
    <el-form
      ref="queryFormRef"
      :inline="true"
      :model="queryParams"
      class="-mb-15px"
      label-width="68px"
    >
      <el-form-item label="活动名称" prop="name">
        <el-input
          v-model="queryParams.name"
          class="!w-240px"
          clearable
          placeholder="请输入活动名称"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动状态" prop="status">
        <el-select
          v-model="queryParams.status"
          class="!w-240px"
          clearable
          placeholder="请选择活动状态"
        >
          <el-option label="未开始" :value="0" />
          <el-option label="进行中" :value="1" />
          <el-option label="已结束" :value="2" />
          <el-option label="已关闭" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon class="mr-5px" icon="ep:search" />搜索</el-button>
        <el-button @click="resetQuery"><Icon class="mr-5px" icon="ep:refresh" />重置</el-button>
        <el-button
          v-hasPermi="['group-buy:activity:create']"
          plain
          type="primary"
          @click="openForm('create')"
        >
          <Icon class="mr-5px" icon="ep:plus" />新增
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-alert
      class="mb-16px"
      :closable="false"
      show-icon
      title="社区拼团活动关闭后不再接受新开团；已付款且已成团的订单仍需在交易订单中发货。"
      type="info"
    />
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" min-width="60" prop="id" />
      <el-table-column label="活动名称" min-width="160" prop="name" />
      <el-table-column label="商品图" min-width="80">
        <template #default="scope">
          <el-image
            v-if="scope.row.picUrl"
            :src="scope.row.picUrl"
            class="h-40px w-40px"
            :preview-src-list="[scope.row.picUrl]"
            preview-teleported
          />
        </template>
      </el-table-column>
      <el-table-column label="拼团价" min-width="100">
        <template #default="scope">¥{{ (scope.row.groupPrice / 100).toFixed(2) }}</template>
      </el-table-column>
      <el-table-column label="原价" min-width="100">
        <template #default="scope">¥{{ (scope.row.originalPrice / 100).toFixed(2) }}</template>
      </el-table-column>
      <el-table-column label="成团人数" min-width="80" prop="userSize" />
      <el-table-column label="库存" min-width="120">
        <template #default="scope">
          <div class="flex flex-col gap-4px">
            <span>{{ scope.row.stockUsed }} / {{ scope.row.stockTotal }}</span>
            <el-progress
              :percentage="stockPercent(scope.row)"
              :show-text="false"
              :stroke-width="6"
              :status="stockPercent(scope.row) >= 100 ? 'exception' : undefined"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column label="活动期" min-width="200">
        <template #default="scope">
          {{ formatDate(scope.row.startTime, 'YYYY-MM-DD HH:mm') }} ~
          {{ formatDate(scope.row.endTime, 'YYYY-MM-DD HH:mm') }}
        </template>
      </el-table-column>
      <el-table-column label="状态" min-width="80">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 0" type="info">未开始</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">进行中</el-tag>
          <el-tag v-else-if="scope.row.status === 2">已结束</el-tag>
          <el-tag v-else-if="scope.row.status === 3" type="danger">已关闭</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="200" fixed="right">
        <template #default="scope">
          <el-button
            v-hasPermi="['group-buy:activity:update']"
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
          >
            编辑
          </el-button>
          <el-button
            v-if="scope.row.status !== 3"
            v-hasPermi="['group-buy:activity:close']"
            link
            type="warning"
            @click="handleClose(scope.row.id)"
          >
            关闭
          </el-button>
          <el-button
            v-hasPermi="['group-buy:activity:delete']"
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
          >
            删除
          </el-button>
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

  <!-- 表单弹窗 -->
  <GroupBuyActivityForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { formatDate as formatTime } from '@/utils/formatTime'
import { getGroupBuyActivityPage, deleteGroupBuyActivity, closeGroupBuyActivity } from '@/api/mall/promotion/groupbuy/activity'
import GroupBuyActivityForm from './GroupBuyActivityForm.vue'

defineOptions({ name: 'GroupBuyActivityList' })

const message = useMessage()
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  name: '',
  status: undefined
})
const total = ref(0)
const loading = ref(false)
const list = ref([])
const queryFormRef = ref()
const formRef = ref()

const getList = async () => {
  loading.value = true
  try {
    const data = await getGroupBuyActivityPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value?.open(type, id)
}
const handleClose = async (id: number) => {
  try {
    await message.confirm('确认关闭该活动？')
    await closeGroupBuyActivity(id)
    message.success('关闭成功')
    await getList()
  } catch (e) {
    /* cancel */
  }
}
const handleDelete = async (id: number) => {
  try {
    await message.confirm('确认删除该活动？')
    await deleteGroupBuyActivity(id)
    message.success('删除成功')
    await getList()
  } catch (e) {
    /* cancel */
  }
}

const formatDate = (d: any, fmt: string) => {
  if (!d) return ''
  return formatTime(d, fmt)
}
const stockPercent = (row: any) => {
  const total = Number(row.stockTotal || 0)
  if (total <= 0) return 0
  return Math.min(100, Math.round((Number(row.stockUsed || 0) / total) * 100))
}

onMounted(() => getList())
</script>
