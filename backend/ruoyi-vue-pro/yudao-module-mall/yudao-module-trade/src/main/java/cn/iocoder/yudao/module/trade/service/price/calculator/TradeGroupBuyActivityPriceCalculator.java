package cn.iocoder.yudao.module.trade.service.price.calculator;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.service.activity.GroupBuyActivityService;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
import cn.iocoder.yudao.module.promotion.enums.common.PromotionTypeEnum;
import cn.iocoder.yudao.module.trade.service.price.bo.TradePriceCalculateReqBO;
import cn.iocoder.yudao.module.trade.service.price.bo.TradePriceCalculateRespBO;
import jakarta.annotation.Resource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 社区拼团活动价格计算器。
 */
@Component
@Order(TradePriceCalculator.ORDER_GROUP_BUY_ACTIVITY)
public class TradeGroupBuyActivityPriceCalculator implements TradePriceCalculator {

    @Resource
    private GroupBuyActivityService groupBuyActivityService;
    @Resource
    private GroupBuyHeadService groupBuyHeadService;

    @Override
    public void calculate(TradePriceCalculateReqBO param, TradePriceCalculateRespBO result) {
        if (param.getGroupBuyActivityId() == null) {
            return;
        }
        Assert.isTrue(param.getItems().size() == 1, "社区拼团时，只允许选择一个商品");
        TradePriceCalculateRespBO.OrderItem orderItem = result.getItems().get(0);
        groupBuyHeadService.validateJoinForOrder(param.getUserId(), param.getGroupBuyActivityId(),
                param.getGroupBuyHeadId(), orderItem.getSkuId(), orderItem.getCount());

        GroupBuyActivityDO activity = groupBuyActivityService.getActivity(param.getGroupBuyActivityId());
        Integer groupPayPrice = activity.getGroupPrice() * orderItem.getCount();
        Integer discountPrice = Math.max(orderItem.getPayPrice() - groupPayPrice, 0);
        TradePriceCalculatorHelper.addPromotion(result, orderItem,
                activity.getId(), activity.getName(), PromotionTypeEnum.COMBINATION_ACTIVITY.getType(),
                StrUtil.format("社区拼团：省 {} 元", TradePriceCalculatorHelper.formatPrice(discountPrice)),
                discountPrice);
        orderItem.setDiscountPrice(orderItem.getDiscountPrice() + discountPrice);
        orderItem.setPayPrice(groupPayPrice);
        TradePriceCalculatorHelper.recountAllPrice(result);
    }

}
