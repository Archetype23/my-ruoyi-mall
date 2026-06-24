package cn.iocoder.yudao.module.groupbuy.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * groupbuy 错误码
 * 区间 1-100-000 ~ 1-100-999
 */
public interface ErrorCodeConstants {
    ErrorCode ACTIVITY_NOT_EXISTS = new ErrorCode(1_100_000, "拼团活动不存在");
    ErrorCode ACTIVITY_STATUS_DISABLED = new ErrorCode(1_100_001, "拼团活动已下架");
    ErrorCode ACTIVITY_NOT_IN_TIME = new ErrorCode(1_100_002, "拼团活动未开始或已结束");
    ErrorCode ACTIVITY_STOCK_NOT_ENOUGH = new ErrorCode(1_100_003, "拼团活动库存不足");
    ErrorCode ACTIVITY_SPU_NOT_EXISTS = new ErrorCode(1_100_004, "拼团活动关联的商品不存在");
    ErrorCode ACTIVITY_SKU_NOT_EXISTS = new ErrorCode(1_100_005, "拼团活动关联的 SKU 不存在");
    ErrorCode ACTIVITY_TIME_INVALID = new ErrorCode(1_100_006, "结束时间不能早于开始时间");
    ErrorCode ACTIVITY_PRICE_INVALID = new ErrorCode(1_100_007, "拼团价不能高于原价");

    ErrorCode HEAD_NOT_EXISTS = new ErrorCode(1_100_100, "团单不存在");
    ErrorCode HEAD_STATUS_INVALID = new ErrorCode(1_100_101, "团单状态不允许此操作");
    ErrorCode HEAD_EXPIRED = new ErrorCode(1_100_102, "团单已过期");
    ErrorCode HEAD_FULL = new ErrorCode(1_100_103, "团单人数已满");
    ErrorCode HEAD_MEMBER_EXISTS = new ErrorCode(1_100_104, "用户已在此团中");
    ErrorCode HEAD_LEADER_CANNOT_JOIN_OTHER = new ErrorCode(1_100_105, "团长不能参团其他团");

    ErrorCode MEMBER_NOT_EXISTS = new ErrorCode(1_100_200, "团成员不存在");
    ErrorCode MEMBER_NOT_PAID = new ErrorCode(1_100_201, "团成员未支付");
    ErrorCode MEMBER_ALREADY_PAID = new ErrorCode(1_100_202, "团成员已支付");

    ErrorCode VERIFY_CODE_INVALID = new ErrorCode(1_100_300, "核销码无效");
    ErrorCode VERIFY_NOT_LEADER = new ErrorCode(1_100_301, "您不是此团的团长，无权核销");
    ErrorCode VERIFY_ORDER_NOT_WAIT_PICKUP = new ErrorCode(1_100_302, "订单不在待核销状态");
    ErrorCode VERIFY_ORDER_NOT_BELONG_TO_LEADER = new ErrorCode(1_100_303, "订单不属于您所在的拼团");
}
