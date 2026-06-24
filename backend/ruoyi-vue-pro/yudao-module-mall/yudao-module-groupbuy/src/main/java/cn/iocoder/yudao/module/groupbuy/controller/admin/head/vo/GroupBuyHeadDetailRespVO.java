package cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 团单详情 Response VO")
@Data
public class GroupBuyHeadDetailRespVO {

    @Schema(description = "团单 ID")
    private Long id;

    private Long activityId;
    private String activityName;
    private String activityPicUrl;
    private Integer groupPrice;
    private Integer originalPrice;

    private Long leaderUserId;
    private String leaderNickname;
    private String leaderAvatar;

    private Integer userSize;
    private Integer currentCount;

    private Integer status;
    private String statusName;
    private LocalDateTime expireTime;
    private LocalDateTime successTime;
    private LocalDateTime failTime;
    private String failReason;
    private LocalDateTime createTime;

    private List<Member> members;

    @Data
    public static class Member {
        private Long id;
        private Long userId;
        private String nickname;
        private String avatar;
        private Boolean isLeader;
        private Long orderId;
        private Integer status;
        private String statusName;
        private LocalDateTime joinTime;
    }
}
