<!-- 全局 - 快捷入口 -->
<template>
  <su-popup :show="show" type="top" round="20" backgroundColor="#F0F0F0" @close="closeMenuTools">
    <su-status-bar />
    <view class="tools-wrap ss-m-x-30 ss-m-b-16">
      <view class="title ss-m-b-34 ss-p-t-20">快捷菜单</view>
      <view class="container-list ss-flex ss-flex-wrap">
        <view class="list-item ss-m-b-24" v-for="item in list" :key="item.title">
          <view class="ss-flex-col ss-col-center">
            <button
              class="ss-reset-button list-image ss-flex ss-row-center ss-col-center"
              @tap="onClick(item)"
            >
              <text class="list-emoji">{{ item.icon }}</text>
            </button>
            <view class="list-title ss-m-t-20">{{ item.title }}</view>
          </view>
        </view>
      </view>
    </view>
  </su-popup>
</template>

<script setup>
  import { computed } from 'vue';
  import sheep from '@/sheep';
  import { closeMenuTools } from '@/sheep/hooks/useModal';

  const show = computed(() => sheep.$store('modal').menu);

  function onClick(item) {
    closeMenuTools();
    if (item.url) sheep.$router.go(item.url);
  }

  const list = computed(() => {
    const items = [
      { url: '/pages/groupbuy/index', title: '拼团', icon: '🔥' },
      { url: '/pages/groupbuy/mine', title: '我的拼团', icon: '👥' },
      { url: '/pages/order/list?type=0', title: '我的订单', icon: '📋' },
      { url: '/pages/index/user', title: '我的', icon: '👤' },
      { url: '/pages/public/setting', title: '设置', icon: '⚙️' },
    ];
    return items;
  });
</script>

<style lang="scss" scoped>
  .tools-wrap {
    // background: #F0F0F0;
    // box-shadow: 0px 0px 28rpx 7rpx rgba(0, 0, 0, 0.13);
    // opacity: 0.98;
    // border-radius: 0 0 20rpx 20rpx;

    .title {
      font-size: 36rpx;
      font-weight: bold;
      color: #333333;
    }

    .list-item {
      width: calc(25vw - 20rpx);

      .list-image {
        width: 104rpx;
        height: 104rpx;
        border-radius: 52rpx;
        background: var(--ui-BG);

        .list-icon {
          width: 54rpx;
          height: 54rpx;
        }

        .list-emoji {
          font-size: 40rpx;
          line-height: 1;
        }
      }

      .list-title {
        font-size: 26rpx;
        font-weight: 500;
        color: #333333;
      }
    }
  }

  .uni-popup {
    top: 0 !important;
  }

  :deep(.button-hover) {
    background: #fafafa !important;
  }
</style>
