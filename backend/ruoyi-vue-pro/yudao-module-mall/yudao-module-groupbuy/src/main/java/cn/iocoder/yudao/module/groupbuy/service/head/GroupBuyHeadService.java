package cn.iocoder.yudao.module.groupbuy.service.head;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadPageReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadItemRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyJoinReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyMemberDO;

import java.util.Collection;
import java.util.List;

/**
 * 拼团团单 Service
 */
public interface GroupBuyHeadService {

    /**
     * 开团 (团长发起)
     * @return 团单 ID
     */
    Long openHead(Long userId, AppGroupBuyJoinReqVO reqVO);

    /**
     * 参团 (非团长加入已有团)
     * @return 团单 ID
     */
    Long joinHead(Long userId, AppGroupBuyJoinReqVO reqVO);

    /**
     * 交易订单创建前校验社区拼团资格。
     */
    void validateJoinForOrder(Long userId, Long activityId, Long headId, Long skuId, Integer count);

    /**
     * 交易订单创建后，创建待支付团单/团成员，并返回最终团单 ID。
     */
    Long createPendingMemberForOrder(Long userId, Long orderId, Long activityId, Long headId, Long skuId, Integer count);

    /**
     * 用户支付成功后回调 - 标记已支付
     * 如果是最后一个名额，自动成团
     */
    void onMemberPaid(Long orderId);

    /**
     * 交易订单取消后，取消待支付团成员并释放活动库存。
     */
    void cancelPendingOrder(Long orderId, Integer count);

    /**
     * 团单详情 (管理后台)
     */
    GroupBuyHeadDetailRespVO getHeadDetail(Long id);

    /**
     * 团单详情 (用户端)
     */
    AppGroupBuyHeadDetailRespVO getAppHeadDetail(Long headId, Long currentUserId);

    /**
     * 团单分页 (管理后台)
     */
    PageResult<GroupBuyHeadDO> getHeadPage(GroupBuyHeadPageReqVO pageReqVO);

    /**
     * 用户端 - 我的拼团
     */
    List<AppGroupBuyHeadItemRespVO> getMyHeads(Long userId, Integer status);

    /**
     * 团长可核销的团成员 (管理后台)
     */
    List<GroupBuyMemberDO> getVerifyList(Long deptId, Long activityId, String keyword, int limit);

    /**
     * 通过核销码查找团成员
     */
    GroupBuyMemberDO findMemberByVerifyCode(Long deptId, String verifyCode);

    /**
     * 核销完成
     */
    void confirmVerify(Long deptId, Long memberId);

    /**
     * 团单过期任务 (定时调用)
     * @return [失败数, 关闭团单数]
     */
    int[] expireHeads(int batchSize);

    /**
     * 将单个超时团单标记失败，并对其中已支付成员逐个发起退款、回滚活动库存。
     * 独立事务执行，单个团单失败不影响其它团单。
     *
     * @return 本团单内成功发起退款的成员数
     */
    int failExpiredHead(Long headId);

    /**
     * 失败团单退款（针对单个成员，独立事务）。
     * 会发起真实退款单并回滚活动库存。
     */
    void refundFailedMember(GroupBuyMemberDO member, String reason);

    GroupBuyHeadDO getHead(Long id);

    List<GroupBuyHeadDO> getHeadList(Collection<Long> ids);

    List<GroupBuyMemberDO> getMembersByHeadId(Long headId);
}
