package cn.iocoder.yudao.module.groupbuy.controller.admin.head;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.*;
import cn.iocoder.yudao.module.groupbuy.convert.head.GroupBuyHeadConvert;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
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

@Tag(name = "管理后台 - 社区拼团团单")
@RestController
@RequestMapping("/group-buy/head")
@Validated
public class GroupBuyHeadController {

    @Resource
    private GroupBuyHeadService headService;

    @GetMapping("/page")
    @Operation(summary = "获得团单分页")
    @PreAuthorize("@ss.hasPermission('group-buy:head:query')")
    public CommonResult<PageResult<GroupBuyHeadItemRespVO>> getHeadPage(@Valid GroupBuyHeadPageReqVO pageVO) {
        PageResult<GroupBuyHeadDO> pageResult = headService.getHeadPage(pageVO);
        return success(GroupBuyHeadConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得团单详情（含成员）")
    @Parameter(name = "id", description = "团单 ID", required = true)
    @PreAuthorize("@ss.hasPermission('group-buy:head:query')")
    public CommonResult<GroupBuyHeadDetailRespVO> getHeadDetail(@RequestParam("id") Long id) {
        return success(headService.getHeadDetail(id));
    }

    @GetMapping("/list-by-ids")
    @Operation(summary = "通过 ID 集合获得团单列表")
    @PreAuthorize("@ss.hasPermission('group-buy:head:query')")
    public CommonResult<List<GroupBuyHeadItemRespVO>> getHeadListByIds(@RequestParam("ids") List<Long> ids) {
        List<GroupBuyHeadDO> list = headService.getHeadList(ids);
        return success(GroupBuyHeadConvert.INSTANCE.convertList(list));
    }
}
