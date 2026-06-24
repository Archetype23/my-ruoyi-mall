package cn.iocoder.yudao.module.groupbuy.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyMemberDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface GroupBuyMemberMapper extends BaseMapperX<GroupBuyMemberDO> {

    default List<GroupBuyMemberDO> selectListByHeadId(Long headId) {
        return selectList(GroupBuyMemberDO::getHeadId, headId);
    }

    default List<GroupBuyMemberDO> selectListByUserId(Long userId) {
        return selectList(GroupBuyMemberDO::getUserId, userId);
    }

    default GroupBuyMemberDO selectByHeadIdAndUserId(Long headId, Long userId) {
        return selectOne(new LambdaQueryWrapperX<GroupBuyMemberDO>()
                .eq(GroupBuyMemberDO::getHeadId, headId)
                .eq(GroupBuyMemberDO::getUserId, userId));
    }

    default GroupBuyMemberDO selectByOrderId(Long orderId) {
        return selectOne(GroupBuyMemberDO::getOrderId, orderId);
    }

    default List<GroupBuyMemberDO> selectListByHeadIds(Collection<Long> headIds) {
        return selectList(GroupBuyMemberDO::getHeadId, headIds);
    }

    default int updateStatus(Long id, Integer fromStatus, Integer toStatus) {
        GroupBuyMemberDO update = new GroupBuyMemberDO();
        update.setId(id);
        update.setStatus(toStatus);
        return update(update, new LambdaQueryWrapperX<GroupBuyMemberDO>()
                .eq(GroupBuyMemberDO::getId, id)
                .eq(GroupBuyMemberDO::getStatus, fromStatus));
    }
}
