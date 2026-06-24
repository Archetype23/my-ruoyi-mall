<template>
  <s-layout title="拼团详情" navbar="custom" :showLeftButton="true" :bgStyle="{ backgroundColor: '#fafafa' }">
  <view class="page">
    <view v-if="loading" class="state-panel">
      <view class="state-logo-wrap">
        <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      </view>
      <text class="state-title">正在加载拼团详情</text>
      <text class="state-desc">请稍候，正在同步活动和团单信息</text>
    </view>

    <view v-else-if="loadError || !activity" class="state-panel">
      <view class="state-logo-wrap muted">
        <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
      </view>
      <text class="state-title">详情加载失败</text>
      <text class="state-desc">{{ loadError || '活动不存在或已下架' }}</text>
      <button class="state-btn" @click="loadDetail">重新加载</button>
    </view>

    <view v-else class="detail-shell">
      <view class="media-section">
        <image
          v-if="detailImage"
          :src="detailImage"
          class="banner"
          mode="aspectFill"
          @error="imageFailed = true"
        />
        <view v-else class="banner-fallback">
          <image class="fallback-logo" src="/static/logo.png" mode="aspectFit" />
          <text>西北师大社区团购</text>
        </view>
        <view class="media-shade"></view>
        <text class="activity-status" :class="{ soldout: isSoldOut }">{{ activityStatus }}</text>
      </view>

      <view class="summary-section">
        <view class="brand-line">
          <view class="brand-left">
            <image class="brand-logo" src="/static/logo.png" mode="aspectFit" />
            <text>西北师大校园优选</text>
          </view>
          <text class="brand-chat" @tap="goChat">联系客服 ›</text>
        </view>
        <view class="activity-name">{{ activity.name }}</view>

        <view v-if="myHead" class="joined-banner" @click="goMyHead">
          <view class="joined-copy">
            <text class="joined-title">你已参与该拼团</text>
            <text class="joined-desc">点击查看拼团进度与成员</text>
          </view>
          <text class="joined-arrow">查看进度 ›</text>
        </view>

        <view class="price-panel">
          <view>
            <text class="price-label">拼团价</text>
            <view class="price-row">
              <text class="group-price">¥{{ formatPrice(activity.groupPrice) }}</text>
              <text v-if="activity.originalPrice" class="original-price">
                ¥{{ formatPrice(activity.originalPrice) }}
              </text>
            </view>
          </view>
          <view class="stock-box" :class="{ empty: isSoldOut }">
            <text>{{ isSoldOut ? '已售罄' : `剩 ${activity.stockRemain || 0} 件` }}</text>
          </view>
        </view>

        <view class="rule-grid">
          <view class="rule-item">
            <text class="rule-value">{{ activity.userSize || 1 }}人</text>
            <text class="rule-label">成团人数</text>
          </view>
          <view class="rule-item">
            <text class="rule-value">{{ activity.singleLimit || 1 }}件</text>
            <text class="rule-label">单次限购</text>
          </view>
          <view class="rule-item">
            <text class="rule-value">{{ activeHeadCount }}个</text>
            <text class="rule-label">可参与团</text>
          </view>
        </view>
      </view>

      <view class="flow-section">
        <view class="section-title">拼团流程</view>
        <view class="flow-row">
          <view class="flow-step">
            <text class="flow-index">1</text>
            <text>发起或参团</text>
          </view>
          <view class="flow-line"></view>
          <view class="flow-step">
            <text class="flow-index">2</text>
            <text>钱包付款</text>
          </view>
          <view class="flow-line"></view>
          <view class="flow-step">
            <text class="flow-index">3</text>
            <text>成团发货</text>
          </view>
        </view>
      </view>

      <view class="head-section">
        <view class="section-head">
          <view>
            <text class="section-title">正在成团</text>
            <text class="section-desc">选择一个团参团，或自己发起新团</text>
          </view>
          <text class="head-count">{{ activeHeadCount }} 个团</text>
        </view>

        <view v-if="activeHeads.length" class="head-list">
          <view v-for="head in activeHeads" :key="head.headId" class="head-item">
            <view class="head-avatar">
              <text>{{ leaderInitial(head) }}</text>
            </view>
            <view class="head-main">
              <view class="head-title-row">
                <text class="head-title">{{ head.leaderNickname || '校园用户' }} 的团</text>
                <text class="head-missing">差 {{ missingCount(head) }} 人</text>
              </view>
              <view class="head-progress">
                <view class="head-progress-bar" :style="{ width: headProgressWidth(head) }"></view>
              </view>
              <view class="head-meta">
                <text>{{ head.currentCount || 0 }}/{{ head.userSize || activity.userSize || 1 }} 人</text>
                <text>剩余 {{ formatDuration(head.expireInSeconds) }}</text>
              </view>
            </view>
            <button class="join-btn" :disabled="isSoldOut" @click="joinGroup(head.headId)">
              {{ isSoldOut ? '售罄' : '参团' }}
            </button>
          </view>
        </view>

        <view v-else class="empty-head">
          <image class="empty-logo" src="/static/logo.png" mode="aspectFit" />
          <text>暂时没有可参与的团</text>
          <text class="empty-tip">可以直接发起新团，邀请同学或邻里一起拼</text>
        </view>
      </view>

      <view class="service-section">
        <view class="section-title">服务说明</view>
        <view class="service-grid">
          <view class="service-item">
            <text class="service-mark">校</text>
            <text>校园社区优选商品</text>
          </view>
          <view class="service-item">
            <text class="service-mark delivery">配</text>
            <text>成团后商家发货</text>
          </view>
          <view class="service-item">
            <text class="service-mark pay">付</text>
            <text>订单使用钱包支付</text>
          </view>
        </view>
      </view>

      <view class="action-bar">
        <button class="action-secondary" @click="goMy">我的拼团</button>
        <button v-if="myHead" class="action-primary" @click="goMyHead">查看我的拼团进度</button>
        <button v-else class="action-primary" :disabled="!canOpenGroup" @click="openGroup">
          {{ primaryActionText }}
        </button>
      </view>
    </view>
  </view>
  </s-layout>
