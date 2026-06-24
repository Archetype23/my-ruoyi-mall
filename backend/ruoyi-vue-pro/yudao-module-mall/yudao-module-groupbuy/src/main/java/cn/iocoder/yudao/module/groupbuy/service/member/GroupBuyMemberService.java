package cn.iocoder.yudao.module.groupbuy.service.member;

import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyMemberDO;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyMemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

@Service
@Validated
@Slf4j
public class GroupBuyMemberService {

    @Resource
    private GroupBuyMemberMapper memberMapper;

    /**
     * 根据订单 ID 查找团成员
     */
    public GroupBuyMemberDO getMemberByOrderId(Long orderId) {
        if (orderId == null) {
            return null;
        }
        List<GroupBuyMemberDO> list = memberMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyMemberDO>()
                        .eq(GroupBuyMemberDO::getOrderId, orderId)
                        .last("LIMIT 1"));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 更新成员订单号
     */
    public void updateOrderId(Long memberId, Long orderId) {
        GroupBuyMemberDO update = new GroupBuyMemberDO();
        update.setId(memberId);
        update.setOrderId(orderId);
        memberMapper.updateById(update);
    }

    /**
     * 填充用户信息
     */
    public void fillUserInfo(Long memberId, String nickname, String avatar) {
        GroupBuyMemberDO update = new GroupBuyMemberDO();
        update.setId(memberId);
        update.setNickname(nickname);
        update.setAvatar(avatar);
        memberMapper.updateById(update);
    }
}
