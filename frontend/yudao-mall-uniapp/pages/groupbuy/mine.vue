<template>
  <s-layout title="我的拼团" navbar="custom" :showLeftButton="true" :bgStyle="{ backgroundColor: '#fafafa' }">
  <view class="page">
    <view class="mine-hero">
      <view class="brand-row">
        <image class="brand-logo" src="/static/logo.png" mode="aspectFit" />
        <view>
          <text class="brand-eyebrow">西北师大社区团购</text>
          <text class="brand-title">我的拼团</text>
        </view>
      </view>
      <text class="hero-desc">查看当前拼团进度、付款订单和历史团单状态。</text>
    </view>

    <view class="tabs">
      <view
        v-for="(tab, index) in tabs"
        :key="tab.label"
        class="tab-item"
        :class="{ active: activeTab === index }"
        @click="switchTab(index)"
      >
        {{ tab.label }}
      </view>
    </view>

    <view v-if="loading" class="state-panel">
      <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="state-title">正在加载拼团记录</text>
      <text class="state-desc">请稍候，正在同步你的团单</text>
    </view>

    <view v-else-if="loadError" class="state-panel">
      <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="state-title">加载失败</text>
      <text class="state-desc">{{ loadError }}</text>
      <button class="state-btn" @click="loadList">重新加载</button>
    </view>

    <view v-else-if="list.length === 0" class="state-panel">
      <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="state-title">暂无拼团记录</text>
      <text class="state-desc">参与或发起拼团后，会在这里看到进度</text>
      <button class="state-btn" @click="goHome">去看看</button>
    </view>

    <view v-else class="list">
      <view v-for="item in list" :key="item.id" class="card" @click="goDetail(item.id)">
        <view class="card-main">
          <view class="media">
            <image
              v-if="getImage(item)"
              :src="getImage(item)"
              class="activity-img"
              mode="aspectFill"
              @error="markImageError(item.id)"
            />
            <view v-else class="image-fallback">
              <image class="fallback-logo" src="/static/logo.png" mode="aspectFit" />
            </view>
          </view>
          <view class="info">
            <view class="title-row">
              <text class="name">{{ item.activityName || `拼团活动 #${item.activityId}` }}</text>
              <text class="status" :class="statusClass(item)">{{ statusText(item) }}</text>
            </view>
            <view class="price-row">
              <text class="price">¥{{ formatPrice(item.groupPrice) }}</text>
              <text v-if="item.originalPrice" class="original">¥{{ formatPrice(item.originalPrice) }}</text>
            </view>
            <view class="meta-row">
              <text>{{ item.isLeader ? '我发起的团' : '我参与的团' }}</text>
              <text>{{ orderStatusText(item.orderStatus) }}</text>
            </view>
          </view>
        </view>

        <view class="progress-block">
          <view class="progress-copy">
            <text>{{ item.currentCount || 0 }}/{{ item.userSize || 1 }} 人</text>
            <text>{{ remainText(item) }}</text>
          </view>
          <view class="progress-track">
            <view class="progress-bar" :style="{ width: progressWidth(item) }"></view>
          </view>
        </view>

        <view class="actions">
          <button v-if="item.orderId" class="action-btn ghost" @click.stop="goOrder(item.orderId)">
            订单详情
          </button>
          <button v-if="item.orderStatus === 0" class="action-btn primary" @click.stop="goOrder(item.orderId)">
            去付款
          </button>
          <button v-else class="action-btn primary" @click.stop="goDetail(item.id)">
            查看进度
          </button>
        </view>
      </view>
    </view>
  </view>
  </s-layout>
</template>

<script setup>
import { ref } from 'vue'
import { onShow, onPullDownRefresh } from '@dcloudio/uni-app'
import { getMyGroupBuyHeads } from '@/sheep/api/promotion/groupbuy'

const tabs = [
  { label: '全部', value: undefined },
  { label: '拼团中', value: 0 },
  { label: '已成团', value: 1 },
  { label: '已结束', value: 2 }
]

