package cn.iocoder.yudao.module.groupbuy.service.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityCreateReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityPageReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityUpdateReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 拼团活动 Service
 */
public interface GroupBuyActivityService {

    Long createActivity(@Valid GroupBuyActivityCreateReqVO createReqVO);

    void updateActivity(@Valid GroupBuyActivityUpdateReqVO updateReqVO);

    void closeActivity(Long id);

    void deleteActivity(Long id);

    GroupBuyActivityDO getActivity(Long id);

    List<GroupBuyActivityDO> getActivityList(Collection<Long> ids);

    PageResult<GroupBuyActivityDO> getActivityPage(GroupBuyActivityPageReqVO pageReqVO);

    /**
     * 原子扣减库存 (并发安全)
     * @return true=扣减成功, false=库存不足
     */
    boolean decrStock(Long id, Integer count);

    /**
     * 释放库存 (失败/取消时)
     */
    void incrStock(Long id, Integer count);

    /**
     * 自动更新活动状态 (start_time/end_time → 0/1/2)
     */
    int refreshActivityStatus();
}
