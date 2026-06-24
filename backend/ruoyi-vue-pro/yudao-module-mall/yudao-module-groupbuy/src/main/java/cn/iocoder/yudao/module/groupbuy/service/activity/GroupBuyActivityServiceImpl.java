package cn.iocoder.yudao.module.groupbuy.service.activity;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityCreateReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityPageReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityUpdateReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyActivityMapper;
import cn.iocoder.yudao.module.groupbuy.enums.ErrorCodeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Validated
@Slf4j
public class GroupBuyActivityServiceImpl implements GroupBuyActivityService {

    @Resource
    private GroupBuyActivityMapper activityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createActivity(@Valid GroupBuyActivityCreateReqVO reqVO) {
        validate(reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getGroupPrice(), reqVO.getOriginalPrice());
        GroupBuyActivityDO entity = GroupBuyActivityDO.builder()
                .name(reqVO.getName())
                .spuId(reqVO.getSpuId())
                .skuId(reqVO.getSkuId())
                .picUrl(reqVO.getPicUrl())
                .groupPrice(reqVO.getGroupPrice())
                .originalPrice(reqVO.getOriginalPrice())
                .userSize(reqVO.getUserSize())
                .stockTotal(reqVO.getStockTotal())
                .stockUsed(0)
                .singleLimit(reqVO.getSingleLimit())
                .startTime(reqVO.getStartTime())
                .endTime(reqVO.getEndTime())
                .expireMinutes(reqVO.getExpireMinutes())
                .status(0)
                .deptId(SecurityFrameworkUtils.getLoginUserDeptId())
                .build();
        activityMapper.insert(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateActivity(@Valid GroupBuyActivityUpdateReqVO reqVO) {
        GroupBuyActivityDO exist = validateActivityExists(reqVO.getId());
        if (exist.getStockUsed() > 0 && reqVO.getStockTotal() < exist.getStockUsed()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH,
                    "总库存不能小于已用库存 (" + exist.getStockUsed() + ")");
        }
        validate(reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getGroupPrice(), reqVO.getOriginalPrice());
        GroupBuyActivityDO update = GroupBuyActivityDO.builder()
                .id(reqVO.getId())
                .name(reqVO.getName())
                .spuId(reqVO.getSpuId())
                .skuId(reqVO.getSkuId())
                .picUrl(reqVO.getPicUrl())
                .groupPrice(reqVO.getGroupPrice())
                .originalPrice(reqVO.getOriginalPrice())
                .userSize(reqVO.getUserSize())
                .stockTotal(reqVO.getStockTotal())
                .singleLimit(reqVO.getSingleLimit())
                .startTime(reqVO.getStartTime())
                .endTime(reqVO.getEndTime())
                .expireMinutes(reqVO.getExpireMinutes())
                .status(reqVO.getStatus() != null ? reqVO.getStatus() : exist.getStatus())
                .build();
        activityMapper.updateById(update);
    }

    @Override
    public void closeActivity(Long id) {
        validateActivityExists(id);
        GroupBuyActivityDO update = new GroupBuyActivityDO();
        update.setId(id);
        update.setStatus(3);
        activityMapper.updateById(update);
    }

    @Override
    public void deleteActivity(Long id) {
        validateActivityExists(id);
        activityMapper.deleteById(id);
    }

    @Override
    public GroupBuyActivityDO getActivity(Long id) {
        return id == null ? null : activityMapper.selectById(id);
    }

    @Override
    public List<GroupBuyActivityDO> getActivityList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return activityMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GroupBuyActivityDO> getActivityPage(GroupBuyActivityPageReqVO pageReqVO) {
        return activityMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean decrStock(Long id, Integer count) {
        int rows = activityMapper.decrStock(id, count);
        return rows > 0;
    }

    @Override
    public void incrStock(Long id, Integer count) {
        GroupBuyActivityDO exist = activityMapper.selectById(id);
        if (exist == null) {
            return;
        }
        int newUsed = Math.max(0, exist.getStockUsed() - count);
        GroupBuyActivityDO update = new GroupBuyActivityDO();
        update.setId(id);
        update.setStockUsed(newUsed);
        activityMapper.updateById(update);
    }

    @Override
    public int refreshActivityStatus() {
        // 0→1 (未开始→进行中), 2→1 (已结束→进行中，延长结束时间后恢复), 0/1→2 (→已结束)
        LocalDateTime now = LocalDateTime.now();
        int updated = 0;
        // 未开始或已结束 → 进行中（start_time <= now < end_time）
        List<GroupBuyActivityDO> running = activityMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyActivityDO>()
                        .in(GroupBuyActivityDO::getStatus, 0, 2)
                        .le(GroupBuyActivityDO::getStartTime, now)
                        .gt(GroupBuyActivityDO::getEndTime, now));
        for (GroupBuyActivityDO a : running) {
            GroupBuyActivityDO u = new GroupBuyActivityDO();
            u.setId(a.getId());
            u.setStatus(1);
            activityMapper.updateById(u);
            updated++;
        }
        // 未开始或进行中 → 已结束（end_time <= now）
        List<GroupBuyActivityDO> ended = activityMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyActivityDO>()
                        .in(GroupBuyActivityDO::getStatus, 0, 1)
                        .le(GroupBuyActivityDO::getEndTime, now));
        for (GroupBuyActivityDO a : ended) {
            GroupBuyActivityDO u = new GroupBuyActivityDO();
            u.setId(a.getId());
            u.setStatus(2);
            activityMapper.updateById(u);
            updated++;
        }
        return updated;
    }

    private GroupBuyActivityDO validateActivityExists(Long id) {
        GroupBuyActivityDO exist = activityMapper.selectById(id);
        if (exist == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_EXISTS);
        }
        return exist;
    }

    private void validate(LocalDateTime start, LocalDateTime end, Integer groupPrice, Integer originalPrice) {
        if (end.isBefore(start)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_TIME_INVALID);
        }
        if (groupPrice > originalPrice) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_PRICE_INVALID);
        }
    }
}
