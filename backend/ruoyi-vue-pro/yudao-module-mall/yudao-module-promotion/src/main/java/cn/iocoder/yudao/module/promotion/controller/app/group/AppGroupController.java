package cn.iocoder.yudao.module.promotion.controller.app.group;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.controller.app.combination.vo.activity.AppCombinationActivityRespVO;
import cn.iocoder.yudao.module.promotion.controller.app.combination.vo.record.AppCombinationRecordDetailRespVO;
import cn.iocoder.yudao.module.promotion.controller.app.group.vo.AppGroupBaseReqVO;
import cn.iocoder.yudao.module.promotion.controller.app.group.vo.AppGroupCreateReqVO;
import cn.iocoder.yudao.module.promotion.controller.app.group.vo.AppGroupJoinReqVO;
import cn.iocoder.yudao.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import cn.iocoder.yudao.module.promotion.dal.dataobject.combination.CombinationProductDO;
import cn.iocoder.yudao.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import cn.iocoder.yudao.module.promotion.service.combination.CombinationActivityService;
import cn.iocoder.yudao.module.promotion.service.combination.CombinationRecordService;
import cn.iocoder.yudao.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.promotion.convert.combination.CombinationActivityConvert.INSTANCE;

@Tag(name = "用户 App - 团购")
@RestController
@RequestMapping({"/promotion/group", "/api/group"})
@Validated
public class AppGroupController {

    @Resource
    private CombinationActivityService combinationActivityService;
    @Resource
    private CombinationRecordService combinationRecordService;

    @PostMapping("/create")
    @Operation(summary = "发起团购")
    public CommonResult<Long> createGroup(@Valid @RequestBody AppGroupCreateReqVO reqVO) {
        return success(createGroupRecord(reqVO, null).getId());
    }

    @PostMapping("/join")
    @Operation(summary = "参与团购")
    public CommonResult<Long> joinGroup(@Valid @RequestBody AppGroupJoinReqVO reqVO) {
        return success(createGroupRecord(reqVO, reqVO.getHeadId()).getId());
    }

    @GetMapping("/list")
    @Operation(summary = "获取团购列表")
    @Parameter(name = "goodsId", description = "商品编号", required = true, example = "2048")
    @PermitAll
    public CommonResult<List<AppCombinationActivityRespVO>> getGroupList(@RequestParam("goodsId") Long goodsId) {
        CombinationActivityDO activity = combinationActivityService.getMatchCombinationActivityBySpuId(goodsId);
        if (activity == null) {
            return success(Collections.emptyList());
        }
        List<CombinationProductDO> products = combinationActivityService.getCombinationProductsByActivityId(activity.getId());
        return success(INSTANCE.convertAppList(Collections.singletonList(activity), products, Collections.emptyList()));
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "获取团购详情")
    @Parameter(name = "id", description = "团购记录编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCombinationRecordDetailRespVO> getGroupDetail(@PathVariable("id") Long id) {
        CombinationRecordDO record = combinationRecordService.getCombinationRecordById(id);
        if (record == null) {
            return success(null);
        }

        CombinationRecordDO headRecord;
        List<CombinationRecordDO> memberRecords;
        if (CombinationRecordDO.HEAD_ID_GROUP.equals(record.getHeadId())) {
            headRecord = record;
            memberRecords = combinationRecordService.getCombinationRecordListByHeadId(record.getId());
        } else {
            headRecord = combinationRecordService.getCombinationRecordById(record.getHeadId());
            memberRecords = combinationRecordService.getCombinationRecordListByHeadId(headRecord.getId());
        }
        return success(INSTANCE.convert(getLoginUserId(), headRecord, memberRecords));
    }

    private CombinationRecordDO createGroupRecord(AppGroupBaseReqVO reqVO, Long headId) {
        CombinationRecordCreateReqDTO reqDTO = new CombinationRecordCreateReqDTO();
        reqDTO.setActivityId(reqVO.getActivityId());
        reqDTO.setSpuId(reqVO.getGoodsId());
        reqDTO.setSkuId(reqVO.getSkuId());
        reqDTO.setCount(reqVO.getCount());
        reqDTO.setOrderId(reqVO.getOrderId());
        reqDTO.setUserId(getLoginUserId());
        reqDTO.setHeadId(headId);
        reqDTO.setCombinationPrice(reqVO.getCombinationPrice());
        return combinationRecordService.createCombinationRecord(reqDTO);
    }

}
