<!-- 拼团进度弹窗 -->
<template>
  <su-popup :show="show" type="center" round="20" @close="close">
    <view class="progress-popup" v-if="head">
      <view class="popup-header">
        <text class="popup-title">拼团进度</text>
        <text class="popup-close" @tap="close">✕</text>
      </view>

      <view class="popup-body">
        <!-- 状态 -->
        <view class="status-row">
          <text class="status-badge" :class="statusClass">{{ statusText }}</text>
          <text class="status-desc">{{ statusDesc }}</text>
        </view>

        <!-- 进度条 -->
        <view class="progress-section">
          <view class="progress-bar">
            <view class="progress-fill" :style="{ width: progressWidth }"></view>
          </view>
          <view class="progress-meta">
            <text>{{ head.currentCount || 0 }}/{{ head.userSize || 0 }} 人</text>
            <text v-if="head.status === 0" class="countdown">{{ countdownText }}</text>
          </view>
        </view>

        <!-- 成员列表 -->
        <view class="members-section">
          <text class="members-title">团员列表</text>
          <view class="member-list">
            <view v-for="member in members" :key="member.id" class="member-item">
              <view class="member-avatar">
                <text>{{ memberInitial(member) }}</text>
              </view>
              <view class="member-info">
                <text class="member-name">{{ member.nickname || '用户' }}</text>
                <text class="member-role" v-if="member.isLeader">团长</text>
              </view>
              <text class="member-status" :class="{ paid: member.payStatus }">
                {{ member.payStatus ? '已付款' : '待付款' }}
              </text>
            </view>
          </view>
        </view>

        <view class="chat-btn-row">
          <button class="chat-btn" @tap="goChat">联系客服</button>
        </view>
      </view>
    </view>

    <view class="progress-popup loading" v-else>
      <view class="popup-header">
        <text class="popup-title">拼团进度</text>
        <text class="popup-close" @tap="close">✕</text>
      </view>
      <view class="popup-loading">
        <text>加载中...</text>
      </view>
    </view>
  </su-popup>
</template>

<script setup>
  import { ref, computed, watch } from 'vue';
  import sheep from '@/sheep';
  import { getGroupBuyHeadDetail } from '@/sheep/api/promotion/groupbuy';

  const props = defineProps({
    show: { type: Boolean, default: false },
    headId: { type: Number, default: 0 },
  });
  const emit = defineEmits(['close']);

  const head = ref(null);
  const members = ref([]);

  const close = () => {
    emit('close');
  };

  const goChat = () => {
    emit('close');
    uni.navigateTo({ url: '/pages/chat/index' });
  };

  watch(() => props.show, async (val) => {
    if (val && props.headId) {
      head.value = null;
      try {
        const res = await getGroupBuyHeadDetail(props.headId);
        head.value = res.data;
        members.value = res.data?.members || [];
      } catch (e) {
        console.error('load head detail failed', e);
      }
    }
  });

  const statusText = computed(() => {
    if (!head.value) return '';
    const s = head.value.status;
    if (s === 0) return '拼团中';
    if (s === 1) return '已成团';
    if (s === 2) return '拼团失败';
    return '';
  });

  const statusClass = computed(() => {
    if (!head.value) return '';
    const s = head.value.status;
    if (s === 0) return 'running';
    if (s === 1) return 'success';
    if (s === 2) return 'failed';
    return '';
  });

  const statusDesc = computed(() => {
    if (!head.value) return '';
    const s = head.value.status;
    const missing = (head.value.userSize || 0) - (head.value.currentCount || 0);
    if (s === 0) return `还差 ${Math.max(missing, 0)} 人成团`;
    if (s === 1) return '拼团成功，等待商家发货';
    if (s === 2) return '拼团失败，已退款';
    return '';
  });

  const progressWidth = computed(() => {
    if (!head.value) return '0%';
    const total = head.value.userSize || 1;
    const current = head.value.currentCount || 0;
    return `${Math.min(100, Math.round((current / total) * 100))}%`;
  });

  const countdownText = computed(() => {
    if (!head.value || head.value.status !== 0) return '';
    const expire = head.value.expireTime;
    if (!expire) return '';
    const now = Date.now();
    const diff = expire - now;
    if (diff <= 0) return '即将过期';
    const hours = Math.floor(diff / 3600000);
    const minutes = Math.floor((diff % 3600000) / 60000);
    if (hours > 0) return `剩余 ${hours}小时${minutes}分钟`;
    return `剩余 ${minutes}分钟`;
  });

  const memberInitial = (m) => {
    const name = m.nickname || '用';
    return String(name).slice(0, 1);
  };
