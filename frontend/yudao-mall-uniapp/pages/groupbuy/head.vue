<template>
  <s-layout title="团单详情" navbar="custom" :showLeftButton="true" :bgStyle="{ backgroundColor: '#fafafa' }">
  <view class="page">
    <view v-if="loading" class="state-panel">
      <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="state-title">正在加载团单</text>
      <text class="state-desc">请稍候，正在同步拼团进度</text>
    </view>

    <view v-else-if="loadError || !head" class="state-panel">
      <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="state-title">团单加载失败</text>
      <text class="state-desc">{{ loadError || '团单不存在或已关闭' }}</text>
      <button class="state-btn" @click="loadDetail(headId)">重新加载</button>
    </view>

    <view v-else class="content">
      <view class="hero">
        <view class="hero-bg">
          <image
            v-if="headImage"
            :src="headImage"
            class="hero-img"
            mode="aspectFill"
            @error="imageFailed = true"
          />
          <view v-else class="hero-fallback">
            <image class="fallback-logo" src="/static/logo.png" mode="aspectFit" />
          </view>
        </view>
        <view class="hero-shade"></view>
        <view class="hero-copy">
          <text class="brand">西北师大社区团购</text>
          <text class="title">{{ head.activityName || '社区拼团' }}</text>
          <text class="status" :class="statusClass">{{ statusText }}</text>
        </view>
      </view>

      <view class="summary-card">
        <view class="price-row">
          <view>
            <text class="price-label">拼团价</text>
            <view>
              <text class="price">¥{{ formatPrice(head.groupPrice) }}</text>
              <text v-if="head.originalPrice" class="original">¥{{ formatPrice(head.originalPrice) }}</text>
            </view>
          </view>
          <view class="role-pill">{{ head.isLeader ? '我是团长' : '我是团员' }}</view>
        </view>

        <view class="progress-copy">
          <text>{{ head.currentCount || 0 }}/{{ head.userSize || 1 }} 人已参团</text>
          <text>{{ remainText }}</text>
        </view>
        <view class="progress-track">
          <view class="progress-bar" :style="{ width: progressWidth }"></view>
        </view>

        <view class="rule-grid">
          <view class="rule-item">
            <text class="rule-value">{{ missingCount }}</text>
            <text class="rule-label">还差人数</text>
          </view>
          <view class="rule-item">
            <text class="rule-value">{{ head.userSize || 1 }}</text>
            <text class="rule-label">成团人数</text>
          </view>
          <view class="rule-item">
            <text class="rule-value">{{ memberCount }}</text>
            <text class="rule-label">已付款成员</text>
          </view>
        </view>
      </view>

      <view class="section-card">
        <view class="section-head">
          <view>
            <text class="section-title">团成员</text>
            <text class="section-desc">付款成功的成员会计入成团人数</text>
          </view>
        </view>
        <view class="member-list">
          <view v-for="(member, index) in normalizedMembers" :key="index" class="member-item">
            <view class="avatar">
              <image v-if="member.avatar" :src="member.avatar" class="avatar-img" mode="aspectFill" />
              <text v-else>{{ memberInitial(member) }}</text>
            </view>
            <view class="member-info">
              <view class="member-name-row">
                <text class="member-name">{{ member.nickname || '校园用户' }}</text>
                <text v-if="member.isLeader" class="leader-badge">团长</text>
              </view>
              <text class="member-status">{{ memberStatusText(member.status) }}</text>
            </view>
          </view>
        </view>
      </view>

      <view class="section-card">
        <view class="section-title">后续流程</view>
        <view class="flow-row">
          <view class="flow-step" :class="{ active: head.status >= 0 }">
            <text class="flow-dot">1</text>
            <text>参团付款</text>
          </view>
          <view class="flow-line"></view>
          <view class="flow-step" :class="{ active: head.status === 1 }">
            <text class="flow-dot">2</text>
            <text>拼团成功</text>
          </view>
          <view class="flow-line"></view>
          <view class="flow-step" :class="{ active: head.status === 1 }">
            <text class="flow-dot">3</text>
            <text>商家发货</text>
          </view>
        </view>
      </view>

      <view class="action-bar">
        <button v-if="head.orderId" class="primary full" @click="goOrder(head.orderId)">
          {{ orderActionText }}
        </button>
        <button v-else class="primary full" @click="goActivity">查看活动</button>
      </view>
    </view>
  </view>
  </s-layout>
</template>

