package cn.iocoder.yudao.module.promotion.dal.mysql.kefu;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.promotion.dal.dataobject.kefu.KeFuConversationDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeFuConversationMapper extends BaseMapperX<KeFuConversationDO> {

    default List<KeFuConversationDO> selectConversationList(Long deptId, Long adminId) {
        return selectList(new LambdaQueryWrapperX<KeFuConversationDO>()
                .eq(KeFuConversationDO::getAdminDeleted, Boolean.FALSE)
                .eq(deptId != null, KeFuConversationDO::getDeptId, deptId)
                .and(adminId != null, w -> w
                    .eq(KeFuConversationDO::getAssignedTo, adminId)
                    .or().isNull(KeFuConversationDO::getAssignedTo))
                .orderByDesc(KeFuConversationDO::getCreateTime));
    }

    default void updateAdminUnreadMessageCountIncrement(Long id) {
        update(new LambdaUpdateWrapper<KeFuConversationDO>()
                .eq(KeFuConversationDO::getId, id)
                .setSql("admin_unread_message_count = admin_unread_message_count + 1"));
    }

    default KeFuConversationDO selectByUserId(Long userId) {
        return selectOne(KeFuConversationDO::getUserId, userId);
    }

    /** 轮询：找到部门里有效会话数最少的客服 */
    @Select("SELECT u.id FROM system_users u " +
            "JOIN system_user_role ur ON u.id = ur.user_id " +
            "JOIN system_role r ON ur.role_id = r.id " +
            "WHERE r.code = 'kefu_staff' AND u.dept_id = #{deptId} AND u.deleted = 0 " +
            "ORDER BY (SELECT COUNT(*) FROM promotion_kefu_conversation c " +
            "          WHERE c.assigned_to = u.id AND c.admin_deleted = 0 AND c.deleted = 0) ASC " +
            "LIMIT 1")
    Long selectNextKefu(@Param("deptId") Long deptId);
}
