package cn.iocoder.yudao.module.trade.service.order.handler;

import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import cn.iocoder.yudao.module.groupbuy.enums.GroupBuyHeadStatusEnum;
import cn.iocoder.yudao.module.groupbuy.service.activity.GroupBuyActivityService;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
import cn.iocoder.yudao.module.trade.dal.dataobject.order.TradeOrderDO;
import cn.iocoder.yudao.module.trade.dal.dataobject.order.TradeOrderItemDO;
import cn.iocoder.yudao.module.trade.dal.mysql.order.TradeOrderMapper;
import cn.iocoder.yudao.module.trade.enums.order.TradeOrderTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.trade.enums.ErrorCodeConstants.ORDER_DELIVERY_FAIL_GROUP_BUY_HEAD_STATUS_NOT_SUCCESS;

/**
 * 社区拼团订单处理器。
 */
@Component
public class TradeGroupBuyOrderHandler implements TradeOrderHandler {

    @Resource
    private GroupBuyHeadService groupBuyHeadService;
    @Resource
    private GroupBuyActivityService groupBuyActivityService;
    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Override
    public void beforeOrderCreate(TradeOrderDO order, List<TradeOrderItemDO> orderItems) {
        if (!TradeOrderTypeEnum.isGroupBuy(order.getType())) {
            return;
        }
        Assert.isTrue(orderItems.size() == 1, "社区拼团时，只允许选择一个商品");
        TradeOrderItemDO item = orderItems.get(0);
        groupBuyHeadService.validateJoinForOrder(order.getUserId(), order.getGroupBuyActivityId(),
                order.getGroupBuyHeadId(), item.getSkuId(), item.getCount());
        // 从活动继承商家部门编号，用于数据权限隔离
        GroupBuyActivityDO activity = groupBuyActivityService.getActivity(order.getGroupBuyActivityId());
        if (activity != null) {
            order.setDeptId(activity.getDeptId());
        }
    }

    @Override
    public void afterOrderCreate(TradeOrderDO order, List<TradeOrderItemDO> orderItems) {
        if (!TradeOrderTypeEnum.isGroupBuy(order.getType())) {
            return;
        }
        TradeOrderItemDO item = orderItems.get(0);
        Long headId = groupBuyHeadService.createPendingMemberForOrder(order.getUserId(), order.getId(),
                order.getGroupBuyActivityId(), order.getGroupBuyHeadId(), item.getSkuId(), item.getCount());
        if (!headId.equals(order.getGroupBuyHeadId())) {
            tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId()).setGroupBuyHeadId(headId));
            order.setGroupBuyHeadId(headId);
        }
    }

    @Override
    public void afterPayOrder(TradeOrderDO order, List<TradeOrderItemDO> orderItems) {
        if (TradeOrderTypeEnum.isGroupBuy(order.getType())) {
            groupBuyHeadService.onMemberPaid(order.getId());
        }
    }

    @Override
    public void afterCancelOrder(TradeOrderDO order, List<TradeOrderItemDO> orderItems) {
        if (TradeOrderTypeEnum.isGroupBuy(order.getType())) {
            Integer count = orderItems == null || orderItems.isEmpty() ? null : orderItems.get(0).getCount();
            groupBuyHeadService.cancelPendingOrder(order.getId(), count);
        }
    }

    @Override
    public void beforeDeliveryOrder(TradeOrderDO order) {
        if (!TradeOrderTypeEnum.isGroupBuy(order.getType())) {
            return;
        }
        GroupBuyHeadDO head = groupBuyHeadService.getHead(order.getGroupBuyHeadId());
        if (head == null || !GroupBuyHeadStatusEnum.SUCCESS.getStatus().equals(head.getStatus())) {
            throw exception(ORDER_DELIVERY_FAIL_GROUP_BUY_HEAD_STATUS_NOT_SUCCESS);
        }
    }

}