</script>

<style lang="scss" scoped>
  .progress-popup {
    width: 640rpx;
    background: #fff;
    border-radius: 20rpx;
    overflow: hidden;

    .popup-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 32rpx;
      border-bottom: 1rpx solid #f0f0f0;
    }
    .popup-title {
      font-size: 32rpx;
      font-weight: 700;
      color: #333;
    }
    .popup-close {
      font-size: 36rpx;
      color: #999;
      padding: 0 8rpx;
    }

    .popup-body {
      padding: 24rpx 32rpx 32rpx;
    }
  }

  .status-row {
    display: flex;
    align-items: center;
    gap: 16rpx;
    margin-bottom: 24rpx;
  }
  .status-badge {
    padding: 6rpx 16rpx;
    border-radius: 8rpx;
    font-size: 24rpx;
    font-weight: 700;
  }
  .status-badge.running { background: #fff5f0; color: #f37b1d; }
  .status-badge.success { background: #e8f7ee; color: #52c41a; }
  .status-badge.failed { background: #fff0f0; color: #ff4d4f; }
  .status-desc {
    font-size: 26rpx;
    color: #666;
  }

  .progress-section {
    margin-bottom: 24rpx;
  }
  .progress-bar {
    height: 16rpx;
    background: #f0f0f0;
    border-radius: 8rpx;
    overflow: hidden;
  }
  .progress-fill {
    height: 100%;
    background: linear-gradient(90deg, #f37b1d, #ffd44f);
    border-radius: 8rpx;
    transition: width 0.3s;
  }
  .progress-meta {
    display: flex;
    justify-content: space-between;
    margin-top: 12rpx;
    font-size: 24rpx;
    color: #999;
  }
  .countdown {
    color: #f37b1d;
  }

  .members-section {
    .members-title {
      display: block;
      font-size: 28rpx;
      font-weight: 600;
      color: #333;
      margin-bottom: 16rpx;
    }
  }
  .member-list {
    max-height: 400rpx;
    overflow-y: auto;
  }
  .member-item {
    display: flex;
    align-items: center;
    gap: 16rpx;
    padding: 16rpx 0;
    border-bottom: 1rpx solid #f5f5f5;
  }
  .member-item:last-child {
    border-bottom: none;
  }
  .member-avatar {
    width: 56rpx;
    height: 56rpx;
    border-radius: 50%;
    background: #fff5f0;
    color: #f37b1d;
    font-size: 24rpx;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
  }
  .member-info {
    flex: 1;
  }
  .member-name {
    font-size: 26rpx;
    color: #333;
  }
  .member-role {
    margin-left: 8rpx;
    font-size: 20rpx;
    color: #f37b1d;
    background: #fff5f0;
    padding: 2rpx 8rpx;
    border-radius: 4rpx;
  }
  .member-status {
    font-size: 24rpx;
    color: #999;
  }
  .member-status.paid {
    color: #52c41a;
  }

  .popup-loading {
    padding: 80rpx 0;
    text-align: center;
    color: #999;
  }

  .chat-btn-row {
    margin-top: 24rpx;
    text-align: center;
  }
  .chat-btn {
    width: 100%;
    height: 72rpx;
    line-height: 72rpx;
    background: #f37b1d;
    color: #fff;
    border-radius: 36rpx;
    font-size: 28rpx;
    border: none;
  }
  .chat-btn::after { border: none; }
</style>
