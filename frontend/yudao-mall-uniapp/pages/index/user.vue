<template>
  <s-layout
    title="我的"
    tabbar="/pages/index/user"
    navbar="custom"
    :bgStyle="{ backgroundColor: '#fafafa' }"
    :navbarStyle="{ title: '我的', style: { backgroundColor: '#ffffff' } }"
  >
    <!-- 未登录提示 -->
    <view v-if="!isLogin" class="login-hint">
      <image class="hint-logo" src="/static/logo.png" mode="aspectFit" />
      <text class="hint-title">目前尚未登录</text>
      <text class="hint-desc">请前往「设置」界面登录</text>
      <button class="hint-btn" @click="goSetting">去设置</button>
    </view>

    <!-- 已登录：个人中心 -->
    <view v-else class="user-page">
      <!-- 用户信息 -->
      <view class="user-header">
        <image class="user-avatar" :src="userInfo.avatar || '/static/logo.png'" mode="aspectFill" />
        <view class="user-info">
          <text class="user-name">{{ userInfo.nickname || '用户' }}</text>
          <text class="user-mobile">{{ userInfo.mobile || '' }}</text>
        </view>
      </view>

      <!-- 功能入口 -->
      <view class="menu-grid">
        <view class="menu-item" @click="goGroupBuyMine">
          <text class="menu-icon">🔥</text>
          <text class="menu-title">我的拼团</text>
          <text class="menu-desc">当前进度</text>
        </view>
        <view class="menu-item" @click="goOrderList(0)">
          <text class="menu-icon">📋</text>
          <text class="menu-title">全部订单</text>
          <text class="menu-desc">历史记录</text>
        </view>
        <view class="menu-item" @click="goOrderList(2)">
          <text class="menu-icon">📦</text>
          <text class="menu-title">待发货</text>
          <text class="menu-desc">成团履约</text>
        </view>
        <view class="menu-item" @click="goAfterSale">
          <text class="menu-icon">↩</text>
          <text class="menu-title">售后服务</text>
          <text class="menu-desc">退款进度</text>
        </view>
      </view>
    </view>
  </s-layout>
</template>

<script setup>
  import { computed } from 'vue';
  import { onShow, onPullDownRefresh } from '@dcloudio/uni-app';
  import sheep from '@/sheep';

  const isLogin = computed(() => sheep.$store('user').isLogin);
  const userInfo = computed(() => sheep.$store('user').userInfo);

  onShow(() => {
    if (sheep.$store('user').isLogin) {
      sheep.$store('user').updateUserData();
    }
  });

  onPullDownRefresh(() => {
    if (sheep.$store('user').isLogin) {
      sheep.$store('user').updateUserData();
    }
    setTimeout(() => uni.stopPullDownRefresh(), 800);
  });

  const goSetting = () => {
    uni.switchTab({ url: '/pages/public/setting' });
  };

  const goGroupBuyMine = () => {
    sheep.$router.go('/pages/groupbuy/mine');
  };

  const goOrderList = (type) => {
    sheep.$router.go('/pages/order/list', { type });
  };

  const goAfterSale = () => {
    sheep.$router.go('/pages/order/aftersale/list');
  };
</script>

<style lang="scss" scoped>
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

  .user-page {
    min-height: 100vh;
  }

  .user-header {
    display: flex; align-items: center; gap: 24rpx;
    padding: 40rpx 32rpx; background: #fff;
    border-bottom: 1rpx solid #f0f0f0;
  }
  .user-avatar {
    width: 120rpx; height: 120rpx; border-radius: 50%; flex: none;
    background: #fafafa;
  }
  .user-info { flex: 1; min-width: 0; }
  .user-name {
    display: block; font-size: 34rpx; font-weight: 700; color: #333;
  }
  .user-mobile {
    display: block; font-size: 26rpx; color: #999; margin-top: 8rpx;
  }

  .menu-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 2rpx;
    background: #f0f0f0;
    margin: 24rpx 0;
  }
  .menu-item {
    display: flex; flex-direction: column; align-items: center;
    padding: 32rpx 16rpx; background: #fff;
    gap: 8rpx;
  }
  .menu-icon {
    font-size: 48rpx; line-height: 1;
  }
  .menu-title {
    font-size: 26rpx; color: #333; font-weight: 600;
  }
  .menu-desc {
    font-size: 22rpx; color: #999;
  }
</style>
