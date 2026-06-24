package cn.iocoder.yudao.module.groupbuy.controller.app.activity;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.app.activity.vo.AppGroupBuyActivityDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.activity.vo.AppGroupBuyActivityRespVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyActivityMapper;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyHeadMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户端 - 社区拼团活动")
@RestController
@RequestMapping("/group-buy/activity")
public class AppGroupBuyActivityController {

    @Resource
    private GroupBuyActivityMapper activityMapper;

    @Resource
    private GroupBuyHeadMapper headMapper;

    @GetMapping("/list")
    @Operation(summary = "获取进行中的拼团活动")
    @PermitAll
    public CommonResult<List<AppGroupBuyActivityRespVO>> list() {
        List<GroupBuyActivityDO> list = activityMapper.selectRunningList();
        List<AppGroupBuyActivityRespVO> result = new ArrayList<>();
        for (GroupBuyActivityDO a : list) {
            AppGroupBuyActivityRespVO vo = new AppGroupBuyActivityRespVO();
            vo.setId(a.getId());
            vo.setName(a.getName());
            vo.setSpuId(a.getSpuId());
            vo.setSkuId(a.getSkuId());
            vo.setPicUrl(a.getPicUrl());
            vo.setGroupPrice(a.getGroupPrice());
            vo.setOriginalPrice(a.getOriginalPrice());
            vo.setUserSize(a.getUserSize());
            vo.setStockRemain(a.getStockTotal() - a.getStockUsed());
            vo.setStartTime(a.getStartTime());
            vo.setEndTime(a.getEndTime());
            vo.setStatus(a.getStatus());
            vo.setActiveHeadCount(headMapper.selectInProgressByActivity(a.getId(), 100).size());
            result.add(vo);
        }
        return success(result);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获取活动详情 + 进行中的团单")
    @PermitAll
    public CommonResult<AppGroupBuyActivityDetailRespVO> getDetail(@RequestParam("id") Long id) {
        GroupBuyActivityDO a = activityMapper.selectById(id);
        if (a == null) {
            return success(null);
        }
        AppGroupBuyActivityDetailRespVO vo = new AppGroupBuyActivityDetailRespVO();
        vo.setId(a.getId());
        vo.setName(a.getName());
        vo.setSpuId(a.getSpuId());
        vo.setSkuId(a.getSkuId());
        vo.setPicUrl(a.getPicUrl());
        vo.setGroupPrice(a.getGroupPrice());
        vo.setOriginalPrice(a.getOriginalPrice());
        vo.setUserSize(a.getUserSize());
        vo.setStockRemain(a.getStockTotal() - a.getStockUsed());
        vo.setSingleLimit(a.getSingleLimit());
        vo.setStartTime(a.getStartTime());
        vo.setEndTime(a.getEndTime());
        vo.setExpireMinutes(a.getExpireMinutes());
        vo.setStatus(a.getStatus());
        List<GroupBuyHeadDO> heads = headMapper.selectInProgressByActivity(a.getId(), 5);
        List<AppGroupBuyActivityDetailRespVO.ActiveHead> activeHeads = new ArrayList<>();
        for (GroupBuyHeadDO h : heads) {
            AppGroupBuyActivityDetailRespVO.ActiveHead activeHead = new AppGroupBuyActivityDetailRespVO.ActiveHead();
            activeHead.setHeadId(h.getId());
            activeHead.setCurrentCount(h.getCurrentCount());
            activeHead.setUserSize(h.getUserSize());
            activeHead.setLeaderNickname(h.getLeaderNickname());
            activeHead.setLeaderAvatar(h.getLeaderAvatar());
            activeHead.setExpireInSeconds(Math.max(0, Duration.between(LocalDateTime.now(), h.getExpireTime()).getSeconds()));
            activeHeads.add(activeHead);
        }
        vo.setActiveHeads(activeHeads);
        return success(vo);
    }
}
