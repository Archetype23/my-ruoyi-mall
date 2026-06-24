<template>
  <s-layout
    title="社区拼团"
    navbar="custom"
    tabbar="/pages/groupbuy/index"
    :bgStyle="{ backgroundColor: '#fafafa' }"
    :navbarStyle="{ title: '社区拼团', style: { backgroundColor: '#ffffff' } }"
  >
    <view class="page">
      <!-- 未登录提示 -->
      <view v-if="!isLogin" class="login-hint">
        <image class="hint-logo" src="/static/logo.png" mode="aspectFit" />
        <text class="hint-title">目前尚未登录</text>
        <text class="hint-desc">请前往「设置」界面登录</text>
        <button class="hint-btn" @click="goSetting">去设置</button>
      </view>

      <!-- 已登录：拼团列表 -->
      <template v-else>
        <view class="section-head">
          <view>
            <text class="section-title">今日拼团</text>
            <text class="section-subtitle">成团后商家按订单发货</text>
          </view>
          <button class="refresh-btn" :disabled="loading" @click="loadList">刷新</button>
        </view>

        <view v-if="loading" class="state-panel">
          <image class="state-logo" src="/static/logo.png" mode="aspectFit" />
          <text class="state-title">正在加载今日拼团</text>
        </view>

        <view v-else-if="list.length === 0" class="state-panel">
          <image class="state-logo muted" src="/static/logo.png" mode="aspectFit" />
          <text class="state-title">暂无可参团活动</text>
          <text class="state-desc">商家上架后会第一时间展示在这里</text>
        </view>

        <view v-else class="list">
          <view v-for="item in list" :key="item.id" class="card" @click="goDetail(item.id)">
            <view class="media">
              <image
                :src="getImage(item)"
                class="img"
                mode="aspectFill"
                @error="markImageError(item.id)"
              />
              <text class="status-badge" :class="{ soldout: isSoldOut(item) }">
                {{ isSoldOut(item) ? '已抢完' : '可参团' }}
              </text>
            </view>
            <view class="info">
              <view class="name">{{ item.name }}</view>
              <view class="tags">
                <text>{{ item.userSize || 0 }} 人成团</text>
                <text v-if="item.activeHeadCount">{{ item.activeHeadCount }} 个团在拼</text>
              </view>
              <view class="price-row">
                <text class="group-price">¥{{ formatPrice(item.groupPrice) }}</text>
                <text v-if="item.originalPrice" class="original-price">¥{{ formatPrice(item.originalPrice) }}</text>
                <text class="stock">剩 {{ item.stockRemain ?? 0 }} 件</text>
              </view>
              <button class="card-cta" :disabled="isSoldOut(item)" @click.stop="goDetail(item.id)">
                {{ isSoldOut(item) ? '已售罄' : '去拼团' }}
              </button>
            </view>
          </view>
        </view>
      </template>
    </view>
  </s-layout>
</template>

<script setup>
import { ref, computed } from 'vue'
import { onShow, onPullDownRefresh } from '@dcloudio/uni-app'
import sheep from '@/sheep'
import { getAppGroupBuyActivityList } from '@/sheep/api/promotion/groupbuy'

const isLogin = computed(() => sheep.$store('user').isLogin)
const loading = ref(true)
const list = ref([])
const imageErrors = ref({})

const formatPrice = (price = 0) => ((Number(price) || 0) / 100).toFixed(2)
const isSoldOut = (item) => (item?.stockRemain ?? 0) <= 0
const getImage = (item) => {
  if (!item?.picUrl || imageErrors.value[item.id]) return '/static/logo.png'
  return item.picUrl
}
const markImageError = (id) => {
  imageErrors.value = { ...imageErrors.value, [id]: true }
}

const goSetting = () => {
  uni.switchTab({ url: '/pages/public/setting' })
}

