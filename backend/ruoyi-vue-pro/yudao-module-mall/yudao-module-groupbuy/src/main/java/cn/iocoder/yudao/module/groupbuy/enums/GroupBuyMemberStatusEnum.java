package cn.iocoder.yudao.module.groupbuy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 团成员状态
 */
@Getter
@AllArgsConstructor
public enum GroupBuyMemberStatusEnum {
    PENDING(0, "待支付"),
    PAID(1, "已支付"),
    CANCELED(2, "已取消"),
    REFUNDED(3, "已退款");

    private final Integer status;
    private final String desc;

    public static String fromStatus(Integer status) {
        return Arrays.stream(values())
                .filter(e -> e.status.equals(status))
                .findFirst()
                .map(GroupBuyMemberStatusEnum::getDesc)
                .orElse("未知");
    }
}
