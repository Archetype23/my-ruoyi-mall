package cn.iocoder.yudao.module.groupbuy.controller.admin.verify.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "团长核销分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupBuyVerifyPageReqVO extends PageParam {

    @Schema(description = "活动 ID")
    private Long activityId;

    @Schema(description = "订单号/核销码 (模糊)")
    private String keyword;
}
