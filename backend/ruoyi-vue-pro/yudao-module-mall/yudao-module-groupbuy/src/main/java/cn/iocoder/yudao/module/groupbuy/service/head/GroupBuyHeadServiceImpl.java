package cn.iocoder.yudao.module.groupbuy.service.head;

import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadPageReqVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadDetailRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyHeadItemRespVO;
import cn.iocoder.yudao.module.groupbuy.controller.app.head.vo.AppGroupBuyJoinReqVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyMemberDO;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyActivityMapper;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyHeadMapper;
import cn.iocoder.yudao.module.groupbuy.dal.mysql.GroupBuyMemberMapper;
import cn.iocoder.yudao.module.groupbuy.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.groupbuy.enums.GroupBuyHeadStatusEnum;
import cn.iocoder.yudao.module.groupbuy.enums.GroupBuyMemberStatusEnum;
import cn.iocoder.yudao.module.groupbuy.service.activity.GroupBuyActivityService;
import cn.iocoder.yudao.module.trade.api.order.TradeOrderApi;
import cn.iocoder.yudao.module.trade.api.order.dto.TradeOrderRespDTO;
import cn.iocoder.yudao.module.trade.enums.order.TradeOrderCancelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Validated
@Slf4j
public class GroupBuyHeadServiceImpl implements GroupBuyHeadService {

    @Resource
    private GroupBuyHeadMapper headMapper;

    @Resource
    private GroupBuyMemberMapper memberMapper;

    @Resource
    private GroupBuyActivityMapper activityMapper;

    @Resource
    private GroupBuyActivityService activityService;

    @Autowired
    private ObjectProvider<TradeOrderApi> tradeOrderApiProvider;

