package cn.iocoder.yudao.module.promotion.controller.app.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "用户 App - 团购请求基类 VO")
@Data
public abstract class AppGroupBaseReqVO {

    @Schema(description = "拼团活动编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "拼团活动编号不能为空")
    private Long activityId;

    @Schema(description = "商品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    @NotNull(message = "商品编号不能为空")
    private Long goodsId;

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "4096")
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;

    @Schema(description = "购买数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "购买数量不能为空")
    private Integer count;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8192")
    @NotNull(message = "订单编号不能为空")
    private Long orderId;

    @Schema(description = "拼团金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "拼团金额不能为空")
    private Integer combinationPrice;

}
