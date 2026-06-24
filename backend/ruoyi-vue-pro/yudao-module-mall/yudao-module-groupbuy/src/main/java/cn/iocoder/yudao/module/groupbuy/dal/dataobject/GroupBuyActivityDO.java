package cn.iocoder.yudao.module.groupbuy.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 拼团活动 DO
 */
@TableName("group_buy_activity")
@KeySequence("group_buy_activity_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyActivityDO extends BaseDO {

    @TableId
    private Long id;

    private String name;
    private Long spuId;
    private Long skuId;
    private String picUrl;

    /** 拼团价 (分) */
    private Integer groupPrice;
    /** 原价 (分) */
    private Integer originalPrice;

    /** 成团人数 */
    private Integer userSize;
    /** 总库存 */
    private Integer stockTotal;
    /** 已用 */
    private Integer stockUsed;
    /** 单次限购 */
    private Integer singleLimit;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    /** 开团后 N 分钟未成团自动失败 */
    private Integer expireMinutes;

    /** 0=未开始,1=进行中,2=已结束,3=已关闭 */
    private Integer status;

    /**
     * 商家部门编号
     *
     * 用于数据权限隔离，关联 {@link cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO#getId()}
     */
    private Long deptId;
}
