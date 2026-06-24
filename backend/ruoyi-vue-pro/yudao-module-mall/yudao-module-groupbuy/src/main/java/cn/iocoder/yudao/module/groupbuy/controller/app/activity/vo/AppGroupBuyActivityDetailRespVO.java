package cn.iocoder.yudao.module.groupbuy.controller.app.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "用户端 - 拼团活动详情 Response VO")
@Data
public class AppGroupBuyActivityDetailRespVO {

    private Long id;
    private String name;
    private Long spuId;
    private Long skuId;
    private String picUrl;
    private Integer groupPrice;
    private Integer originalPrice;
    private Integer userSize;
    private Integer stockRemain;
    private Integer singleLimit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer expireMinutes;
    private Integer status;

    /** 进行中的团 (前 5 个) */
    private List<ActiveHead> activeHeads;

    @Data
    public static class ActiveHead {
        private Long headId;
        private Integer currentCount;
        private Integer userSize;
        private String leaderNickname;
        private String leaderAvatar;
        private Long expireInSeconds;
    }
}
