package cn.iocoder.yudao.module.promotion.controller.app.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 App - 发起团购 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppGroupCreateReqVO extends AppGroupBaseReqVO {
}
