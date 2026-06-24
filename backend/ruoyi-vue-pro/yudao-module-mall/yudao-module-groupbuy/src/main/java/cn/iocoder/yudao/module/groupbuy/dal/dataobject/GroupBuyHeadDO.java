package cn.iocoder.yudao.module.groupbuy.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 团单 DO (一次开团)
 */
@TableName("group_buy_head")
@KeySequence("group_buy_head_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyHeadDO extends BaseDO {

    @TableId
    private Long id;

    private Long activityId;
    private Long leaderUserId;
    private String leaderNickname;
    private String leaderAvatar;

    private Integer userSize;
    private Integer currentCount;

    /** 0=拼团中,1=已成团,2=已失败 */
    private Integer status;
    private LocalDateTime expireTime;
    private LocalDateTime successTime;
    private LocalDateTime failTime;
    private String failReason;
}
