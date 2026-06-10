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
        <el-col :lg="8" :md="24" :sm="24" :xs="24" class="text-right lt-lg:mt-12px">
          <XButton type="primary" @click="router.push('/mall/product')">商品管理</XButton>
          <XButton class="ml-8px" @click="router.push('/promotion/combination-activity')">
            拼团活动
          </XButton>
        </el-col>
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

defineOptions({ name: 'Index' })

const { t } = useI18n()
const router = useRouter()
const userStore = useUserStore()
const nickname = computed(() => userStore.getUser.nickname || '管理员')

const stats = [
  { label: '进行中拼团', value: '12', desc: '当前正在推进的团购活动' },
  { label: '待发货订单', value: '38', desc: '需要商家处理的订单' },
  { label: '待审核商品', value: '6', desc: '上架前需要平台检查的商品' },
  { label: '活跃会员', value: '1,284', desc: '近期参与团购的用户数量' }
]

const shortcuts = [
  { label: '商品管理', desc: '维护团购商品、价格、库存', path: '/mall/product' },
  { label: '拼团活动', desc: '配置拼团活动与成团规则', path: '/promotion/combination-activity' },
  { label: '订单管理', desc: '查看订单、发货、更新状态', path: '/mall/trade' },
  { label: '会员管理', desc: '查看用户与收货信息', path: '/member' }
]

const todos = [
  { time: '今日', title: '检查新建拼团活动', desc: '确认商品、价格和人数规则已生效' },
  { time: '今日', title: '处理待发货订单', desc: '优先处理已成团的订单' },
  { time: '今日', title: '审核商品上架申请', desc: '把违规或缺失信息的商品退回' }
]
</script>
