<!-- 订单详情 -->
<template>
  <s-layout title="订单详情" class="index-wrap" navbar="custom" :showLeftButton="true">
    <view
      class="state-box ss-flex-col ss-col-center ss-row-right"
    >
      <view class="ss-flex ss-m-t-32 ss-m-b-20">
        <!-- 待支付 -->
        <image
          v-if="state.orderInfo.status === 0"
          class="state-img"
          :src="sheep.$url.static('/static/img/shop/order/no_pay.png')"
        />
        <!-- 待发货 -->
        <image
          v-if="state.orderInfo.status === 10"
          class="state-img"
          :src="sheep.$url.static('/static/img/shop/order/order_loading.png')"
        />
        <!-- 已完成 -->
        <image
          v-else-if="state.orderInfo.status === 30"
          class="state-img"
          :src="sheep.$url.static('/static/img/shop/order/order_success.png')"
        />
        <!-- 已关闭 -->
        <image
          v-else-if="state.orderInfo.status === 40"
          class="state-img"
          :src="sheep.$url.static('/static/img/shop/order/order_close.png')"
        />
        <!-- 已发货 -->
        <image
          v-else-if="state.orderInfo.status === 20"
          class="state-img"
          :src="sheep.$url.static('/static/img/shop/order/order_express.png')"
        />
        <view class="ss-font-30">{{ formatOrderStatus(state.orderInfo) }}</view>
      </view>
      <view class="ss-font-26 ss-m-x-20 ss-m-b-70">
        {{ formatOrderStatusDescription(state.orderInfo) }}
      </view>
    </view>

    <view v-if="isGroupBuyOrder" class="group-buy-card">
      <view class="group-buy-head">
        <view class="group-buy-brand">
          <image class="group-buy-logo" src="/static/logo.png" mode="aspectFit" />
          <view>
            <text class="group-buy-eyebrow">西北师大社区拼团</text>
            <text class="group-buy-title">{{ groupBuyTitle }}</text>
          </view>
        </view>
        <text class="group-buy-status">{{ groupBuyOrderStatus }}</text>
      </view>
      <view class="group-buy-desc">{{ groupBuyDesc }}</view>
      <view class="group-buy-flow">
        <view class="group-buy-step active">
          <text class="step-dot">1</text>
          <text>参团付款</text>
        </view>
        <view class="step-line"></view>
        <view class="group-buy-step" :class="{ active: state.orderInfo.status >= 10 }">
          <text class="step-dot">2</text>
          <text>等待成团</text>
        </view>
        <view class="step-line"></view>
        <view class="group-buy-step" :class="{ active: state.orderInfo.status >= 20 }">
          <text class="step-dot">3</text>
          <text>发货收货</text>
        </view>
      </view>
      <view class="group-buy-actions">
        <button class="group-buy-action ghost ss-reset-button" @tap="onGroupBuyProgress">
          拼团进度
        </button>
        <button class="group-buy-action ghost ss-reset-button" @tap="goChat">
          联系客服
        </button>
      </view>
    </view>

    <!-- 收货地址 -->
    <view
      class="order-address-box"
      :class="{ 'after-group-buy': isGroupBuyOrder }"
      v-if="state.orderInfo.receiverAreaId > 0"
    >
      <view class="ss-flex ss-col-center">
        <text class="address-username">
          {{ state.orderInfo.receiverName }}
        </text>
        <text class="address-phone">{{ state.orderInfo.receiverMobile }}</text>
      </view>
      <view class="address-detail">
        {{ state.orderInfo.receiverAreaName }} {{ state.orderInfo.receiverDetailAddress }}
      </view>
    </view>

    <view
      class="detail-goods"
      :style="[{ marginTop: state.orderInfo.receiverAreaId > 0 ? '0' : '-40rpx' }]"
    >
      <!-- 订单信 -->
      <view class="order-list" v-for="item in state.orderInfo.items" :key="item.goods_id">
        <view class="order-card">
          <s-goods-item
            @tap="onGoodsDetail(item.spuId)"
            :img="item.picUrl"
            :title="item.spuName"
            :skuText="item.properties.map((property) => property.valueName).join(' ')"
            :price="item.price"
            :num="item.count"
          >
            <template #tool>
              <view class="ss-flex">
                <button
                  class="ss-reset-button apply-btn"
                  v-if="[10, 20, 30].includes(state.orderInfo.status) && item.afterSaleStatus === 0"
                  @tap.stop="
                    sheep.$router.go('/pages/order/aftersale/apply', {
                      orderId: state.orderInfo.id,
                      itemId: item.id,
                    })
                  "
                >
                  申请售后
                </button>
                <button
                  class="ss-reset-button apply-btn"
                  v-if="item.afterSaleStatus === 10"
                  @tap.stop="
                    sheep.$router.go('/pages/order/aftersale/detail', {
                      id: item.afterSaleId,
                    })
                  "
                >
                  退款中
                </button>
                <button
                  class="ss-reset-button apply-btn"
                  v-if="item.afterSaleStatus === 20"
                  @tap.stop="
                    sheep.$router.go('/pages/order/aftersale/detail', {
                      id: item.afterSaleId,
                    })
                  "
                >
                  退款成功
                </button>
              </view>
            </template>
            <template #priceSuffix>
              <button class="ss-reset-button tag-btn" v-if="item.status_text">
                {{ item.status_text }}
              </button>
            </template>
          </s-goods-item>
        </view>
      </view>
    </view>

    <!--  自提核销  -->
    <PickUpVerify
      :order-info="state.orderInfo"
      :systemStore="systemStore"
      ref="pickUpVerifyRef"
    ></PickUpVerify>

    <!-- 订单信息 -->
    <view class="notice-box">
      <view class="notice-box__content">
        <view class="notice-item--center">
          <view class="ss-flex ss-flex-1">
            <text class="title">订单编号：</text>
            <text class="detail">{{ state.orderInfo.no }}</text>
          </view>
          <button class="ss-reset-button copy-btn" @tap="onCopy">复制</button>
        </view>
        <view class="notice-item">
          <text class="title">下单时间：</text>
          <text class="detail">
            {{ sheep.$helper.timeFormat(state.orderInfo.createTime, 'yyyy-mm-dd hh:MM:ss') }}
          </text>
        </view>
        <view class="notice-item" v-if="state.orderInfo.payTime">
          <text class="title">支付时间：</text>
          <text class="detail">
            {{ sheep.$helper.timeFormat(state.orderInfo.payTime, 'yyyy-mm-dd hh:MM:ss') }}
          </text>
        </view>
        <view class="notice-item">
          <text class="title">支付方式：</text>
          <text class="detail">{{ state.orderInfo.payChannelName || '-' }}</text>
        </view>
      </view>
    </view>

    <!-- 价格信息 -->
    <view class="order-price-box">
      <view class="notice-item ss-flex ss-row-between">
        <text class="title">商品总额</text>
        <view class="ss-flex">
          <text class="detail">￥{{ fen2yuan(state.orderInfo.totalPrice) }}</text>
        </view>
      </view>
      <view class="notice-item ss-flex ss-row-between">
        <text class="title">运费</text>
        <text class="detail">￥{{ fen2yuan(state.orderInfo.deliveryPrice) }}</text>
      </view>
      <view class="notice-item ss-flex ss-row-between" v-if="state.orderInfo.couponPrice > 0">
        <text class="title">优惠劵金额</text>
        <text class="detail">-¥{{ fen2yuan(state.orderInfo.couponPrice) }}</text>
      </view>
      <view class="notice-item ss-flex ss-row-between" v-if="state.orderInfo.pointPrice > 0">
        <text class="title">积分抵扣</text>
        <text class="detail">-¥{{ fen2yuan(state.orderInfo.pointPrice) }}</text>
      </view>
      <view class="notice-item ss-flex ss-row-between" v-if="state.orderInfo.discountPrice > 0">
        <text class="title">活动优惠</text>
        <text class="detail">¥{{ fen2yuan(state.orderInfo.discountPrice) }}</text>
      </view>
      <view class="notice-item ss-flex ss-row-between" v-if="state.orderInfo.vipPrice > 0">
        <text class="title">会员优惠</text>
        <text class="detail">-¥{{ fen2yuan(state.orderInfo.vipPrice) }}</text>
      </view>
      <view class="notice-item all-rpice-item ss-flex ss-m-t-20">
        <text class="title">{{ state.orderInfo.payStatus ? '已付款' : '需付款' }}</text>
        <text class="detail all-price">￥{{ fen2yuan(state.orderInfo.payPrice) }}</text>
      </view>
      <view
        class="notice-item all-rpice-item ss-flex ss-m-t-20"
        v-if="state.orderInfo.refundPrice > 0"
      >
        <text class="title">已退款</text>
        <text class="detail all-price">￥{{ fen2yuan(state.orderInfo.refundPrice) }}</text>
      </view>
    </view>

    <!-- 底部按钮 -->
    <!-- TODO: 查看物流、等待成团、评价完后返回页面没刷新页面 -->
    <su-fixed bottom placeholder bg="bg-white" v-if="state.orderInfo.buttons?.length">
      <view class="footer-box ss-flex ss-col-center ss-row-right">
        <button
          class="ss-reset-button cancel-btn"
          v-if="state.orderInfo.buttons?.includes('cancel')"
          @tap="onCancel(state.orderInfo.id)"
        >
          取消订单
        </button>
        <button
          class="ss-reset-button pay-btn ui-BG-Main-Gradient"
          v-if="state.orderInfo.buttons?.includes('pay')"
          @tap="onPay(state.orderInfo.payOrderId)"
        >
          继续支付
        </button>
        <button
          class="ss-reset-button cancel-btn"
          v-if="state.orderInfo.buttons?.includes('combination')"
          @tap="
            sheep.$router.go('/pages/activity/groupon/detail', {
              id: state.orderInfo.combinationRecordId,
            })
          "
        >
          拼团详情
        </button>
        <button
          class="ss-reset-button cancel-btn"
          v-if="state.orderInfo.buttons?.includes('express')"
          @tap="onExpress(state.orderInfo.id)"
        >
          查看物流
        </button>
        <button
          class="ss-reset-button cancel-btn"
          v-if="state.orderInfo.buttons?.includes('confirm')"
          @tap="onConfirm(state.orderInfo.id)"
        >
          确认收货
        </button>
        <button
          class="ss-reset-button cancel-btn"
          v-if="state.orderInfo.buttons?.includes('comment')"
          @tap="onComment(state.orderInfo.id)"
        >
          评价
        </button>
      </view>
    </su-fixed>
  </s-layout>
  <s-group-buy-progress :show="progressShow" :headId="progressHeadId" @close="progressShow = false" />