const loading = ref(true)
const loadError = ref('')
const list = ref([])
const activeTab = ref(0)
const imageErrors = ref({})

const loadList = async () => {
  loading.value = true
  loadError.value = ''
  try {
    const res = await getMyGroupBuyHeads(tabs[activeTab.value].value)
    list.value = res.data || []
  } catch (error) {
    console.error('load my group-buy heads failed', error)
    list.value = []
    loadError.value = '请确认已登录，或稍后再试'
  } finally {
    loading.value = false
  }
}

const switchTab = (index) => {
  if (activeTab.value === index) return
  activeTab.value = index
  loadList()
}

const formatPrice = (price = 0) => ((Number(price) || 0) / 100).toFixed(2)

const statusText = (item) => {
  if (item.statusName) return item.statusName
  if (item.status === 0) return '拼团中'
  if (item.status === 1) return '已成团'
  return '已结束'
}

const statusClass = (item) => {
  if (item.status === 0) return 'pending'
  if (item.status === 1) return 'success'
  return 'closed'
}

const orderStatusText = (status) => {
  if (status === 0) return '订单待付款'
  if (status === 10) return '订单待发货'
  if (status === 20) return '订单待收货'
  if (status === 30) return '订单已完成'
  if (status === 40) return '订单已关闭'
  return '订单处理中'
}

const remainText = (item) => {
  if (item.status === 1) return '已成团'
  if (item.status !== 0) return '已结束'
  const seconds = Number(item.expireInSeconds || 0)
  if (seconds <= 0) return '即将结束'
  const hours = Math.floor(seconds / 3600)
  const minutes = Math.floor((seconds % 3600) / 60)
  if (hours > 0) return `剩 ${hours}小时${minutes}分钟`
  return `剩 ${Math.max(minutes, 1)}分钟`
}

const progressWidth = (item) => {
  const userSize = Number(item.userSize || 1)
  const currentCount = Number(item.currentCount || 0)
  return `${Math.max(10, Math.min(100, Math.round((currentCount / userSize) * 100)))}%`
}

const getImage = (item) => {
  if (!item.activityPicUrl || imageErrors.value[item.id]) return ''
  return item.activityPicUrl
}

const markImageError = (id) => {
  imageErrors.value = { ...imageErrors.value, [id]: true }
}

const goDetail = (id) => {
  uni.navigateTo({ url: `/pages/groupbuy/head?id=${id}` })
}

const goOrder = (id) => {
  if (!id) {
    uni.showToast({ title: '订单信息缺失', icon: 'none' })
    return
  }
  uni.navigateTo({ url: `/pages/order/detail?id=${id}` })
}

const goHome = () => {
  uni.switchTab({ url: '/pages/index/index' })
}

onShow(loadList)
onPullDownRefresh(async () => {
  await loadList()
  uni.stopPullDownRefresh()
})
</script>

<style>
.page {
  box-sizing: border-box;
  min-height: 100vh;
  max-width: 430px;
  margin: 0 auto;
  padding: 24rpx;
  background: #fafafa;
}

