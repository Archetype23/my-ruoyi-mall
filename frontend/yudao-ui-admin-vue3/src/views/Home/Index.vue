<template>
  <div class="space-y-16px">
    <el-card shadow="never">
      <el-row :gutter="16" align="middle">
        <el-col :lg="16" :md="24" :sm="24" :xs="24">
          <div class="text-20px font-bold">
            {{ t('workplace.welcome') }}，{{ nickname }}，今日团购运营看板
          </div>
          <div class="mt-8px text-14px text-gray-500">
            这里展示当前团购系统的核心运营入口、活动状态和待处理事项。
          </div>
        </el-col>
        <el-col :lg="8" :md="24" :sm="24" :xs="24" class="text-right lt-lg:mt-12px" />
      </el-row>
    </el-card>

    <el-row :gutter="16">
      <el-col v-for="item in stats" :key="item.label" :lg="6" :md="12" :sm="24" :xs="24">
        <el-card shadow="never">
          <div class="text-14px text-gray-400">{{ item.label }}</div>
          <div class="mt-8px text-28px font-bold">{{ item.value }}</div>
          <div class="mt-8px text-12px text-gray-500">{{ item.desc }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16">
      <el-col :lg="14" :md="24" :sm="24" :xs="24">
        <el-card shadow="never">
          <template #header>
            <div class="flex items-center justify-between">
              <span>快捷入口</span>
              <el-tag type="success">社区团购后台</el-tag>
            </div>
          </template>
          <div class="grid grid-cols-2 gap-12px lg:grid-cols-4">
            <el-card
              v-for="item in shortcuts"
              :key="item.label"
              shadow="hover"
              class="cursor-pointer"
              @click="router.push(item.path)"
            >
              <div class="text-16px font-medium">{{ item.label }}</div>
              <div class="mt-8px text-12px text-gray-500">{{ item.desc }}</div>
            </el-card>
          </div>
        </el-card>
      </el-col>
      <el-col :lg="10" :md="24" :sm="24" :xs="24" class="lt-lg:mt-16px">
        <el-card shadow="never">
          <template #header>
            <span>最近待办</span>
          </template>
          <el-timeline>
            <el-timeline-item v-for="item in todos" :key="item.title" :timestamp="item.time">
              <div class="font-medium">{{ item.title }}</div>
              <div class="text-12px text-gray-500">{{ item.desc }}</div>
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { useUserStore } from '@/store/modules/user'
import * as TradeStatisticsApi from '@/api/mall/statistics/trade'
import * as MemberStatisticsApi from '@/api/mall/statistics/member'
import * as ProductSpuApi from '@/api/mall/product/spu'
import * as GroupBuyActivityApi from '@/api/mall/promotion/groupbuy/activity'

defineOptions({ name: 'Index' })

const { t } = useI18n()
const router = useRouter()
const userStore = useUserStore()
const nickname = computed(() => userStore.getUser.nickname || '管理员')

const loading = ref(true)
const activeGroupBuyCount = ref(0)
const pendingOrderCount = ref(0)
const productCount = ref(0)
const memberCount = ref(0)

const stats = computed(() => [
  { label: '进行中拼团', value: String(activeGroupBuyCount.value), desc: '当前正在推进的团购活动' },
  { label: '待发货订单', value: String(pendingOrderCount.value), desc: '需要商家处理的订单' },
  { label: '商品总数', value: String(productCount.value), desc: '店铺内上架商品数量' },
  { label: '会员总数', value: String(memberCount.value), desc: '已注册的会员数量' }
])

const shortcuts = [
  { label: '商品管理', desc: '维护团购商品、价格、库存', path: '/mall/product/spu' },
  { label: '拼团活动', desc: '配置拼团活动与成团规则', path: '/mall/promotion/combination/activity' },
  { label: '订单管理', desc: '查看订单、发货、更新状态', path: '/mall/trade/order' },
  { label: '会员管理', desc: '查看用户与收货信息', path: '/member/user' }
]

const todos = computed(() => {
  const list: { time: string; title: string; desc: string }[] = []
  if (activeGroupBuyCount.value === 0) list.push({ time: '提醒', title: '暂无进行中的拼团活动', desc: '前往拼团活动页面创建新的团购活动' })
  if (pendingOrderCount.value > 0) list.push({ time: '今日', title: `处理 ${pendingOrderCount.value} 笔待发货订单`, desc: '优先处理已成团的订单' })
  return list
})

onMounted(async () => {
  loading.value = true
  // 逐个请求，权限不足时静默跳过，不弹错误提示
  await GroupBuyActivityApi.getGroupBuyActivityPage({ pageNo: 1, pageSize: 1 })
    .then(r => { activeGroupBuyCount.value = r?.total || 0 })
    .catch(() => {})
  await TradeStatisticsApi.getOrderCount()
    .then(r => { pendingOrderCount.value = r?.undelivered || 0 })
    .catch(() => {})
  await ProductSpuApi.getSpuPage({ pageNo: 1, pageSize: 1 })
    .then(r => { productCount.value = r?.total || 0 })
    .catch(() => {})
  await MemberStatisticsApi.getMemberSummary()
    .then(r => { memberCount.value = r?.userCount || 0 })
    .catch(() => {})
  loading.value = false
})
</script>
