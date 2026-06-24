package cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 拼团活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupBuyActivityPageReqVO extends PageParam {

    @Schema(description = "活动名 (模糊)")
    private String name;

    @Schema(description = "SPU 编号")
    private Long spuId;

    @Schema(description = "活动状态")
    private Integer status;
}
