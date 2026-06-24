package cn.iocoder.yudao.module.groupbuy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 团单状态
 */
@Getter
@AllArgsConstructor
public enum GroupBuyHeadStatusEnum {
    IN_PROGRESS(0, "拼团中"),
    SUCCESS(1, "已成团"),
    FAILED(2, "已失败");

    private final Integer status;
    private final String desc;

    public static String fromStatus(Integer status) {
        return Arrays.stream(values())
                .filter(e -> e.status.equals(status))
                .findFirst()
                .map(GroupBuyHeadStatusEnum::getDesc)
                .orElse("未知");
    }
}
