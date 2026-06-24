package cn.iocoder.yudao.module.groupbuy.controller.app.head.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "用户端 - 开团/参团 Request VO")
@Data
public class AppGroupBuyJoinReqVO {

    @Schema(description = "活动 ID (开团时必填, 参团时可不填)", example = "1")
    private Long activityId;

    @Schema(description = "团单 ID (参团时必填, 开团时为 null)", example = "1")
    private Long headId;

    @Schema(description = "SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "SKU 不能为空")
    private Long skuId;

    @Schema(description = "购买数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "购买数量不能为空")
    @Min(value = 1, message = "购买数量至少 1")
    private Integer count;

    @Schema(description = "收货地址 ID (自提时可不填)", example = "123")
    private Long addressId;

    @Schema(description = "自提门店 ID (自提时必填)", example = "1")
    private Long pickUpStoreId;

    @Schema(description = "用户备注", example = "请尽快发货")
    private String remark;
}
