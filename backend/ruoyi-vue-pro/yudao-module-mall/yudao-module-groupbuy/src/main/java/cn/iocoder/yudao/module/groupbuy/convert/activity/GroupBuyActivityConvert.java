package cn.iocoder.yudao.module.groupbuy.convert.activity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.activity.vo.GroupBuyActivityRespVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupBuyActivityConvert {

    GroupBuyActivityConvert INSTANCE = Mappers.getMapper(GroupBuyActivityConvert.class);

    GroupBuyActivityRespVO convert(GroupBuyActivityDO bean);

    List<GroupBuyActivityRespVO> convertList(List<GroupBuyActivityDO> list);

    default PageResult<GroupBuyActivityRespVO> convertPage(PageResult<GroupBuyActivityDO> page) {
        if (page == null) {
            return null;
        }
        PageResult<GroupBuyActivityRespVO> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setList(convertList(page.getList()));
        return result;
    }
}
