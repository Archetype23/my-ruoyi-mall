package cn.iocoder.yudao.module.groupbuy.controller.app.head.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "用户端 - 团单详情 Response VO")
@Data
public class AppGroupBuyHeadDetailRespVO {

    private Long id;
    private Long activityId;
    private String activityName;
    private String activityPicUrl;
    private Integer groupPrice;
    private Integer originalPrice;

    private Long leaderUserId;
    private String leaderNickname;
    private String leaderAvatar;
    private Boolean isLeader;

    private Integer userSize;
    private Integer currentCount;
    private Integer status;
    private String statusName;
    private LocalDateTime expireTime;
    private Long expireInSeconds;
    private LocalDateTime createTime;

    /** 当前用户订单 ID (用于支付跳转) */
    private Long orderId;

    private List<Member> members;

    @Data
    public static class Member {
        private Long userId;
        private String nickname;
        private String avatar;
        private Boolean isLeader;
        private Integer status;
    }
}
