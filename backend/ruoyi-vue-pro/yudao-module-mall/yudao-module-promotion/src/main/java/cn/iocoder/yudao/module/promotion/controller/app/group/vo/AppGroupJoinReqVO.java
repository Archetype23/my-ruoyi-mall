package cn.iocoder.yudao.module.promotion.controller.app.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 App - 参与团购 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppGroupJoinReqVO extends AppGroupBaseReqVO {

    @Schema(description = "团长记录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "团长记录编号不能为空")
    private Long headId;

}
