<template>
  <Dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
    <el-alert
      class="mb-16px"
      :closable="false"
      show-icon
      title="社区拼团活动用于西北师大社区团购。用户付款参团，团单成团后商家再按订单发货。"
      type="success"
    />
    <el-form
      ref="formRef"
      v-loading="formLoading"
      :model="formData"
      :rules="formRules"
      label-width="100px"
    >
      <el-form-item label="活动名称" prop="name">
        <el-input v-model="formData.name" placeholder="请输入活动名称" />
      </el-form-item>
      <el-form-item label="商品 SPU" prop="spuId">
        <el-input v-model="formData.spuId" disabled placeholder="请选择商品" class="!w-220px" />
        <el-button class="ml-2" @click="spuSelectRef.open()">选择商品</el-button>
      </el-form-item>
      <el-form-item label="商品 SKU" prop="skuId">
        <el-input v-model="formData.skuId" disabled placeholder="请选择 SKU" class="!w-220px" />
      </el-form-item>
      <el-form-item label="活动图" prop="picUrl">
        <el-input v-model="formData.picUrl" placeholder="默认使用商品主图，可粘贴活动图 URL" />
      </el-form-item>
      <el-form-item label="拼团价(元)" prop="groupPrice">
        <el-input-number
          v-model="groupPriceYuan"
          :min="0.01"
          :precision="2"
          :step="0.1"
          placeholder="拼团价"
        />
      </el-form-item>
      <el-form-item label="原价(元)" prop="originalPrice">
        <el-input-number
          v-model="originalPriceYuan"
          :min="0.01"
          :precision="2"
          :step="0.1"
          placeholder="原价"
        />
      </el-form-item>
      <el-form-item label="成团人数" prop="userSize">
        <el-input-number v-model="formData.userSize" :min="2" :max="10" placeholder="2/3/5" />
        <span class="ml-2 text-gray-500">建议校园团购先用 2-5 人团</span>
      </el-form-item>
      <el-form-item label="总库存" prop="stockTotal">
        <el-input-number v-model="formData.stockTotal" :min="0" placeholder="总库存" />
      </el-form-item>
      <el-form-item label="单人限购" prop="singleLimit">
        <el-input-number v-model="formData.singleLimit" :min="1" placeholder="单次购买数量限制" />
      </el-form-item>
      <el-form-item label="成团时限" prop="expireMinutes">
        <el-input-number v-model="formData.expireMinutes" :min="5" placeholder="开团后多少分钟未成团自动失败" />
        <span class="ml-2 text-gray-500">分钟</span>
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="formData.startTime"
          type="datetime"
          placeholder="开始时间"
          value-format="x"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="datetime"
          placeholder="结束时间"
          value-format="x"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button :disabled="formLoading" type="primary" @click="submitForm">确 认</el-button>
    </template>
  </Dialog>
  <SpuSelect ref="spuSelectRef" :isSelectSku="true" :radio="true" @confirm="selectSpu" />
</template>

<script setup lang="ts">
import {
  createGroupBuyActivity,
  getGroupBuyActivity,
  updateGroupBuyActivity,
  type GroupBuyActivityVO
} from '@/api/mall/promotion/groupbuy/activity'
import { SpuSelect } from '@/views/mall/promotion/components'
import * as ProductSpuApi from '@/api/mall/product/spu'

defineOptions({ name: 'GroupBuyActivityForm' })

const message = useMessage()
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formData = ref<GroupBuyActivityVO>({
  id: undefined,
  name: '',
  spuId: undefined,
  skuId: undefined,
  picUrl: '',
  groupPrice: 1000,
  originalPrice: 2000,
  userSize: 3,
  stockTotal: 100,
  singleLimit: 1,
  expireMinutes: 1440,
  startTime: '',
  endTime: '',
  status: 0
})
const formRules = reactive({
  name: [{ required: true, message: '活动名称不能为空', trigger: 'blur' }],
  spuId: [{ required: true, message: 'SPU 不能为空', trigger: 'blur' }],
  skuId: [{ required: true, message: 'SKU 不能为空', trigger: 'blur' }],
  groupPrice: [{ required: true, message: '拼团价不能为空', trigger: 'blur' }],
  originalPrice: [{ required: true, message: '原价不能为空', trigger: 'blur' }],
  userSize: [{ required: true, message: '成团人数不能为空', trigger: 'blur' }],
  stockTotal: [{ required: true, message: '总库存不能为空', trigger: 'blur' }],
  singleLimit: [{ required: true, message: '单人限购不能为空', trigger: 'blur' }],
  expireMinutes: [{ required: true, message: '成团时限不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '开始时间不能为空', trigger: 'change' }],
  endTime: [{ required: true, message: '结束时间不能为空', trigger: 'change' }]
})
const formRef = ref()
const spuSelectRef = ref()

const toYuan = (fen?: number) => Number(((Number(fen || 0)) / 100).toFixed(2))
const toFen = (yuan?: number) => Math.round(Number(yuan || 0) * 100)
const groupPriceYuan = computed({
  get: () => toYuan(formData.value.groupPrice),
  set: (value) => {
    formData.value.groupPrice = toFen(value)
  }
})
const originalPriceYuan = computed({
  get: () => toYuan(formData.value.originalPrice),
  set: (value) => {
    formData.value.originalPrice = toFen(value)
  }
})

const selectSpu = async (spuId: number, skuIds: number[]) => {
  const skuId = skuIds?.[0]
  if (!skuId) return
  const list = await ProductSpuApi.getSpuDetailList([spuId])
  const spu = list?.[0]
  const sku = spu?.skus?.find((item) => item.id === skuId)
  formData.value.spuId = spuId
  formData.value.skuId = skuId
  if (spu?.name && !formData.value.name) {
    formData.value.name = spu.name
  }
  if (spu?.picUrl && !formData.value.picUrl) {
    formData.value.picUrl = spu.picUrl
  }
  if (sku?.price && formData.value.originalPrice === 2000) {
    formData.value.originalPrice = Number(sku.price)
  }
}

const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增拼团活动' : '编辑拼团活动'
  formType.value = type
  resetForm()
  if (id) {
    formLoading.value = true
    try {
      const data = await getGroupBuyActivity(id)
      formData.value = { ...formData.value, ...data }
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

const resetForm = () => {
  formData.value = {
    id: undefined,
    name: '',
    spuId: undefined,
    skuId: undefined,
    picUrl: '',
    groupPrice: 1000,
    originalPrice: 2000,
    userSize: 3,
    stockTotal: 100,
    singleLimit: 1,
    expireMinutes: 1440,
    startTime: '',
    endTime: '',
    status: 0
  }
  formRef.value?.resetFields()
}

const submitForm = async () => {
  if (!formRef.value) return
  await formRef.value.validate()
  formLoading.value = true
  try {
    if (formType.value === 'create') {
      await createGroupBuyActivity(formData.value)
      message.success('新增成功')
    } else {
      await updateGroupBuyActivity(formData.value)
      message.success('更新成功')
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}
const emit = defineEmits(['success'])
</script>
