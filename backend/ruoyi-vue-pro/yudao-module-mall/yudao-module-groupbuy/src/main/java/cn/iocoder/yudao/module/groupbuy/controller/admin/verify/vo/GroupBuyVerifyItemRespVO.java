package cn.iocoder.yudao.module.groupbuy.controller.admin.verify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "团长核销列表项 Response VO")
@Data
public class GroupBuyVerifyItemRespVO {

    private Long orderId;
    private String orderNo;
    private Long headId;
    private Long memberId;
    private Long userId;
    private String userNickname;
    private String userAvatar;

    private Long activityId;
    private String activityName;
    private String spuName;
    private String spuPicUrl;
    private Integer quantity;

    private String pickUpVerifyCode;
    private Integer orderStatus;
    private String orderStatusName;
    private Integer payStatus;

    private LocalDateTime payTime;
    private LocalDateTime createTime;
}
