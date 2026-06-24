package cn.iocoder.yudao.module.groupbuy.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadPageReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Mapper
public interface GroupBuyHeadMapper extends BaseMapperX<GroupBuyHeadDO> {

    default PageResult<GroupBuyHeadDO> selectPage(GroupBuyHeadPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupBuyHeadDO>()
                .eqIfPresent(GroupBuyHeadDO::getActivityId, reqVO.getActivityId())
                .eqIfPresent(GroupBuyHeadDO::getLeaderUserId, reqVO.getLeaderUserId())
                .eqIfPresent(GroupBuyHeadDO::getStatus, reqVO.getStatus())
                .orderByDesc(GroupBuyHeadDO::getId));
    }

    /**
     * 原子增加 current_count (并发安全) - 团长开团时调用
     */
    int incrCurrentCount(Long headId, Integer delta);

    /**
     * 标记成团
     */
    int markSuccess(Long headId, LocalDateTime successTime);

    /**
     * 标记失败
     */
    int markFailed(Long headId, LocalDateTime failTime, String reason);

    /**
     * 过期未成团团单
     */
    default List<GroupBuyHeadDO> selectExpiredInProgress(int limit) {
        return selectList(new LambdaQueryWrapperX<GroupBuyHeadDO>()
                .eq(GroupBuyHeadDO::getStatus, 0)
                .lt(GroupBuyHeadDO::getExpireTime, LocalDateTime.now())
                .orderByAsc(GroupBuyHeadDO::getExpireTime)
                .last("LIMIT " + limit));
    }

    /**
     * 列出某活动的进行中团单（仅显示有成员的团，过滤孤儿团）
     */
    default List<GroupBuyHeadDO> selectInProgressByActivity(Long activityId, int limit) {
        return selectList(new LambdaQueryWrapperX<GroupBuyHeadDO>()
                .eq(GroupBuyHeadDO::getActivityId, activityId)
                .eq(GroupBuyHeadDO::getStatus, 0)
                .gt(GroupBuyHeadDO::getCurrentCount, 0)
                .orderByAsc(GroupBuyHeadDO::getCreateTime)
                .last("LIMIT " + limit));
    }

    default List<GroupBuyHeadDO> selectListByIds(Collection<Long> ids) {
        return selectBatchIds(ids);
    }
}
