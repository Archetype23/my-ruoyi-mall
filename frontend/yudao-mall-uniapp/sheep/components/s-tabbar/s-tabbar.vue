<template>
  <view class="tabbar-wrap">
    <view class="tabbar-inner">
      <view
        v-for="item in displayItems"
        :key="item.url"
        class="tabbar-item"
        :class="{ active: isActive(item) }"
        :style="{ color: isActive(item) ? activeColor : inactiveColor }"
        @click="onTabClick(item)"
      >
        <text class="tabbar-text">{{ item.text }}</text>
      </view>
    </view>
    <view class="tabbar-safe" />
  </view>
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import { onShow } from '@dcloudio/uni-app';

  const activeColor = '#f37b1d';
  const inactiveColor = '#999999';
  let switching = false;

  const fallbackItems = [
    { text: '拼团', url: '/pages/groupbuy/index' },
    { text: '我的', url: '/pages/index/user' },
    { text: '设置', url: '/pages/public/setting' },
  ];

  const displayItems = fallbackItems;

  // 隐藏原生 tabBar，使用自定义 tabBar 替代
  onMounted(() => {
    uni.hideTabBar({ fail: () => {} });
  });
  onShow(() => {
    uni.hideTabBar({ fail: () => {} });
  });

  function isActive(item) {
    const current = getCurrentPagePath();
    return item.url === current;
  }

  function getCurrentPagePath() {
    const pages = getCurrentPages();
    if (pages.length > 0) {
      const route = '/' + pages[pages.length - 1].route;
      if (route === '/pages/groupbuy/index') return '/pages/groupbuy/index';
      if (route === '/pages/index/user') return '/pages/index/user';
      if (route === '/pages/public/setting') return '/pages/public/setting';
    }
    return '';
  }

  function onTabClick(item) {
    if (switching) return;
    const current = getCurrentPagePath();
    if (item.url === current) return;
    switching = true;
    uni.switchTab({
      url: item.url,
      fail: () => { switching = false; },
      success: () => { switching = false; },
      complete: () => { setTimeout(() => { switching = false; }, 500); }
    });
  }

  const props = defineProps({
    path: String,
    default: '',
  });
</script>

<style lang="scss" scoped>
  .tabbar-wrap {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 100;
    background: #fff;
    box-shadow: 0 -2rpx 12rpx rgba(0,0,0,0.06);
  }
  .tabbar-inner {
    display: flex;
    height: 100rpx;
    align-items: center;
  }
  .tabbar-item {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }
  .tabbar-item.active {
    font-weight: 700;
  }
  .tabbar-text {
    font-size: 26rpx;
  }
  .tabbar-safe {
    height: env(safe-area-inset-bottom);
  }
</style>