.mine-hero {
  padding: 26rpx;
  border-radius: 8rpx;
  background: linear-gradient(135deg, #ff6a00, #f37b1d);
  color: #ffffff;
}

.brand-row {
  display: flex;
  align-items: center;
  gap: 16rpx;
}

.brand-logo {
  width: 70rpx;
  height: 70rpx;
  border-radius: 8rpx;
  background: #ffffff;
}

.brand-eyebrow,
.brand-title {
  display: block;
}

.brand-eyebrow {
  color: rgba(255, 255, 255, 0.78);
  font-size: 22rpx;
}

.brand-title {
  margin-top: 4rpx;
  font-size: 34rpx;
  font-weight: 800;
}

.hero-desc {
  display: block;
  margin-top: 18rpx;
  color: rgba(255, 255, 255, 0.82);
  font-size: 24rpx;
  line-height: 1.5;
}

.tabs {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10rpx;
  padding: 22rpx 0 12rpx;
}

.tab-item {
  height: 58rpx;
  border: 1rpx solid #d9e4dc;
  border-radius: 8rpx;
  background: #ffffff;
  color: #5f7169;
  font-size: 23rpx;
  line-height: 58rpx;
  text-align: center;
}

.tab-item.active {
  background: #f37b1d;
  color: #ffffff;
  border-color: #f37b1d;
}

.list {
  display: flex;
  flex-direction: column;
  gap: 18rpx;
}

.card {
  padding: 18rpx;
  border-radius: 8rpx;
  background: #ffffff;
  box-shadow: 0 8rpx 24rpx rgba(30, 58, 48, 0.08);
}

.card-main {
  display: flex;
  gap: 18rpx;
}

.media,
.activity-img,
.image-fallback {
  flex-shrink: 0;
  width: 154rpx;
  height: 154rpx;
  border-radius: 8rpx;
}

.image-fallback {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f8f6;
}

.fallback-logo,
.state-logo {
  width: 76rpx;
  height: 76rpx;
}

.info {
  flex: 1;
  min-width: 0;
}

.title-row {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 12rpx;
}

.name {
  flex: 1;
  overflow: hidden;
  color: #17251f;
  font-size: 28rpx;
  font-weight: 800;
  line-height: 1.35;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.status {
  flex-shrink: 0;
  padding: 6rpx 12rpx;
  border-radius: 6rpx;
  font-size: 20rpx;
}

.status.pending {
  background: #fff4dc;
  color: #9a651c;
}

.status.success {
  background: #e5f2ec;
  color: #f37b1d;
}

.status.closed {
  background: #eef0ef;
  color: #727a76;
}

.price-row {
  display: flex;
  align-items: baseline;
  gap: 10rpx;
  margin-top: 18rpx;
}

.price {
  color: #c73a2d;
  font-size: 34rpx;
  font-weight: 900;
}

.original {
  color: #9aa6a1;
  font-size: 22rpx;
  text-decoration: line-through;
}

.meta-row {
  display: flex;
  flex-wrap: wrap;
  gap: 10rpx;
  margin-top: 12rpx;
}

.meta-row text {
  padding: 5rpx 10rpx;
  border-radius: 6rpx;
  background: #f1f5f2;
  color: #66756f;
  font-size: 20rpx;
}

.progress-block {
  padding-top: 18rpx;
}

.progress-copy {
  display: flex;
  justify-content: space-between;
  color: #62746d;
  font-size: 22rpx;
}

.progress-track {
  overflow: hidden;
  height: 10rpx;
  margin-top: 10rpx;
  border-radius: 999rpx;
  background: #e7eee9;
}

.progress-bar {
  height: 100%;
  border-radius: 999rpx;
  background: linear-gradient(90deg, #f37b1d, #ffd44f);
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 12rpx;
  padding-top: 18rpx;
}

.action-btn,
.state-btn {
  height: 58rpx;
  margin: 0;
  border-radius: 8rpx;
  font-size: 23rpx;
  line-height: 58rpx;
}

.action-btn {
  width: 142rpx;
}

.action-btn.ghost {
  background: #ffffff;
  color: #f37b1d;
  border: 1rpx solid #c9ddd4;
}

.action-btn.primary,
.state-btn {
  background: #f37b1d;
  color: #ffffff;
}

.state-panel {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 12rpx;
  padding: 72rpx 36rpx;
  border-radius: 8rpx;
  background: #ffffff;
  text-align: center;
}

.state-title {
  margin-top: 24rpx;
  color: #21352d;
  font-size: 28rpx;
  font-weight: 800;
}

.state-desc {
  margin-top: 10rpx;
  color: #6f7d78;
  font-size: 24rpx;
  line-height: 1.5;
}

.state-btn {
  width: 188rpx;
  margin-top: 28rpx;
}

button::after {
  border: none;
}
</style>