<script setup>
import { computed, ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import { getGroupBuyHeadDetail } from '@/sheep/api/promotion/groupbuy'

const loading = ref(true)
const loadError = ref('')
const head = ref(null)
const headId = ref(null)
const imageFailed = ref(false)

const headImage = computed(() => {
  if (!head.value?.activityPicUrl || imageFailed.value) return ''
  return head.value.activityPicUrl
})

const memberCount = computed(() => (head.value?.members || []).length)
const missingCount = computed(() => {
  const userSize = Number(head.value?.userSize || 1)
  const currentCount = Number(head.value?.currentCount || 0)
  return Math.max(userSize - currentCount, 0)
})
const progressWidth = computed(() => {
  const userSize = Number(head.value?.userSize || 1)
  const currentCount = Number(head.value?.currentCount || 0)
  return `${Math.max(10, Math.min(100, Math.round((currentCount / userSize) * 100)))}%`
})
const statusText = computed(() => {
  if (head.value?.statusName) return head.value.statusName
  if (head.value?.status === 0) return '拼团中'
  if (head.value?.status === 1) return '已成团'
  return '已结束'
})
const statusClass = computed(() => {
  if (head.value?.status === 0) return 'pending'
  if (head.value?.status === 1) return 'success'
  return 'closed'
})
const remainText = computed(() => {
  if (head.value?.status === 1) return '已成团'
  if (head.value?.status !== 0) return '已结束'
  const seconds = Number(head.value?.expireInSeconds || 0)
  if (seconds <= 0) return '即将结束'
  const hours = Math.floor(seconds / 3600)
  const minutes = Math.floor((seconds % 3600) / 60)
  if (hours > 0) return `剩 ${hours}小时${minutes}分钟`
  return `剩 ${Math.max(minutes, 1)}分钟`
})
const normalizedMembers = computed(() => {
  const members = head.value?.members || []
  if (members.length) return members
  return [
    {
      nickname: head.value?.leaderNickname || '校园用户',
      avatar: head.value?.leaderAvatar,
      isLeader: true,
      status: 1
    }
  ]
})
const orderActionText = computed(() => {
  if (!head.value?.orderId) return '查看活动'
  return '查看订单'
})

onLoad((options) => {
  headId.value = options.id
  loadDetail(headId.value)
})

const loadDetail = async (id) => {
  loading.value = true
  loadError.value = ''
  imageFailed.value = false
  try {
    const res = await getGroupBuyHeadDetail(id)
    head.value = res.data
    if (!head.value) {
      loadError.value = '团单不存在或已关闭'
    }
  } catch (error) {
    console.error('load group-buy head detail failed', error)
    head.value = null
    loadError.value = '请确认已登录，或稍后再试'
  } finally {
    loading.value = false
  }
}

const formatPrice = (price = 0) => ((Number(price) || 0) / 100).toFixed(2)

const memberInitial = (member) => String(member?.nickname || '校').slice(0, 1)

const memberStatusText = (status) => {
  if (status === 1) return '已付款，计入成团'
  if (status === 0) return '待付款'
  return '已关闭'
}

const goOrder = (id) => {
  uni.navigateTo({ url: `/pages/order/detail?id=${id}` })
}

const goActivity = () => {
  if (!head.value?.activityId) {
    uni.switchTab({ url: '/pages/index/index' })
    return
  }
  uni.navigateTo({ url: `/pages/groupbuy/detail?id=${head.value.activityId}` })
}
</script>

<style>
.page {
  box-sizing: border-box;
  min-height: 100vh;
  padding-bottom: calc(140rpx + env(safe-area-inset-bottom));
  background: #fafafa;
}

.content {
  max-width: 430px;
  margin: 0 auto;
}

.hero {
  position: relative;
  overflow: hidden;
  height: 420rpx;
  background: #e4ebe7;
}

.hero-bg,
.hero-img,
.hero-fallback {
  width: 100%;
  height: 420rpx;
}

.hero-fallback {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f8f6;
}

.fallback-logo {
  width: 118rpx;
  height: 118rpx;
}

.hero-shade {
  position: absolute;
  right: 0;
  bottom: 0;
  left: 0;
  height: 210rpx;
  background: linear-gradient(180deg, rgba(0, 0, 0, 0), rgba(13, 34, 27, 0.62));
}

.hero-copy {
  position: absolute;
  right: 24rpx;
  bottom: 26rpx;
  left: 24rpx;
  color: #ffffff;
}

.brand,
.title {
  display: block;
}

.brand {
  color: rgba(255, 255, 255, 0.78);
  font-size: 22rpx;
}

.title {
  margin-top: 8rpx;
  font-size: 34rpx;
  font-weight: 900;
  line-height: 1.35;
}

.status {
  display: inline-block;
  margin-top: 14rpx;
  padding: 8rpx 14rpx;
  border-radius: 6rpx;
  font-size: 21rpx;
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

.summary-card,
.section-card {
  margin: 18rpx 24rpx 0;
  padding: 24rpx;
  border-radius: 8rpx;
  background: #ffffff;
}

.price-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 18rpx;
}

.price-label {
  display: block;
  color: #66756f;
  font-size: 22rpx;
}

.price {
  color: #c73a2d;
  font-size: 46rpx;
  font-weight: 900;
}

.original {
  margin-left: 12rpx;
  color: #9aa6a1;
  font-size: 24rpx;
  text-decoration: line-through;
}

.role-pill {
  flex-shrink: 0;
  padding: 10rpx 16rpx;
  border-radius: 8rpx;
  background: #e5f2ec;
  color: #f37b1d;
  font-size: 22rpx;
  font-weight: 800;
}

.progress-copy {
  display: flex;
  justify-content: space-between;
  gap: 16rpx;
  padding-top: 22rpx;
  color: #60736b;
  font-size: 23rpx;
}

.progress-track {
  overflow: hidden;
  height: 12rpx;
  margin-top: 12rpx;
  border-radius: 999rpx;
  background: #e7eee9;
}

.progress-bar {
  height: 100%;
  border-radius: 999rpx;
  background: linear-gradient(90deg, #f37b1d, #ffd44f);
}

.rule-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12rpx;
  margin-top: 22rpx;
}

.rule-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 96rpx;
  border-radius: 8rpx;
  background: #f3f7f5;
}

