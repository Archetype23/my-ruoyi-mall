package cn.iocoder.yudao.module.groupbuy.controller.admin.activity;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.*;
import cn.iocoder.yudao.module.groupbuy.convert.activity.GroupBuyActivityConvert;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.service.activity.GroupBuyActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 社区拼团活动")
@RestController
@RequestMapping("/group-buy/activity")
@Validated
public class GroupBuyActivityController {

    @Resource
    private GroupBuyActivityService activityService;

    @PostMapping("/create")
    @Operation(summary = "创建拼团活动")
    @PreAuthorize("@ss.hasPermission('group-buy:activity:create')")
    public CommonResult<Long> createActivity(@Valid @RequestBody GroupBuyActivityCreateReqVO reqVO) {
        return success(activityService.createActivity(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新拼团活动")
    @PreAuthorize("@ss.hasPermission('group-buy:activity:update')")
    public CommonResult<Boolean> updateActivity(@Valid @RequestBody GroupBuyActivityUpdateReqVO reqVO) {
        activityService.updateActivity(reqVO);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "关闭拼团活动")
    @PreAuthorize("@ss.hasPermission('group-buy:activity:close')")
    public CommonResult<Boolean> closeActivity(@RequestParam("id") Long id) {
        activityService.closeActivity(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除拼团活动")
    @PreAuthorize("@ss.hasPermission('group-buy:activity:delete')")
    public CommonResult<Boolean> deleteActivity(@RequestParam("id") Long id) {
        activityService.deleteActivity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得拼团活动")
    @Parameter(name = "id", description = "活动编号", required = true)
    @PreAuthorize("@ss.hasPermission('group-buy:activity:query')")
    public CommonResult<GroupBuyActivityRespVO> getActivity(@RequestParam("id") Long id) {
        GroupBuyActivityDO activity = activityService.getActivity(id);
        return success(GroupBuyActivityConvert.INSTANCE.convert(activity));
    }

    @GetMapping("/list-by-ids")
    @Operation(summary = "通过 ID 集合获得拼团活动列表")
    @Parameter(name = "ids", description = "活动编号集合", required = true)
    @PreAuthorize("@ss.hasPermission('group-buy:activity:query')")
    public CommonResult<List<GroupBuyActivityRespVO>> getActivityListByIds(@RequestParam("ids") List<Long> ids) {
        List<GroupBuyActivityDO> list = activityService.getActivityList(ids);
        return success(GroupBuyActivityConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得拼团活动分页")
    @PreAuthorize("@ss.hasPermission('group-buy:activity:query')")
    public CommonResult<PageResult<GroupBuyActivityRespVO>> getActivityPage(@Valid GroupBuyActivityPageReqVO pageVO) {
        PageResult<GroupBuyActivityDO> pageResult = activityService.getActivityPage(pageVO);
        return success(GroupBuyActivityConvert.INSTANCE.convertPage(pageResult));
    }
}
