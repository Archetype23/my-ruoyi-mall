package cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 拼团活动更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupBuyActivityUpdateReqVO extends GroupBuyActivityBaseVO {

    @Schema(description = "活动 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "活动状态 (0=未开始 1=进行中 2=已结束 3=已关闭)")
    private Integer status;
}
