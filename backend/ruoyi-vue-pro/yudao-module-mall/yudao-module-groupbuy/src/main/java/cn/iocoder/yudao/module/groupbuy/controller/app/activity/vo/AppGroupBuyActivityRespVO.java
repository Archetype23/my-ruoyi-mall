package cn.iocoder.yudao.module.groupbuy.controller.app.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户端 - 拼团活动 Response VO")
@Data
public class AppGroupBuyActivityRespVO {

    private Long id;
    private String name;
    private Long spuId;
    private Long skuId;
    private String picUrl;
    private Integer groupPrice;
    private Integer originalPrice;
    private Integer userSize;
    private Integer stockRemain;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;

    /** 当前进行中的团数 */
    private Integer activeHeadCount;
}