.rule-value {
  color: #20372e;
  font-size: 30rpx;
  font-weight: 900;
}

.rule-label {
  margin-top: 6rpx;
  color: #6c7974;
  font-size: 21rpx;
}

.section-head {
  display: flex;
  justify-content: space-between;
}

.section-title {
  display: block;
  color: #17251f;
  font-size: 29rpx;
  font-weight: 900;
}

.section-desc {
  display: block;
  margin-top: 6rpx;
  color: #71817a;
  font-size: 22rpx;
}

.member-list {
  display: flex;
  flex-direction: column;
  gap: 16rpx;
  padding-top: 22rpx;
}

.member-item {
  display: flex;
  align-items: center;
  gap: 16rpx;
  padding: 16rpx;
  border: 1rpx solid #e2eae5;
  border-radius: 8rpx;
  background: #fbfdfc;
}

.avatar,
.avatar-img {
  width: 62rpx;
  height: 62rpx;
  border-radius: 50%;
}

.avatar {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  background: #e6f2ec;
  color: #f37b1d;
  font-size: 26rpx;
  font-weight: 900;
}

.member-info {
  flex: 1;
  min-width: 0;
}

.member-name-row {
  display: flex;
  align-items: center;
  gap: 10rpx;
}

.member-name {
  overflow: hidden;
  color: #26372f;
  font-size: 26rpx;
  font-weight: 800;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.leader-badge {
  flex-shrink: 0;
  padding: 4rpx 10rpx;
  border-radius: 6rpx;
  background: #fff4dc;
  color: #9a651c;
  font-size: 20rpx;
}

.member-status {
  display: block;
  margin-top: 6rpx;
  color: #6f7d78;
  font-size: 22rpx;
}

.flow-row {
  display: flex;
  align-items: center;
  padding-top: 22rpx;
}

.flow-step {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  min-width: 0;
  color: #8a9691;
  font-size: 22rpx;
}

.flow-step.active {
  color: #f37b1d;
}

.flow-dot {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 44rpx;
  height: 44rpx;
  margin-bottom: 10rpx;
  border-radius: 50%;
  background: #dbe5df;
  color: #ffffff;
  font-size: 22rpx;
  font-weight: 800;
}

.flow-step.active .flow-dot {
  background: #f37b1d;
}

.flow-line {
  width: 46rpx;
  height: 2rpx;
  margin-bottom: 40rpx;
  background: #d9e5de;
}

.action-bar {
  position: fixed;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 10;
  display: flex;
  gap: 16rpx;
  box-sizing: border-box;
  max-width: 430px;
  margin: 0 auto;
  padding: 18rpx 24rpx calc(18rpx + env(safe-area-inset-bottom));
  background: rgba(255, 255, 255, 0.96);
  box-shadow: 0 -8rpx 26rpx rgba(30, 58, 48, 0.08);
}

.secondary,
.primary,
.state-btn {
  height: 82rpx;
  margin: 0;
  border-radius: 8rpx;
  font-size: 28rpx;
  line-height: 82rpx;
}

.secondary {
  width: 210rpx;
  background: #ffffff;
  color: #f37b1d;
  border: 1rpx solid #c9ddd4;
}

.primary {
  flex: 1;
  background: #f37b1d;
  color: #ffffff;
  font-weight: 800;
}

.primary.full {
  width: 100%;
}

.state-panel {
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;
  min-height: 100vh;
  max-width: 430px;
  margin: 0 auto;
  padding: 220rpx 48rpx 48rpx;
  background: #fafafa;
  text-align: center;
}

.state-logo {
  width: 82rpx;
  height: 82rpx;
}

.state-title {
  margin-top: 24rpx;
  color: #21352d;
  font-size: 30rpx;
  font-weight: 900;
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
  background: #f37b1d;
  color: #ffffff;
}

button::after {
  border: none;
}
</style>
