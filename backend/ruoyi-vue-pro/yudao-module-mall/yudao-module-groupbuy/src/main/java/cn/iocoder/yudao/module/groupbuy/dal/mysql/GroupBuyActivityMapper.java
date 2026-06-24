package cn.iocoder.yudao.module.groupbuy.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityPageReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GroupBuyActivityMapper extends BaseMapperX<GroupBuyActivityDO> {

    default PageResult<GroupBuyActivityDO> selectPage(GroupBuyActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupBuyActivityDO>()
                .likeIfPresent(GroupBuyActivityDO::getName, reqVO.getName())
                .eqIfPresent(GroupBuyActivityDO::getSpuId, reqVO.getSpuId())
                .eqIfPresent(GroupBuyActivityDO::getStatus, reqVO.getStatus())
                .orderByDesc(GroupBuyActivityDO::getId));
    }

    default List<GroupBuyActivityDO> selectRunningList() {
        LocalDateTime now = LocalDateTime.now();
        return selectList(new LambdaQueryWrapperX<GroupBuyActivityDO>()
                .eq(GroupBuyActivityDO::getStatus, 1)
                .le(GroupBuyActivityDO::getStartTime, now)
                .ge(GroupBuyActivityDO::getEndTime, now)
                .orderByDesc(GroupBuyActivityDO::getId));
    }

    /**
     * 原子扣库存：仅当 stock_used + count <= stock_total 时更新成功
     * @return 影响行数 (1 成功, 0 失败)
     */
    int decrStock(Long id, Integer count);
}
