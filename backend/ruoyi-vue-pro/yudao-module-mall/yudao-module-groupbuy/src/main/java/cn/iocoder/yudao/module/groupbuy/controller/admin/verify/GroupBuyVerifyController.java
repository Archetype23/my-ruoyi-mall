package cn.iocoder.yudao.module.groupbuy.controller.admin.verify;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.verify.vo.GroupBuyVerifyItemRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.verify.vo.GroupBuyVerifyReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyMemberDO;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 社区拼团核销")
@RestController
@RequestMapping("/group-buy/verify")
@Validated
public class GroupBuyVerifyController {

    @Resource
    private GroupBuyHeadService headService;

    @GetMapping("/list")
    @Operation(summary = "获取可核销列表")
    @PreAuthorize("@ss.hasPermission('group-buy:verify:query')")
    public CommonResult<List<GroupBuyVerifyItemRespVO>> getVerifyList(
            @RequestParam(value = "activityId", required = false) Long activityId,
            @RequestParam(value = "keyword", required = false) String keyword) {
        Long deptId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId();
        List<GroupBuyMemberDO> members = headService.getVerifyList(deptId, activityId, keyword, 50);
        List<GroupBuyVerifyItemRespVO> result = new ArrayList<>();
        for (GroupBuyMemberDO m : members) {
            GroupBuyVerifyItemRespVO vo = new GroupBuyVerifyItemRespVO();
            vo.setMemberId(m.getId());
            vo.setHeadId(m.getHeadId());
            vo.setUserId(m.getUserId());
            vo.setUserNickname(m.getNickname());
            vo.setUserAvatar(m.getAvatar());
            vo.setOrderId(m.getOrderId());
            vo.setPickUpVerifyCode(m.getOrderId() == null ? "" : m.getOrderId().toString());
            vo.setOrderStatus(m.getStatus());
            result.add(vo);
        }
        return success(result);
    }

    @PostMapping("/confirm")
    @Operation(summary = "核销订单")
    @PreAuthorize("@ss.hasPermission('group-buy:verify:operate')")
    public CommonResult<Boolean> confirmVerify(@Valid @RequestBody GroupBuyVerifyReqVO reqVO) {
        Long deptId = cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId();
        GroupBuyMemberDO member = headService.findMemberByVerifyCode(deptId, reqVO.getVerifyCode());
        if (member == null) {
            return success(false);
        }
        headService.confirmVerify(deptId, member.getId());
        return success(true);
    }
}
