package cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 拼团活动 Response VO")
@Data
public class GroupBuyActivityRespVO {

    @Schema(description = "活动 ID")
    private Long id;

    private String name;
    private Long spuId;
    private Long skuId;
    private String picUrl;
    private Integer groupPrice;
    private Integer originalPrice;
    private Integer userSize;
    private Integer stockTotal;
    private Integer stockUsed;
    private Integer singleLimit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer expireMinutes;
    private Integer status;
    private LocalDateTime createTime;
}
