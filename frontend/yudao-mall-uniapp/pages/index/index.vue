<!-- 首页（重定向到拼团列表） -->
<template>
  <s-layout title="首页" navbar="custom" :bgStyle="{ backgroundColor: '#fafafa' }">
    <view class="redirect-page">
      <text>正在跳转...</text>
    </view>
  </s-layout>
</template>

<script setup>
  import { onLoad } from '@dcloudio/uni-app';
  onLoad(() => {
    uni.switchTab({ url: '/pages/groupbuy/index' });
  });
</script>

<style lang="scss" scoped>
  .redirect-page {
    display: flex; align-items: center; justify-content: center;
    min-height: 60vh; color: #999;
  }
</style>