const loadList = async () => {
  loading.value = true
  try {
    const res = await getAppGroupBuyActivityList()
    list.value = res.data || []
  } catch (e) {
    console.error('load groupbuy list failed', e)
  } finally {
    loading.value = false
  }
}

const goDetail = (id) => {
  uni.navigateTo({ url: `/pages/groupbuy/detail?id=${id}` })
}

onShow(() => {
  if (isLogin.value) loadList()
})
onPullDownRefresh(async () => {
  if (isLogin.value) await loadList()
  uni.stopPullDownRefresh()
})
</script>

<style lang="scss" scoped>
.page { padding: 24rpx; }

/* 未登录提示 */
.login-hint {
  display: flex; flex-direction: column; align-items: center; padding: 120rpx 0; gap: 16rpx;
}
.hint-logo { width: 120rpx; height: 120rpx; opacity: 0.6; }
.hint-title { font-size: 32rpx; color: #333; font-weight: 600; }
.hint-desc { font-size: 26rpx; color: #999; }
.hint-btn {
  margin-top: 20rpx; width: 240rpx; height: 72rpx; line-height: 72rpx;
  background: #f37b1d; color: #fff; font-size: 28rpx; border-radius: 36rpx; border: none;
}
.hint-btn::after { border: none; }

.section-head {
  display: flex; align-items: center; justify-content: space-between; margin-bottom: 20rpx;
}
.section-title { font-size: 36rpx; font-weight: 700; color: #333; }
.section-subtitle { display: block; font-size: 24rpx; color: #999; margin-top: 4rpx; }
.refresh-btn {
  margin: 0; padding: 0 28rpx; height: 56rpx; line-height: 56rpx; font-size: 24rpx;
  color: #f37b1d; background: #fff5f0; border-radius: 28rpx;
}
.refresh-btn::after { border: none; }

.state-panel {
  display: flex; flex-direction: column; align-items: center; padding: 80rpx 0; gap: 12rpx;
}
.state-logo { width: 120rpx; height: 120rpx; }
.state-logo.muted { opacity: 0.5; }
.state-title { font-size: 30rpx; color: #333; font-weight: 600; }
.state-desc { font-size: 24rpx; color: #999; }

.list { display: flex; flex-direction: column; gap: 24rpx; }
.card {
  display: flex; background: #fff; border-radius: 20rpx; padding: 20rpx;
  box-shadow: 0 6rpx 20rpx rgba(243, 123, 29, 0.08);
}
.media { position: relative; flex-shrink: 0; }
.img { width: 200rpx; height: 200rpx; border-radius: 12rpx; background: #fafafa; }
.status-badge {
  position: absolute; left: 10rpx; top: 10rpx; font-size: 20rpx; color: #fff;
  background: #f37b1d; padding: 2rpx 12rpx; border-radius: 16rpx;
}
.status-badge.soldout { background: #ccc; }
.info { flex: 1; margin-left: 20rpx; display: flex; flex-direction: column; justify-content: space-between; }
.name { font-size: 28rpx; font-weight: 600; line-height: 1.4; color: #333; }
.tags { display: flex; gap: 12rpx; margin: 8rpx 0; }
.tags text { font-size: 20rpx; color: #999; background: #fafafa; padding: 2rpx 12rpx; border-radius: 8rpx; }
.price-row { display: flex; align-items: baseline; gap: 12rpx; flex-wrap: wrap; }
.group-price { color: #ff4d4f; font-size: 36rpx; font-weight: bold; }
.original-price { color: #999; font-size: 24rpx; text-decoration: line-through; }
.stock { font-size: 22rpx; color: #999; margin-left: auto; }
.card-cta {
  margin: 12rpx 0 0; height: 60rpx; line-height: 60rpx; font-size: 26rpx;
  color: #fff; background: #f37b1d; border-radius: 30rpx;
}
.card-cta[disabled] { background: #ddd; }
.card-cta::after { border: none; }
</style>