</template>

<script setup>
import { computed, ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import sheep from '@/sheep'
import { getAppGroupBuyActivityDetail, getMyGroupBuyHeads } from '@/sheep/api/promotion/groupbuy'

const loading = ref(true)
const loadError = ref('')
const activity = ref(null)
const activityId = ref(null)
const imageFailed = ref(false)
// 当前用户在本活动下进行中的团（用于“已参团”引导）
const myHead = ref(null)

const activeHeads = computed(() => activity.value?.activeHeads || [])
const activeHeadCount = computed(() => activeHeads.value.length)
const detailImage = computed(() => {
  if (!activity.value?.picUrl || imageFailed.value) {
    return ''
  }
  return activity.value.picUrl
})
const isSoldOut = computed(() => Number(activity.value?.stockRemain || 0) <= 0)
const canOpenGroup = computed(() => Boolean(activity.value?.skuId) && !isSoldOut.value)
const activityStatus = computed(() => (isSoldOut.value ? '已售罄' : '正在拼团'))
const primaryActionText = computed(() => {
  if (!activity.value?.skuId) return '活动配置缺失'
  if (isSoldOut.value) return '库存已售罄'
  return '发起拼团'
})

onLoad((options) => {
  activityId.value = options.id
  loadDetail()
})

const loadDetail = async () => {
  loading.value = true
  loadError.value = ''
  imageFailed.value = false
  try {
    const res = await getAppGroupBuyActivityDetail(activityId.value)
    activity.value = res.data
    if (!activity.value) {
      loadError.value = '活动不存在或已下架'
    }
  } catch (error) {
    console.error('load group-buy detail failed', error)
    activity.value = null
    loadError.value = '请确认后端服务已启动，或稍后再试'
  } finally {
    loading.value = false
  }
  loadMyHead()
}

// 已登录时，查询当前用户在本活动下“进行中”的团；命中则展示“已参团”引导
const loadMyHead = async () => {
  myHead.value = null
  if (!sheep.$store('user').isLogin || !activityId.value) {
    return
  }
  try {
    const res = await getMyGroupBuyHeads(0) // status=0 进行中
    const list = res.data || []
    const matched = list.find(
      (item) => String(item.activityId) === String(activityId.value),
    )
    myHead.value = matched || null
  } catch (error) {
    console.error('load my group-buy head failed', error)
  }
}

const goMyHead = () => {
  if (!myHead.value) {
    return
  }
  uni.navigateTo({ url: `/pages/groupbuy/head?id=${myHead.value.id}` })
}

const openGroup = async () => {
  goConfirm()
}

const joinGroup = (headId) => {
  goConfirm(headId)
}

const goConfirm = (headId) => {
  if (!activity.value?.skuId) {
    uni.showToast({ title: '活动 SKU 缺失', icon: 'none' })
    return
  }
  if (isSoldOut.value) {
    uni.showToast({ title: '库存已售罄', icon: 'none' })
    return
  }
  sheep.$router.go('/pages/order/confirm', {
    data: JSON.stringify({
      items: [{ skuId: activity.value.skuId, count: 1 }],
      groupBuyActivityId: activity.value.id,
      groupBuyHeadId: headId
    })
  })
}

const goMy = () => {
  uni.navigateTo({ url: '/pages/groupbuy/mine' })
}

const goChat = () => {
  uni.navigateTo({ url: '/pages/chat/index' })
}

const formatPrice = (price = 0) => ((Number(price) || 0) / 100).toFixed(2)

const formatDuration = (seconds) => {
  const value = Math.max(0, Number(seconds || 0))
  const hours = Math.floor(value / 3600)
  const minutes = Math.floor((value % 3600) / 60)
  if (hours > 0) return `${hours}小时${minutes}分钟`
  if (minutes > 0) return `${minutes}分钟`
  return '不足1分钟'
}

const missingCount = (head) => {
  const userSize = Number(head?.userSize || activity.value?.userSize || 1)
  const currentCount = Number(head?.currentCount || 0)
  return Math.max(userSize - currentCount, 0)
}

const headProgressWidth = (head) => {
  const userSize = Number(head?.userSize || activity.value?.userSize || 1)
  const currentCount = Number(head?.currentCount || 0)
  return `${Math.max(12, Math.min(100, Math.round((currentCount / userSize) * 100)))}%`
}

const leaderInitial = (head) => {
  const name = head?.leaderNickname || '校'
  return String(name).slice(0, 1)
}
</script>

<style>
.page {
  box-sizing: border-box;
  min-height: 100vh;
  background: #fafafa;
  padding-bottom: calc(140rpx + env(safe-area-inset-bottom));
}

.detail-shell {
  max-width: 430px;
  margin: 0 auto;
}

.media-section {
  position: relative;
  overflow: hidden;
  height: 520rpx;
  background: #e4ebe7;
}

.banner {
  width: 100%;
  height: 520rpx;
}

.banner-fallback {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 520rpx;
  color: #6d7a75;
  font-size: 26rpx;
}

.fallback-logo {
  width: 118rpx;
  height: 118rpx;
  margin-bottom: 18rpx;
}

.media-shade {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  height: 160rpx;
  background: linear-gradient(180deg, rgba(0, 0, 0, 0), rgba(13, 34, 27, 0.42));
}

.activity-status {
  position: absolute;
  right: 24rpx;
  bottom: 24rpx;
  padding: 9rpx 18rpx;
  border-radius: 8rpx;
  background: rgba(23, 107, 82, 0.94);
  color: #ffffff;
  font-size: 22rpx;
}

.activity-status.soldout {
  background: rgba(91, 97, 94, 0.9);
}

.summary-section,
.flow-section,
.head-section,
.service-section {
  margin: 18rpx 24rpx 0;
  padding: 24rpx;
  border-radius: 8rpx;
  background: #ffffff;
}

.brand-line {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10rpx;
  color: #f37b1d;
  font-size: 22rpx;
  font-weight: 700;
}

.brand-left {
  display: flex;
  align-items: center;
  gap: 10rpx;
}

.brand-chat {
  flex-shrink: 0;
  padding: 8rpx 18rpx;
  border-radius: 6rpx;
  background: #fff5f0;
  color: #f37b1d;
  font-size: 22rpx;
  font-weight: 600;
}

.brand-logo {
  width: 38rpx;
  height: 38rpx;
}

.activity-name {
  margin-top: 18rpx;
  color: #17251f;
  font-size: 34rpx;
  font-weight: 800;
  line-height: 1.38;
}

.joined-banner {
  margin-top: 20rpx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20rpx 24rpx;
  background: #fff5f0;
  border: 2rpx solid #bfe0cd;
  border-radius: 16rpx;
}
.joined-copy { display: flex; flex-direction: column; }
.joined-title { color: #f37b1d; font-size: 28rpx; font-weight: 700; }
.joined-desc { color: #5c7a6a; font-size: 22rpx; margin-top: 4rpx; }
.joined-arrow { color: #f37b1d; font-size: 26rpx; font-weight: 600; }

.price-panel {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 20rpx;
  margin-top: 24rpx;
  padding: 20rpx;
  border-radius: 8rpx;
  background: #f7faf8;
}

.price-label {
  color: #64756e;
  font-size: 22rpx;
}

.price-row {
  display: flex;
  align-items: baseline;
  gap: 12rpx;
  margin-top: 4rpx;
}

.group-price {
  color: #c73a2d;
  font-size: 46rpx;
  font-weight: 900;
}

.original-price {
  color: #9aa6a1;
  font-size: 24rpx;
  text-decoration: line-through;
}

.stock-box {
  flex-shrink: 0;
  padding: 12rpx 16rpx;
  border-radius: 8rpx;
  background: #e7f3ed;
  color: #f37b1d;
  font-size: 22rpx;
  font-weight: 700;
}

.stock-box.empty {
  background: #eef0ef;
  color: #727a76;
}

.rule-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12rpx;
  margin-top: 20rpx;
}

.rule-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 104rpx;
  border-radius: 8rpx;
  background: #f3f7f5;
}

.rule-value {
  color: #20372e;
  font-size: 28rpx;
  font-weight: 800;
}

.rule-label {
  margin-top: 6rpx;
  color: #6c7974;
  font-size: 21rpx;
}

.section-title {
  display: block;
  color: #17251f;
  font-size: 29rpx;
  font-weight: 800;
}

.section-desc {
  display: block;
  margin-top: 6rpx;
  color: #71817a;
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
  color: #4e6259;
  font-size: 22rpx;
}

.flow-index {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 44rpx;
  height: 44rpx;
  margin-bottom: 10rpx;
  border-radius: 50%;
  background: #f37b1d;
  color: #ffffff;
  font-size: 22rpx;
  font-weight: 700;
}

.flow-line {
  width: 46rpx;
  height: 2rpx;
  margin-bottom: 40rpx;
  background: #d9e5de;
}

.section-head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 20rpx;
}

.head-count {
  flex-shrink: 0;
  padding: 8rpx 12rpx;
  border-radius: 6rpx;
  background: #fff4dc;
  color: #98651b;
  font-size: 21rpx;
}

.head-list {
  display: flex;
  flex-direction: column;
  gap: 16rpx;
  padding-top: 22rpx;
}

.head-item {
  display: flex;
  align-items: center;
  gap: 16rpx;
  padding: 18rpx;
  border: 1rpx solid #e2eae5;
  border-radius: 8rpx;
  background: #fbfdfc;
}

.head-avatar {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 62rpx;
  height: 62rpx;
  border-radius: 50%;
  background: #e6f2ec;
  color: #f37b1d;
  font-size: 26rpx;
  font-weight: 800;
}

.head-main {
  flex: 1;
  min-width: 0;
}

.head-title-row,
.head-meta {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12rpx;
}

.head-title {
  overflow: hidden;
  color: #26372f;
  font-size: 25rpx;
  font-weight: 700;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.head-missing {
  flex-shrink: 0;
  color: #c73a2d;
  font-size: 22rpx;
  font-weight: 700;
}

.head-progress {
  overflow: hidden;
  height: 10rpx;
  margin-top: 14rpx;
  border-radius: 999rpx;
  background: #e7eee9;
}

.head-progress-bar {
  height: 100%;
  border-radius: 999rpx;
  background: linear-gradient(90deg, #f37b1d, #ffd44f);
}

.head-meta {
  margin-top: 10rpx;
  color: #6e7c77;
  font-size: 21rpx;
}

.join-btn {
  flex-shrink: 0;
  width: 108rpx;
  height: 58rpx;
  margin: 0;
  border-radius: 8rpx;
  background: #f37b1d;
  color: #ffffff;
  font-size: 23rpx;
  line-height: 58rpx;
}

.join-btn[disabled] {
  background: #c9d1cd;
  color: #ffffff;
}

.empty-head {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 42rpx 20rpx 20rpx;
  color: #5f6f68;
  font-size: 24rpx;
  text-align: center;
}

.empty-logo {
  width: 82rpx;
  height: 82rpx;
  margin-bottom: 16rpx;
}

.empty-tip {
  margin-top: 8rpx;
  color: #8a9691;
  font-size: 22rpx;
}

.service-grid {
  display: flex;
  flex-direction: column;
  gap: 12rpx;
  padding-top: 18rpx;
}

.service-item {
  display: flex;
  align-items: center;
  gap: 12rpx;
  color: #41564d;
  font-size: 24rpx;
}

.service-mark {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 38rpx;
  height: 38rpx;
  border-radius: 50%;
  background: #e5f2ec;
  color: #f37b1d;
  font-size: 20rpx;
  font-weight: 800;
}

.service-mark.delivery {
  background: #eef3ff;
  color: #345a9c;
}

.service-mark.pay {
  background: #fff4dc;
  color: #9a651c;
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

.action-secondary,
.action-primary {
  height: 82rpx;
  margin: 0;
  border-radius: 8rpx;
  font-size: 28rpx;
  line-height: 82rpx;
}

.action-secondary {
  width: 210rpx;
  background: #ffffff;
  color: #f37b1d;
  border: 1rpx solid #c9ddd4;
}

.action-primary {
  flex: 1;
  background: #f37b1d;
  color: #ffffff;
  font-weight: 800;
}

.action-primary[disabled] {
  background: #c9d1cd;
  color: #ffffff;
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

.state-logo-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 112rpx;
  height: 112rpx;
  border-radius: 8rpx;
  background: #ffffff;
}

.state-logo-wrap.muted {
  background: #f7faf8;
}

.state-logo {
  width: 76rpx;
  height: 76rpx;
}

.state-title {
  margin-top: 24rpx;
  color: #21352d;
  font-size: 30rpx;
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
  height: 64rpx;
  margin-top: 28rpx;
  border-radius: 8rpx;
  background: #f37b1d;
  color: #ffffff;
  font-size: 24rpx;
  line-height: 64rpx;
}

button::after {
  border: none;
}
</style>
