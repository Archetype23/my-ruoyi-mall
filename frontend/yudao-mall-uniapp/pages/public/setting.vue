<template>
  <s-layout
    title="设置"
    tabbar="/pages/public/setting"
    navbar="custom"
    :bgStyle="{ backgroundColor: '#fafafa' }"
    :navbarStyle="{ title: '设置', style: { backgroundColor: '#ffffff' } }"
  >
    <view class="setting-page">
      <!-- 头部 -->
      <view class="header-box">
        <image class="logo-img" src="/static/logo.png" mode="aspectFit" />
        <text class="app-name">西北师大社区团购</text>
      </view>

      <!-- 未登录：登录/注册入口 -->
      <view v-if="!isLogin" class="auth-section">
        <button class="login-btn" @click="showLogin">登录</button>
        <view class="register-link" @click="goRegister">
          <text>没有账号？</text>
          <text class="register-text">去注册</text>
        </view>
      </view>

      <!-- 已登录：用户信息 + 退出登录 -->
      <view v-else class="user-section">
        <view class="user-card">
          <image class="user-avatar" :src="userInfo.avatar || '/static/logo.png'" mode="aspectFill" />
          <view class="user-detail">
            <text class="user-name">{{ userInfo.nickname || '用户' }}</text>
            <text class="user-mobile">{{ userInfo.mobile || '' }}</text>
          </view>
        </view>
      </view>

      <!-- 通用设置列表 -->
      <view class="list-section">
        <view class="list-item" @click="onCheckUpdate">
          <text class="item-title">当前版本</text>
          <text class="item-value">{{ appInfo.version }}</text>
        </view>
        <view class="list-item">
          <text class="item-title">本地缓存</text>
          <text class="item-value">{{ storageSize }}</text>
        </view>
        <view class="list-item" @click="goAbout">
          <text class="item-title">关于我们</text>
          <text class="item-arrow">›</text>
        </view>
        <view class="list-item" @click="goAgreement('用户协议')">
          <text class="item-title">用户协议</text>
          <text class="item-arrow">›</text>
        </view>
        <view class="list-item" @click="goAgreement('隐私协议')">
          <text class="item-title">隐私协议</text>
          <text class="item-arrow">›</text>
        </view>
      </view>

      <!-- 退出登录按钮 -->
      <view v-if="isLogin" class="logout-section">
        <button class="logout-btn" @click="onLogout">退出登录</button>
      </view>

      <!-- 版权信息 -->
      <view class="footer">
        <text class="copyright">© 2026 西北师大社区团购</text>
      </view>
    </view>
  </s-layout>
</template>

<script setup>
  import sheep from '@/sheep';
  import { computed } from 'vue';
  import AuthUtil from '@/sheep/api/member/auth';
  import { showAuthModal } from '@/sheep/hooks/useModal';

  const appInfo = computed(() => sheep.$store('app').info);
  const isLogin = computed(() => sheep.$store('user').isLogin);
  const userInfo = computed(() => sheep.$store('user').userInfo);
  const storageSize = uni.getStorageInfoSync().currentSize + 'Kb';

  function showLogin() {
    showAuthModal('accountLogin');
  }

  function goRegister() {
    sheep.$router.go('/pages/index/register');
  }

  function onCheckUpdate() {
    sheep.$platform.checkUpdate();
  }

  function goAbout() {
    sheep.$router.go('/pages/public/richtext', { title: '关于我们' });
  }

  function goAgreement(title) {
    sheep.$router.go('/pages/public/richtext', { title });
  }

  function onLogout() {
    uni.showModal({
      title: '提示',
      content: '确认退出账号？',
      success: async (res) => {
        if (!res.confirm) return;
        const { code } = await AuthUtil.logout();
        if (code !== 0) return;
        sheep.$store('user').logout();
        uni.reLaunch({ url: '/pages/groupbuy/index' });
      },
    });
  }
</script>

<style lang="scss" scoped>
  .setting-page {
    min-height: 100vh;
  }

  .header-box {
    display: flex; flex-direction: column; align-items: center;
    padding: 60rpx 0 40rpx;
  }
  .logo-img {
    width: 120rpx; height: 120rpx; border-radius: 24rpx;
  }
  .app-name {
    font-size: 34rpx; font-weight: 700; color: #333; margin-top: 16rpx;
  }

  /* 未登录 */
  .auth-section {
    display: flex; flex-direction: column; align-items: center;
    padding: 20rpx 32rpx 40rpx; gap: 24rpx;
  }
  .login-btn {
    width: 100%; height: 88rpx; line-height: 88rpx;
    background: #f37b1d; color: #fff; font-size: 32rpx;
    border-radius: 44rpx; border: none;
  }
  .login-btn::after { border: none; }
  .register-link {
    display: flex; align-items: center; font-size: 26rpx; color: #999;
  }
  .register-text {
    color: #f37b1d; margin-left: 4rpx;
  }

  /* 已登录用户卡片 */
  .user-section {
    padding: 0 32rpx 20rpx;
  }
  .user-card {
    display: flex; align-items: center; gap: 24rpx;
    background: #fff; border-radius: 20rpx; padding: 32rpx;
  }
  .user-avatar {
    width: 96rpx; height: 96rpx; border-radius: 50%; flex: none;
    background: #fafafa;
  }
  .user-detail { flex: 1; }
  .user-name {
    display: block; font-size: 32rpx; font-weight: 700; color: #333;
  }
  .user-mobile {
    display: block; font-size: 26rpx; color: #999; margin-top: 6rpx;
  }

  /* 设置列表 */
  .list-section {
    margin: 0 32rpx; background: #fff; border-radius: 20rpx; overflow: hidden;
  }
  .list-item {
    display: flex; align-items: center; justify-content: space-between;
    padding: 28rpx 32rpx; border-bottom: 1rpx solid #f5f5f5;
  }
  .list-item:last-child { border-bottom: none; }
  .item-title { font-size: 28rpx; color: #333; }
  .item-value { font-size: 26rpx; color: #bbb; }
  .item-arrow { font-size: 32rpx; color: #ccc; }

  /* 退出登录 */
  .logout-section {
    padding: 40rpx 32rpx;
  }
  .logout-btn {
    width: 100%; height: 88rpx; line-height: 88rpx;
    background: #fff; color: #ff4d4f; font-size: 32rpx;
    border-radius: 44rpx; border: 1rpx solid #ffd6d6;
  }
  .logout-btn::after { border: none; }

  /* 版权 */
  .footer {
    text-align: center; padding: 40rpx 0 60rpx;
  }
  .copyright {
    font-size: 22rpx; color: #ccc;
  }
</style>