</template>

<script setup>
  import sheep from '@/sheep';
  import { onLoad, onShow } from '@dcloudio/uni-app';
  import { computed, reactive, ref } from 'vue';
  import { isEmpty } from 'lodash-es';
  import {
    fen2yuan,
    formatOrderStatus,
    formatOrderStatusDescription,
    handleOrderButtons,
  } from '@/sheep/hooks/useGoods';
  import OrderApi from '@/sheep/api/trade/order';
  import DeliveryApi from '@/sheep/api/trade/delivery';
  import PayOrderApi from '@/sheep/api/pay/order';
  import PickUpVerify from '@/pages/order/pickUpVerify.vue';

  const statusBarHeight = sheep.$platform.device.statusBarHeight * 2;
  const headerBg = sheep.$url.css('/static/img/shop/order/order_bg.png');

  const state = reactive({
    orderInfo: {},
  });
  const isGroupBuyOrder = computed(() => state.orderInfo.type === 5);
  const groupBuyTitle = computed(() => {
    if (state.orderInfo.status === 0) {
      return '待付款，付款后计入成团人数';
    }
    if (state.orderInfo.status === 10) {
      return '已付款，等待商家发货';
    }
    if (state.orderInfo.status === 20) {
      return '商家已发货，请关注物流';
    }
    if (state.orderInfo.status === 30) {
      return '订单已完成';
    }
    return '订单已关闭';
  });
  const groupBuyOrderStatus = computed(() => (state.orderInfo.payStatus ? '已付款' : '待付款'));
  const groupBuyDesc = computed(() => {
    if (state.orderInfo.status === 0) {
      return '请先完成支付。支付成功后可在“我的拼团”查看团单进度。';
    }
    if (state.orderInfo.status === 10) {
      return '团单成团后，商家会按订单履约发货。可进入拼团进度查看当前团单。';
    }
    if (state.orderInfo.status === 20) {
      return '商品已发货。收到商品后可在本页确认收货。';
    }
    if (state.orderInfo.status === 30) {
      return '该社区拼团订单已完成，感谢支持校园社区团购。';
    }
    return '该社区拼团订单已关闭。';
  });

  // ========== 门店自提（核销） ==========
  const systemStore = ref({}); // 门店信息

  // 复制
  const onCopy = () => {
    sheep.$helper.copyText(state.orderInfo.no);
  };

  // 去支付
  function onPay(payOrderId) {
    sheep.$router.go('/pages/pay/index', {
      id: payOrderId,
    });
  }

  // 查看商品
  function onGoodsDetail(id) {
    sheep.$router.go('/pages/goods/index', {
      id,
    });
  }

  // 取消订单
  async function onCancel(orderId) {
    uni.showModal({
      title: '提示',
      content: '确定要取消订单吗?',
      success: async function (res) {
        if (!res.confirm) {
          return;
        }
        const { code } = await OrderApi.cancelOrder(orderId);
        if (code === 0) {
          await getOrderDetail(orderId);
        }
      },
    });
  }

  // 查看物流
  async function onExpress(id) {
    sheep.$router.go('/pages/order/express/log', {
      id,
    });
  }

  function onGroupBuyProgress() {
    if (state.orderInfo.groupBuyHeadId) {
      progressHeadId.value = state.orderInfo.groupBuyHeadId;
      progressShow.value = true;
      return;
    }
    sheep.$router.go('/pages/groupbuy/mine');
  }

  function goChat() {
    uni.navigateTo({ url: '/pages/chat/index' });
  }

  const progressShow = ref(false);
  const progressHeadId = ref(0);

  // 确认收货
  async function onConfirm(orderId, ignore = false) {
    // 需开启确认收货组件
    // todo: 芋艿：【微信物流】待接入微信 https://gitee.com/sheepjs/shopro-uniapp/commit/a6bbba49b84dd418b84c5fefc8b7463df8f4901f
    // 1.怎么检测是否开启了发货组件功能？如果没有开启的话就不能在这里return出去
    // 2.如果开启了走mpConfirm方法,需要在App.vue的show方法中拿到确认收货结果
    let isOpenBusinessView = true;
    if (
      sheep.$platform.name === 'WechatMiniProgram' &&
      !isEmpty(state.orderInfo.wechat_extra_data) &&
      isOpenBusinessView &&
      !ignore
    ) {
      mpConfirm(orderId);
      return;
    }

    uni.showModal({
      title: '提示',
      content: '确认收货吗？',
      success: async function (res) {
        if (!res.confirm) {
          return;
        }
        // 正常的确认收货流程
        const { code } = await OrderApi.receiveOrder(orderId);
        if (code === 0) {
          await getOrderDetail(orderId);
        }
      },
    });
  }

  // #ifdef MP-WEIXIN
  // 小程序确认收货组件
  function mpConfirm(orderId) {
    if (!wx.openBusinessView) {
      sheep.$helper.toast(`请升级微信版本`);
      return;
    }
    wx.openBusinessView({
      businessType: 'weappOrderConfirm',
      extraData: {
        merchant_trade_no: state.orderInfo.wechat_extra_data.merchant_trade_no,
        transaction_id: state.orderInfo.wechat_extra_data.transaction_id,
      },
      success(response) {
        console.log('success:', response);
        if (response.errMsg === 'openBusinessView:ok') {
          if (response.extraData.status === 'success') {
            onConfirm(orderId, true);
          }
        }
      },
      fail(error) {
        console.log('error:', error);
      },
      complete(result) {
        console.log('result:', result);
      },
    });
  }

  // #endif

  // 评价
  function onComment(id) {
    sheep.$router.go('/pages/goods/comment/add', {
      id,
    });
  }

  const pickUpVerifyRef = ref();

  async function getOrderDetail(id) {
    // 对详情数据进行适配
    let res;
    if (state.comeinType === 'wechat') {
      // TODO 芋艿：【微信物流】微信场景下
      res = await OrderApi.getOrderDetail(id, {
        merchant_trade_no: state.merchantTradeNo,
      });
    } else {
      res = await OrderApi.getOrderDetail(id);
    }
    if (res.code === 0) {
      state.orderInfo = res.data;
      handleOrderButtons(state.orderInfo);
      // 配送方式：门店自提
      if (res.data.pickUpStoreId) {
        const { data } = await DeliveryApi.getDeliveryPickUpStore(res.data.pickUpStoreId);
        systemStore.value = data || {};
      }
      if (state.orderInfo.deliveryType === 2 && state.orderInfo.payStatus) {
        pickUpVerifyRef.value && pickUpVerifyRef.value.markCode(res.data.pickUpVerifyCode);
      }
    } else {
      sheep.$router.back();
    }
  }

  onShow(async () => {
    // onShow 中获取订单列表,保证跳转后页面为最新状态
    // 有几率在 onLoad 完成 state.orderInfo.id 赋值前进入 onShow
    if (state.orderInfo.id) {
      await getOrderDetail(state.orderInfo.id);
    }
  });

  onLoad(async (options) => {
    let id = 0;
    if (options.id) {
      id = options.id;
    }
    // 场景：例如说“微信小程序购物订单”
    // https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order_center/order_center.html
    // （小程序商品订单详情 path）配置参考：pages/order/detail?payOrderNo=${商品订单号} 。其中：${商品订单号} out_trade_no 为 payOrderNo
    if (!id && options.payOrderNo) {
      // 查询支付订单：根据 payOrderNo 取 merchantOrderId ，merchantOrderId 即 tradeOrderId
      const payOrder = await PayOrderApi.getOrder(undefined, undefined, options.payOrderNo);
      if (payOrder.code === 0) {
        id = payOrder.data?.merchantOrderId;
      }
    }
    state.orderInfo.id = id;
    // 完成 state.orderInfo.id 赋值后加载一次detail，但有几率与 onShow 重复可能导致 detail 会加载两次。
    await getOrderDetail(state.orderInfo.id);
  });
