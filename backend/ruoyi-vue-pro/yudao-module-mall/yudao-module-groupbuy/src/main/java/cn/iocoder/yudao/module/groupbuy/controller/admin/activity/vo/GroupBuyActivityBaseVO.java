package cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 拼团活动 Base VO")
@Data
public class GroupBuyActivityBaseVO {

    @Schema(description = "活动名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芒果 3 人团")
    @NotBlank(message = "活动名不能为空")
    private String name;

    @Schema(description = "商品 SPU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品 SPU 不能为空")
    private Long spuId;

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品 SKU 不能为空")
    private Long skuId;

    @Schema(description = "活动图", example = "https://...")
    private String picUrl;

    @Schema(description = "拼团价 (分)", requiredMode = Schema.RequiredMode.REQUIRED, example = "9900")
    @NotNull(message = "拼团价不能为空")
    @Min(value = 1, message = "拼团价必须大于 0")
    private Integer groupPrice;

    @Schema(description = "原价 (分)", requiredMode = Schema.RequiredMode.REQUIRED, example = "19900")
    @NotNull(message = "原价不能为空")
    @Min(value = 1, message = "原价必须大于 0")
    private Integer originalPrice;

    @Schema(description = "成团人数 (2/3/5)", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull(message = "成团人数不能为空")
    @Min(value = 2, message = "成团人数至少 2 人")
    private Integer userSize;

    @Schema(description = "总库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "总库存不能为空")
    @Min(value = 0, message = "总库存不能为负")
    private Integer stockTotal;

    @Schema(description = "单人购买数量限制", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "单次限购不能为空")
    @Min(value = 1, message = "单次限购至少 1")
    private Integer singleLimit;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @Schema(description = "开团后多少分钟未成团自动失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "1440")
    @NotNull(message = "成团时限不能为空")
    @Min(value = 5, message = "成团时限至少 5 分钟")
    private Integer expireMinutes;
}
