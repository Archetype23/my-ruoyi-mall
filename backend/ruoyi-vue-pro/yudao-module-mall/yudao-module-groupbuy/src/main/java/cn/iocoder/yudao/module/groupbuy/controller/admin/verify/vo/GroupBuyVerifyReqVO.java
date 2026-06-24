package cn.iocoder.yudao.module.groupbuy.controller.admin.verify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "团长核销 Request VO")
@Data
public class GroupBuyVerifyReqVO {

    @Schema(description = "核销码 (订单号或 6 位验证码)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "核销码不能为空")
    private String verifyCode;
}
