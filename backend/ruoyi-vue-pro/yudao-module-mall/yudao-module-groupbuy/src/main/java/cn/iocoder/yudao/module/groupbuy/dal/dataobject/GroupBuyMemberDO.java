package cn.iocoder.yudao.module.groupbuy.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 团成员 DO
 */
@TableName("group_buy_member")
@KeySequence("group_buy_member_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyMemberDO extends BaseDO {

    @TableId
    private Long id;

    private Long headId;
    private Long userId;
    private String nickname;
    private String avatar;
    private Boolean isLeader;
    private Long orderId;

    /** 0=待支付,1=已支付,2=已取消,3=已退款 */
    private Integer status;
    private LocalDateTime joinTime;
}
