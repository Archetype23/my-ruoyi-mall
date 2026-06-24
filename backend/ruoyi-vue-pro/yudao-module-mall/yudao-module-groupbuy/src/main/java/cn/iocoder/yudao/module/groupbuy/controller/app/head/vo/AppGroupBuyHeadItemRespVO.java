package cn.iocoder.yudao.module.groupbuy.controller.app.head.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户端 - 我的拼团列表项 Response VO")
@Data
public class AppGroupBuyHeadItemRespVO {

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

    private Long orderId;
    private Integer orderStatus;
}