</script>

<style lang="scss" scoped>
  .score-img {
    width: 36rpx;
    height: 36rpx;
    margin: 0 4rpx;
  }

  .apply-btn {
    width: 140rpx;
    height: 50rpx;
    border-radius: 25rpx;
    font-size: 24rpx;
    border: 2rpx solid #dcdcdc;
    line-height: normal;
    margin-left: 16rpx;
  }

  .state-box {
    color: rgba(#fff, 0.9);
    width: 100%;
    background: v-bind(headerBg) no-repeat,
      linear-gradient(90deg, var(--ui-BG-Main), var(--ui-BG-Main-gradient));
    background-size: 750rpx 100%;
    box-sizing: border-box;

    .state-img {
      width: 60rpx;
      height: 60rpx;
      margin-right: 20rpx;
    }
  }

  .order-address-box {
    background-color: #fff;
    border-radius: 10rpx;
    margin: -50rpx 20rpx 16rpx 20rpx;
    padding: 44rpx 34rpx 42rpx 20rpx;
    font-size: 30rpx;
    box-sizing: border-box;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);

    .address-username {
      margin-right: 20rpx;
    }

    .address-detail {
      font-size: 26rpx;
      font-weight: 500;
      color: rgba(153, 153, 153, 1);
      margin-top: 20rpx;
    }
  }

  .order-address-box.after-group-buy {
    margin-top: 0;
  }

  .group-buy-card {
    margin: -50rpx 20rpx 16rpx 20rpx;
    padding: 24rpx;
    border-radius: 10rpx;
    background: #ffffff;
    box-shadow: 0 8rpx 24rpx rgba(30, 58, 48, 0.08);
  }

  .group-buy-head {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    gap: 20rpx;
  }

  .group-buy-brand {
    display: flex;
    align-items: center;
    min-width: 0;
  }

  .group-buy-logo {
    flex-shrink: 0;
    width: 64rpx;
    height: 64rpx;
    margin-right: 14rpx;
  }

  .group-buy-eyebrow,
  .group-buy-title {
    display: block;
  }

  .group-buy-eyebrow {
    color: #f37b1d;
    font-size: 22rpx;
    font-weight: 700;
  }

  .group-buy-title {
    margin-top: 6rpx;
    color: #17251f;
    font-size: 28rpx;
    font-weight: 800;
    line-height: 1.35;
  }

  .group-buy-status {
    flex-shrink: 0;
    padding: 8rpx 12rpx;
    border-radius: 6rpx;
    background: #e5f2ec;
    color: #f37b1d;
    font-size: 21rpx;
    font-weight: 700;
  }

  .group-buy-desc {
    margin-top: 18rpx;
    padding: 18rpx;
    border-radius: 8rpx;
    background: #f3f7f5;
    color: #60736b;
    font-size: 24rpx;
    line-height: 1.5;
  }

  .group-buy-flow {
    display: flex;
    align-items: center;
    padding-top: 22rpx;
  }

  .group-buy-step {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    min-width: 0;
    color: #8a9691;
    font-size: 22rpx;
  }

  .group-buy-step.active {
    color: #f37b1d;
  }

  .step-dot {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 42rpx;
    height: 42rpx;
    margin-bottom: 10rpx;
    border-radius: 50%;
    background: #dbe5df;
    color: #ffffff;
    font-size: 21rpx;
    font-weight: 800;
  }

  .group-buy-step.active .step-dot {
    background: #f37b1d;
  }

  .step-line {
    width: 44rpx;
    height: 2rpx;
    margin-bottom: 38rpx;
    background: #d9e5de;
  }

  .group-buy-actions {
    display: flex;
    justify-content: flex-end;
    gap: 12rpx;
    padding-top: 22rpx;
  }

  .group-buy-action {
    width: 156rpx;
    height: 58rpx;
    border-radius: 8rpx;
    background: #f37b1d;
    color: #ffffff;
    font-size: 23rpx;
    line-height: 58rpx;
  }

  .group-buy-action.ghost {
    background: #ffffff;
    color: #f37b1d;
    border: 1rpx solid #c9ddd4;
  }

  .detail-goods {
    border-radius: 10rpx;
    margin: 0 20rpx 20rpx 20rpx;

    .order-list {
      margin-bottom: 20rpx;
      background-color: #fff;

      .order-card {
        padding: 20rpx 0;

        .order-sku {
          font-size: 24rpx;

          font-weight: 400;
          color: rgba(153, 153, 153, 1);
          width: 450rpx;
          margin-bottom: 20rpx;

          .order-num {
            margin-right: 10rpx;
          }
        }

        .tag-btn {
          margin-left: 16rpx;
          font-size: 24rpx;
          height: 36rpx;
          color: var(--ui-BG-Main);
          border: 2rpx solid var(--ui-BG-Main);
          border-radius: 14rpx;
          padding: 0 4rpx;
        }
      }
    }
  }

  // 订单信息。
  .notice-box {
    background: #fff;
    border-radius: 10rpx;
    margin: 0 20rpx 20rpx 20rpx;

    .notice-box__head {
      font-size: 30rpx;

      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 80rpx;
      border-bottom: 1rpx solid #dfdfdf;
      padding: 0 25rpx;
    }

    .notice-box__content {
      padding: 20rpx;

      .self-pickup-box {
        width: 100%;

        .self-pickup--img {
          width: 200rpx;
          height: 200rpx;
          margin: 40rpx 0;
        }
      }
    }

    .notice-item,
    .notice-item--center {
      display: flex;
      align-items: center;
      line-height: normal;
      margin-bottom: 24rpx;

      .title {
        font-size: 28rpx;
        color: #999;
      }

      .detail {
        font-size: 28rpx;
        color: #333;
        flex: 1;
      }
    }
  }

  .copy-btn {
    width: 100rpx;
    line-height: 50rpx;
    border-radius: 25rpx;
    padding: 0;
    background: rgba(238, 238, 238, 1);
    font-size: 22rpx;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }

  // 订单价格信息
  .order-price-box {
    background-color: #fff;
    border-radius: 10rpx;
    padding: 20rpx;
    margin: 0 20rpx 20rpx 20rpx;

    .notice-item {
      line-height: 70rpx;

      .title {
        font-size: 28rpx;
        color: #999;
      }

      .detail {
        font-size: 28rpx;
        color: #333;
        font-family: OPPOSANS;
      }
    }

    .all-rpice-item {
      justify-content: flex-end;
      align-items: center;

      .title {
        font-size: 26rpx;
        font-weight: 500;
        color: #333333;
        line-height: normal;
      }

      .all-price {
        font-size: 26rpx;
        font-family: OPPOSANS;
        line-height: normal;
        color: $red;
      }
    }
  }

  // 底部
  .footer-box {
    height: 100rpx;
    width: 100%;
    box-sizing: border-box;
    border-radius: 10rpx;
    padding-right: 20rpx;

    .cancel-btn {
      width: 160rpx;
      height: 60rpx;
      background: #eeeeee;
      border-radius: 30rpx;
      margin-right: 20rpx;
      font-size: 26rpx;
      font-weight: 400;
      color: #333333;
    }

    .group-buy-footer-btn {
      color: #f37b1d;
      background: #e5f2ec;
    }

    .pay-btn {
      width: 160rpx;
      height: 60rpx;
      font-size: 26rpx;
      border-radius: 30rpx;
      font-weight: 500;
      color: #fff;
    }
  }
</style>
