package cn.iocoder.yudao.module.groupbuy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 拼团活动状态
 */
@Getter
@AllArgsConstructor
public enum GroupBuyActivityStatusEnum {
    WAIT(0, "未开始"),
    RUNNING(1, "进行中"),
    END(2, "已结束"),
    CLOSE(3, "已关闭");

    private final Integer status;
    private final String desc;

    public static String fromStatus(Integer status) {
        return Arrays.stream(values())
                .filter(e -> e.status.equals(status))
                .findFirst()
                .map(GroupBuyActivityStatusEnum::getDesc)
                .orElse("未知");
    }
}