    /**
     * 自身代理引用：用于在过期任务中按团单维度走独立事务，
     * 保证单个团单/单个成员退款失败时不会回滚整批。
     */
    @Autowired
    private ObjectProvider<GroupBuyHeadService> selfProvider;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long openHead(Long userId, AppGroupBuyJoinReqVO reqVO) {
        if (reqVO.getHeadId() != null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_LEADER_CANNOT_JOIN_OTHER);
        }
        GroupBuyActivityDO activity = activityService.getActivity(reqVO.getActivityId());
        if (activity == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_EXISTS);
        }
        if (activity.getStatus() != 1) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STATUS_DISABLED);
        }
        if (LocalDateTime.now().isBefore(activity.getStartTime()) || LocalDateTime.now().isAfter(activity.getEndTime())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_IN_TIME);
        }
        if (!activityService.decrStock(reqVO.getActivityId(), reqVO.getCount())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH);
        }
        GroupBuyHeadDO head = GroupBuyHeadDO.builder()
                .activityId(activity.getId())
                .leaderUserId(userId)
                .userSize(activity.getUserSize())
                .currentCount(0)
                .status(GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus())
                .expireTime(LocalDateTime.now().plusMinutes(activity.getExpireMinutes()))
                .build();
        headMapper.insert(head);
        GroupBuyMemberDO member = GroupBuyMemberDO.builder()
                .headId(head.getId())
                .userId(userId)
                .isLeader(true)
                .orderId(0L)
                .status(GroupBuyMemberStatusEnum.PENDING.getStatus())
                .joinTime(LocalDateTime.now())
                .build();
        memberMapper.insert(member);
        return head.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long joinHead(Long userId, AppGroupBuyJoinReqVO reqVO) {
        if (reqVO.getHeadId() == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_NOT_EXISTS);
        }
        GroupBuyHeadDO head = headMapper.selectById(reqVO.getHeadId());
        if (head == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_NOT_EXISTS);
        }
        if (head.getStatus() != GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_STATUS_INVALID);
        }
        if (LocalDateTime.now().isAfter(head.getExpireTime())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_EXPIRED);
        }
        if (head.getCurrentCount() >= head.getUserSize()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_FULL);
        }
        GroupBuyMemberDO existing = memberMapper.selectByHeadIdAndUserId(head.getId(), userId);
        if (existing != null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_MEMBER_EXISTS);
        }
        GroupBuyActivityDO activity = activityService.getActivity(head.getActivityId());
        if (activity == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_EXISTS);
        }
        if (!activityService.decrStock(activity.getId(), reqVO.getCount())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH);
        }
        GroupBuyMemberDO member = GroupBuyMemberDO.builder()
                .headId(head.getId())
                .userId(userId)
                .isLeader(false)
                .orderId(0L)
                .status(GroupBuyMemberStatusEnum.PENDING.getStatus())
                .joinTime(LocalDateTime.now())
                .build();
        memberMapper.insert(member);
        return head.getId();
    }

    @Override
    public void validateJoinForOrder(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        GroupBuyActivityDO activity = activityService.getActivity(activityId);
        validateActivityForOrder(activity, skuId, count);
        if (headId == null) {
            return;
        }
        GroupBuyHeadDO head = validateHeadForJoin(headId, activity.getId());
        validateHeadCapacityAndUser(head, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPendingMemberForOrder(Long userId, Long orderId, Long activityId, Long headId, Long skuId, Integer count) {
        GroupBuyActivityDO activity = activityService.getActivity(activityId);
        validateActivityForOrder(activity, skuId, count);
        GroupBuyHeadDO head;
        boolean leader = headId == null;
        if (leader) {
            head = GroupBuyHeadDO.builder()
                    .activityId(activity.getId())
                    .leaderUserId(userId)
                    .userSize(activity.getUserSize())
                    .currentCount(0)
                    .status(GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus())
                    .expireTime(LocalDateTime.now().plusMinutes(activity.getExpireMinutes()))
                    .build();
            headMapper.insert(head);
        } else {
            head = validateHeadForJoin(headId, activity.getId());
            validateHeadCapacityAndUser(head, userId);
        }
        if (!activityService.decrStock(activity.getId(), count)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH);
        }
        GroupBuyMemberDO member = GroupBuyMemberDO.builder()
                .headId(head.getId())
                .userId(userId)
                .isLeader(leader)
                .orderId(orderId)
                .status(GroupBuyMemberStatusEnum.PENDING.getStatus())
                .joinTime(LocalDateTime.now())
                .build();
        memberMapper.insert(member);
        return head.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onMemberPaid(Long orderId) {
        if (orderId == null) {
            return;
        }
        List<GroupBuyMemberDO> members = memberMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyMemberDO>()
                        .eq(GroupBuyMemberDO::getOrderId, orderId));
        if (members.isEmpty()) {
            return;
        }
        GroupBuyMemberDO member = members.get(0);
        if (member.getStatus() != GroupBuyMemberStatusEnum.PENDING.getStatus()) {
            return;
        }
        memberMapper.updateStatus(member.getId(),
                GroupBuyMemberStatusEnum.PENDING.getStatus(),
                GroupBuyMemberStatusEnum.PAID.getStatus());
        GroupBuyHeadDO head = headMapper.selectById(member.getHeadId());
        if (head == null || head.getStatus() != GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus()) {
            return;
        }
        headMapper.incrCurrentCount(head.getId(), 1);
        GroupBuyHeadDO refreshed = headMapper.selectById(head.getId());
        if (refreshed != null && refreshed.getCurrentCount() >= refreshed.getUserSize()) {
            headMapper.markSuccess(refreshed.getId(), LocalDateTime.now());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPendingOrder(Long orderId, Integer count) {
        if (orderId == null) {
            return;
        }
        GroupBuyMemberDO member = memberMapper.selectByOrderId(orderId);
        if (member == null || member.getStatus() != GroupBuyMemberStatusEnum.PENDING.getStatus()) {
            return;
        }
        GroupBuyHeadDO head = headMapper.selectById(member.getHeadId());
        if (head != null) {
            activityService.incrStock(head.getActivityId(), count == null || count < 1 ? 1 : count);
        }
        memberMapper.updateStatus(member.getId(),
                GroupBuyMemberStatusEnum.PENDING.getStatus(),
                GroupBuyMemberStatusEnum.CANCELED.getStatus());
    }

    @Override
    public GroupBuyHeadDetailRespVO getHeadDetail(Long id) {
        GroupBuyHeadDO head = validateHeadExists(id);
        GroupBuyActivityDO activity = activityService.getActivity(head.getActivityId());
        GroupBuyHeadDetailRespVO vo = new GroupBuyHeadDetailRespVO();
        vo.setId(head.getId());
        vo.setActivityId(head.getActivityId());
        if (activity != null) {
            vo.setActivityName(activity.getName());
            vo.setActivityPicUrl(activity.getPicUrl());
            vo.setGroupPrice(activity.getGroupPrice());
            vo.setOriginalPrice(activity.getOriginalPrice());
        }
        vo.setLeaderUserId(head.getLeaderUserId());
        vo.setLeaderNickname(head.getLeaderNickname());
        vo.setLeaderAvatar(head.getLeaderAvatar());
        vo.setUserSize(head.getUserSize());
        vo.setCurrentCount(head.getCurrentCount());
        vo.setStatus(head.getStatus());
        vo.setStatusName(GroupBuyHeadStatusEnum.fromStatus(head.getStatus()));
        vo.setExpireTime(head.getExpireTime());
        vo.setSuccessTime(head.getSuccessTime());
        vo.setFailTime(head.getFailTime());
        vo.setFailReason(head.getFailReason());
        vo.setCreateTime(head.getCreateTime());
        List<GroupBuyMemberDO> memberList = memberMapper.selectListByHeadId(head.getId());
        List<GroupBuyHeadDetailRespVO.Member> members = new ArrayList<>();
        for (GroupBuyMemberDO m : memberList) {
            GroupBuyHeadDetailRespVO.Member dto = new GroupBuyHeadDetailRespVO.Member();
            dto.setId(m.getId());
            dto.setUserId(m.getUserId());
            dto.setNickname(m.getNickname());
            dto.setAvatar(m.getAvatar());
            dto.setIsLeader(m.getIsLeader());
            dto.setOrderId(m.getOrderId());
            dto.setStatus(m.getStatus());
            dto.setStatusName(GroupBuyMemberStatusEnum.fromStatus(m.getStatus()));
            dto.setJoinTime(m.getJoinTime());
            members.add(dto);
        }
        vo.setMembers(members);
        return vo;
    }

    @Override
    public AppGroupBuyHeadDetailRespVO getAppHeadDetail(Long headId, Long currentUserId) {
        GroupBuyHeadDO head = validateHeadExists(headId);
        GroupBuyActivityDO activity = activityService.getActivity(head.getActivityId());
        AppGroupBuyHeadDetailRespVO vo = new AppGroupBuyHeadDetailRespVO();
        vo.setId(head.getId());
        vo.setActivityId(head.getActivityId());
        if (activity != null) {
            vo.setActivityName(activity.getName());
            vo.setActivityPicUrl(activity.getPicUrl());
            vo.setGroupPrice(activity.getGroupPrice());
            vo.setOriginalPrice(activity.getOriginalPrice());
        }
        vo.setLeaderNickname(head.getLeaderNickname());
        vo.setLeaderAvatar(head.getLeaderAvatar());
        vo.setUserSize(head.getUserSize());
        vo.setCurrentCount(head.getCurrentCount());
        vo.setStatus(head.getStatus());
        vo.setExpireTime(head.getExpireTime());
        return vo;
    }

    @Override
    public PageResult<GroupBuyHeadDO> getHeadPage(GroupBuyHeadPageReqVO pageReqVO) {
        return headMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AppGroupBuyHeadItemRespVO> getMyHeads(Long userId, Integer status) {
        if (userId == null) {
            return Collections.emptyList();
        }
        List<GroupBuyMemberDO> myMemberships = memberMapper.selectListByUserId(userId);
        if (myMemberships.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> headIds = myMemberships.stream().map(GroupBuyMemberDO::getHeadId).distinct().toList();
        List<GroupBuyHeadDO> heads = headMapper.selectListByIds(headIds);
        if (status != null) {
            heads = heads.stream().filter(h -> status.equals(h.getStatus())).toList();
        }
        List<AppGroupBuyHeadItemRespVO> result = new ArrayList<>();
        for (GroupBuyHeadDO h : heads) {
            GroupBuyMemberDO membership = myMemberships.stream()
                    .filter(member -> member.getHeadId().equals(h.getId()))
                    .findFirst()
                    .orElse(null);
            GroupBuyActivityDO activity = activityService.getActivity(h.getActivityId());
            AppGroupBuyHeadItemRespVO vo = new AppGroupBuyHeadItemRespVO();
            vo.setId(h.getId());
            vo.setActivityId(h.getActivityId());
            if (activity != null) {
                vo.setActivityName(activity.getName());
                vo.setActivityPicUrl(activity.getPicUrl());
                vo.setGroupPrice(activity.getGroupPrice());
                vo.setOriginalPrice(activity.getOriginalPrice());
            }
            vo.setLeaderUserId(h.getLeaderUserId());
            vo.setLeaderNickname(h.getLeaderNickname());
            vo.setLeaderAvatar(h.getLeaderAvatar());
            if (membership != null) {
                vo.setIsLeader(membership.getIsLeader());
                vo.setOrderId(membership.getOrderId());
            }
            vo.setStatus(h.getStatus());
            vo.setStatusName(GroupBuyHeadStatusEnum.fromStatus(h.getStatus()));
            vo.setUserSize(h.getUserSize());
            vo.setCurrentCount(h.getCurrentCount());
            vo.setExpireTime(h.getExpireTime());
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<GroupBuyMemberDO> getVerifyList(Long deptId, Long activityId, String keyword, int limit) {
        // 按部门获取活动
        List<Long> activityIds = new ArrayList<>();
        if (deptId != null) {
            List<GroupBuyActivityDO> activities = activityMapper.selectList(
                    new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyActivityDO>()
                            .eq(GroupBuyActivityDO::getDeptId, deptId));
            activityIds = activities.stream().map(GroupBuyActivityDO::getId).toList();
            if (activityIds.isEmpty()) {
                return Collections.emptyList();
            }
        }
        List<GroupBuyHeadDO> heads = headMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyHeadDO>()
                        .in(deptId != null, GroupBuyHeadDO::getActivityId, activityIds)
                        .eq(activityId != null, GroupBuyHeadDO::getActivityId, activityId)
                        .in(GroupBuyHeadDO::getStatus,
                                GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus(),
                                GroupBuyHeadStatusEnum.SUCCESS.getStatus())
                        .orderByDesc(GroupBuyHeadDO::getId)
                        .last(limit > 0 ? "LIMIT " + limit : ""));
        if (heads.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> headIds = heads.stream().map(GroupBuyHeadDO::getId).toList();
        List<GroupBuyMemberDO> members = memberMapper.selectListByHeadIds(headIds);
        if (keyword != null && !keyword.isEmpty()) {
            members = members.stream()
                    .filter(m -> m.getOrderId() != null && m.getOrderId().toString().contains(keyword))
                    .toList();
        }
        return members;
    }

    @Override
    public GroupBuyMemberDO findMemberByVerifyCode(Long deptId, String verifyCode) {
        if (verifyCode == null || verifyCode.isEmpty()) {
            return null;
        }
        List<Long> activityIds = new ArrayList<>();
        if (deptId != null) {
            List<GroupBuyActivityDO> activities = activityMapper.selectList(
                    new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyActivityDO>()
                            .eq(GroupBuyActivityDO::getDeptId, deptId));
            activityIds = activities.stream().map(GroupBuyActivityDO::getId).toList();
        }
        List<GroupBuyHeadDO> heads = headMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<GroupBuyHeadDO>()
                        .in(deptId != null && !activityIds.isEmpty(), GroupBuyHeadDO::getActivityId, activityIds));
        if (heads.isEmpty()) {
            return null;
        }
        List<Long> headIds = heads.stream().map(GroupBuyHeadDO::getId).toList();
        List<GroupBuyMemberDO> members = memberMapper.selectListByHeadIds(headIds);
        return members.stream()
                .filter(m -> verifyCode.equals(m.getOrderId() != null ? m.getOrderId().toString() : ""))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmVerify(Long deptId, Long memberId) {
        if (memberId == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.MEMBER_NOT_EXISTS);
        }
        GroupBuyMemberDO member = memberMapper.selectById(memberId);
        if (member == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.MEMBER_NOT_EXISTS);
        }
        GroupBuyHeadDO head = headMapper.selectById(member.getHeadId());
        if (head == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.MEMBER_NOT_EXISTS);
        }
        // 校验该 admin 所属部门是否有权限核销此订单
        if (deptId != null) {
            GroupBuyActivityDO activity = activityMapper.selectById(head.getActivityId());
            if (activity == null || !deptId.equals(activity.getDeptId())) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.VERIFY_NOT_LEADER);
            }
        }
        if (member.getStatus() != GroupBuyMemberStatusEnum.PAID.getStatus()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.VERIFY_ORDER_NOT_WAIT_PICKUP);
        }
        memberMapper.updateStatus(member.getId(),
                GroupBuyMemberStatusEnum.PAID.getStatus(),
                GroupBuyMemberStatusEnum.CANCELED.getStatus());
    }

    @Override
    public int[] expireHeads(int batchSize) {
        // 注意：本方法不开启事务。每个团单通过 self 代理调用 failExpiredHead，
        // 走独立事务，保证某个团单（或其中某个成员退款）失败时不影响其它团单。
        int limit = batchSize > 0 ? batchSize : 100;
        List<GroupBuyHeadDO> expired = headMapper.selectExpiredInProgress(limit);
        if (expired.isEmpty()) {
            return new int[]{0, 0};
        }
        GroupBuyHeadService self = selfProvider.getObject();
        int headCount = 0;
        int memberCount = 0;
        for (GroupBuyHeadDO head : expired) {
            try {
                memberCount += self.failExpiredHead(head.getId());
                headCount++;
            } catch (Exception e) {
                // 单个团单处理失败：记录日志后继续处理下一个，等待下次任务重试
                log.error("[expireHeads] 团单 {} 关闭失败，将在下次任务重试", head.getId(), e);
            }
        }
        return new int[]{headCount, memberCount};
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int failExpiredHead(Long headId) {
        GroupBuyHeadDO head = headMapper.selectById(headId);
        // 并发安全：若已被其它任务/支付处理（不再进行中），直接跳过
        if (head == null || head.getStatus() != GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus()) {
            return 0;
        }
        headMapper.markFailed(head.getId(), LocalDateTime.now(), "成团超时");
        int refundCount = 0;
        List<GroupBuyMemberDO> members = memberMapper.selectListByHeadId(head.getId());
        for (GroupBuyMemberDO m : members) {
            // 待支付成员：取消并回滚库存（订单侧由 afterCancelOrder 处理，这里兜底处理无单成员）
            if (m.getStatus() == GroupBuyMemberStatusEnum.PENDING.getStatus()) {
                releasePendingMemberStock(head.getActivityId(), m);
                continue;
            }
            // 已支付成员：发起真实退款。独立事务，单个失败不影响整团关闭。
            if (m.getStatus() == GroupBuyMemberStatusEnum.PAID.getStatus()) {
                try {
                    selfProvider.getObject().refundFailedMember(m, "拼团失败自动退款");
                    refundCount++;
                } catch (Exception e) {
                    log.error("[failExpiredHead] 团单 {} 成员 {} 退款失败，等待下次重试",
                            head.getId(), m.getId(), e);
                }
            }
        }
        return refundCount;
    }

    /**
     * 释放待支付成员占用的活动库存，并将其标记为已取消。
     */
    private void releasePendingMemberStock(Long activityId, GroupBuyMemberDO member) {
        int count = resolveOrderProductCount(member.getOrderId());
        activityService.incrStock(activityId, count);
        memberMapper.updateStatus(member.getId(),
                GroupBuyMemberStatusEnum.PENDING.getStatus(),
                GroupBuyMemberStatusEnum.CANCELED.getStatus());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void refundFailedMember(GroupBuyMemberDO member, String reason) {
        log.warn("[refundFailedMember] memberId={} orderId={} reason={}", member.getId(), member.getOrderId(), reason);
        if (member.getOrderId() == null || member.getOrderId() <= 0) {
            // 无关联订单，直接置为已退款（无需真实退款）
            memberMapper.updateStatus(member.getId(),
                    GroupBuyMemberStatusEnum.PAID.getStatus(),
                    GroupBuyMemberStatusEnum.REFUNDED.getStatus());
            return;
        }
        // 1. 发起真实退款（cancelPaidOrder 内部会创建 PayRefund 退款单）
        tradeOrderApiProvider.getObject().cancelPaidOrder(member.getUserId(), member.getOrderId(),
                TradeOrderCancelTypeEnum.COMBINATION_CLOSE.getType());
        // 2. 回滚活动库存
        GroupBuyHeadDO head = headMapper.selectById(member.getHeadId());
        if (head != null) {
            activityService.incrStock(head.getActivityId(), resolveOrderProductCount(member.getOrderId()));
        }
        // 3. 标记成员已退款
        int rows = memberMapper.updateStatus(member.getId(),
                GroupBuyMemberStatusEnum.PAID.getStatus(),
                GroupBuyMemberStatusEnum.REFUNDED.getStatus());
        if (rows == 0) {
            log.warn("[refundFailedMember] memberId={} 状态已变更，跳过", member.getId());
        }
    }

    /**
     * 通过订单获取购买数量，用于回滚库存；失败时回退为 1，保证库存最少能释放一个名额。
     */
    private int resolveOrderProductCount(Long orderId) {
        if (orderId == null || orderId <= 0) {
            return 1;
        }
        try {
            TradeOrderRespDTO order = tradeOrderApiProvider.getObject().getOrder(orderId);
            if (order != null && order.getProductCount() != null && order.getProductCount() > 0) {
                return order.getProductCount();
            }
        } catch (Exception e) {
            log.warn("[resolveOrderProductCount] 查询订单 {} 失败，库存回滚按 1 处理", orderId, e);
        }
        return 1;
    }

    @Override
    public GroupBuyHeadDO getHead(Long id) {
        return id == null ? null : headMapper.selectById(id);
    }

    @Override
    public List<GroupBuyHeadDO> getHeadList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return headMapper.selectListByIds(ids);
    }

    @Override
    public List<GroupBuyMemberDO> getMembersByHeadId(Long headId) {
        return memberMapper.selectListByHeadId(headId);
    }

    private GroupBuyHeadDO validateHeadExists(Long id) {
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_NOT_EXISTS);
        }
        GroupBuyHeadDO head = headMapper.selectById(id);
        if (head == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_NOT_EXISTS);
        }
        return head;
    }

    private void validateActivityForOrder(GroupBuyActivityDO activity, Long skuId, Integer count) {
        if (activity == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_EXISTS);
        }
        if (activity.getStatus() != 1) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STATUS_DISABLED);
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(activity.getStartTime()) || now.isAfter(activity.getEndTime())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_NOT_IN_TIME);
        }
        if (!Objects.equals(activity.getSkuId(), skuId)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_SKU_NOT_EXISTS);
        }
        if (count == null || count < 1 || count > activity.getSingleLimit()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH);
        }
        if (activity.getStockUsed() + count > activity.getStockTotal()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ACTIVITY_STOCK_NOT_ENOUGH);
        }
    }

    private GroupBuyHeadDO validateHeadForJoin(Long headId, Long activityId) {
        GroupBuyHeadDO head = validateHeadExists(headId);
        if (!Objects.equals(head.getActivityId(), activityId)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_NOT_EXISTS);
        }
        if (head.getStatus() != GroupBuyHeadStatusEnum.IN_PROGRESS.getStatus()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_STATUS_INVALID);
        }
        if (LocalDateTime.now().isAfter(head.getExpireTime())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_EXPIRED);
        }
        return head;
    }

    private void validateHeadCapacityAndUser(GroupBuyHeadDO head, Long userId) {
        List<GroupBuyMemberDO> members = memberMapper.selectListByHeadId(head.getId());
        long activeCount = members.stream()
                .filter(member -> member.getStatus() == GroupBuyMemberStatusEnum.PENDING.getStatus()
                        || member.getStatus() == GroupBuyMemberStatusEnum.PAID.getStatus())
                .count();
        if (activeCount >= head.getUserSize()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_FULL);
        }
        boolean exists = members.stream()
                .anyMatch(member -> Objects.equals(member.getUserId(), userId)
                        && (member.getStatus() == GroupBuyMemberStatusEnum.PENDING.getStatus()
                        || member.getStatus() == GroupBuyMemberStatusEnum.PAID.getStatus()));
        if (exists) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.HEAD_MEMBER_EXISTS);
        }
    }
}
