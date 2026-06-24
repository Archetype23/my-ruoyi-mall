<!-- 注册页面 -->
<template>
  <s-layout title="注册" navbar="normal" :bgStyle="{ backgroundColor: '#eef3f0' }">
    <view class="register-page">
      <view class="register-header">
        <image class="register-logo" src="/static/logo.png" mode="aspectFit" />
        <text class="register-title">西北师大社区团购</text>
        <text class="register-subtitle">注册新账号</text>
      </view>

      <view class="register-form">
        <view class="form-item">
          <text class="form-label">手机号</text>
          <input
            class="form-input"
            type="number"
            maxlength="11"
            placeholder="请输入手机号"
            v-model="form.mobile"
          />
        </view>
        <view class="form-item">
          <text class="form-label">密码</text>
          <input
            class="form-input"
            :password="true"
            placeholder="4-16 位密码"
            v-model="form.password"
          />
        </view>
        <view class="form-item">
          <text class="form-label">确认密码</text>
          <input
            class="form-input"
            :password="true"
            placeholder="请再次输入密码"
            v-model="form.confirmPassword"
          />
        </view>

        <button class="register-btn" :disabled="loading" @tap="onRegister">
          {{ loading ? '注册中...' : '注 册' }}
        </button>

        <view class="register-footer">
          <text class="footer-text">已有账号？</text>
          <text class="footer-link" @tap="goLogin">去登录</text>
        </view>
      </view>
    </view>
  </s-layout>
</template>

<script setup>
  import { reactive, ref } from 'vue';
  import sheep from '@/sheep';
  import AuthUtil from '@/sheep/api/member/auth';
  import { showAuthModal } from '@/sheep/hooks/useModal';

  const form = reactive({
    mobile: '',
    password: '',
    confirmPassword: '',
  });
  const loading = ref(false);

  function goLogin() {
    showAuthModal('accountLogin');
    sheep.$router.back();
  }

  async function onRegister() {
    if (!form.mobile || form.mobile.length !== 11) {
      sheep.$helper.toast('请输入正确的手机号');
      return;
    }
    if (!form.password || form.password.length < 4) {
      sheep.$helper.toast('密码至少 4 位');
      return;
    }
    if (form.password !== form.confirmPassword) {
      sheep.$helper.toast('两次密码不一致');
      return;
    }

    loading.value = true;
    try {
      const res = await AuthUtil.register({
        mobile: form.mobile,
        password: form.password,
      });
      if (res?.data?.accessToken) {
        sheep.$store('user').setToken(res.data.accessToken, res.data.refreshToken);
        await sheep.$store('user').loginAfter();
        sheep.$router.go('/pages/index/index');
      }
    } catch (e) {
      // error toast handled by request interceptor
    } finally {
      loading.value = false;
    }
  }
</script>

<style lang="scss" scoped>
  .register-page {
    min-height: 100vh;
    padding: 0 48rpx;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .register-header {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 80rpx;
    padding-bottom: 60rpx;

    .register-logo {
      width: 120rpx;
      height: 120rpx;
      border-radius: 24rpx;
      margin-bottom: 24rpx;
    }

    .register-title {
      font-size: 38rpx;
      font-weight: bold;
      color: #f37b1d;
    }

    .register-subtitle {
      font-size: 28rpx;
      color: #999;
      margin-top: 8rpx;
    }
  }

  .register-form {
    width: 100%;
    background: #fff;
    border-radius: 24rpx;
    padding: 40rpx 32rpx;

    .form-item {
      margin-bottom: 32rpx;

      .form-label {
        display: block;
        font-size: 28rpx;
        color: #333;
        margin-bottom: 12rpx;
        font-weight: 500;
      }

      .form-input {
        width: 100%;
        height: 88rpx;
        border: 2rpx solid #e8e8e8;
        border-radius: 12rpx;
        padding: 0 24rpx;
        font-size: 30rpx;
        box-sizing: border-box;
      }
    }

    .register-btn {
      width: 100%;
      height: 88rpx;
      line-height: 88rpx;
      background: #f37b1d;
      color: #fff;
      border-radius: 12rpx;
      font-size: 32rpx;
      font-weight: 500;
      margin-top: 16rpx;
      border: none;

      &[disabled] {
        opacity: 0.6;
      }
    }
  }

  .register-footer {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 40rpx;

    .footer-text {
      font-size: 28rpx;
      color: #999;
    }

    .footer-link {
      font-size: 28rpx;
      color: #f37b1d;
      margin-left: 8rpx;
    }
  }
</style>
