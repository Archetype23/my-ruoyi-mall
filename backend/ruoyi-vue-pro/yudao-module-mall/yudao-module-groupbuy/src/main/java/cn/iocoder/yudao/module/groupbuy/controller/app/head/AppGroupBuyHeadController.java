package cn.iocoder.yudao.module.groupbuy.controller.app.head;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadItemRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyJoinReqVO;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户端 - 社区拼团团单")
@RestController
@RequestMapping("/group-buy/head")
@Validated
public class AppGroupBuyHeadController {

    @Resource
    private GroupBuyHeadService headService;

    @PostMapping("/open")
    @Operation(summary = "开团（团长发起）")
    public CommonResult<Long> openHead(@Valid @RequestBody AppGroupBuyJoinReqVO reqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(headService.openHead(userId, reqVO));
    }

    @PostMapping("/join")
    @Operation(summary = "参团（加入已有团）")
    public CommonResult<Long> joinHead(@Valid @RequestBody AppGroupBuyJoinReqVO reqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(headService.joinHead(userId, reqVO));
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获取团单详情")
    public CommonResult<AppGroupBuyHeadDetailRespVO> getDetail(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(headService.getAppHeadDetail(id, userId));
    }

    @GetMapping("/my")
    @Operation(summary = "我的拼团")
    public CommonResult<List<AppGroupBuyHeadItemRespVO>> getMyHeads(
            @RequestParam(value = "status", required = false) Integer status) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(headService.getMyHeads(userId, status));
    }
}
