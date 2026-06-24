package cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 团单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupBuyHeadPageReqVO extends PageParam {

    @Schema(description = "活动 ID")
    private Long activityId;

    @Schema(description = "团长 user_id")
    private Long leaderUserId;

    @Schema(description = "团单状态 (0=拼团中 1=已成团 2=已失败)")
    private Integer status;
}
