package cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "团单列表项 Response VO")
@Data
public class GroupBuyHeadItemRespVO {

    private Long id;
    private Long activityId;
    private String activityName;
    private String activityPicUrl;

    private Long leaderUserId;
    private String leaderNickname;
    private String leaderAvatar;

    private Integer userSize;
    private Integer currentCount;
    private Integer status;
    private String statusName;

    private LocalDateTime expireTime;
    private LocalDateTime successTime;
    private LocalDateTime createTime;
}
