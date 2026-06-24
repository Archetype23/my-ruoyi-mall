package cn.iocoder.yudao.module.groupbuy.convert.head;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.groupbuy.controller.admin.head.vo.GroupBuyHeadItemRespVO;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyHeadDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupBuyHeadConvert {

    GroupBuyHeadConvert INSTANCE = Mappers.getMapper(GroupBuyHeadConvert.class);

    GroupBuyHeadItemRespVO convert(GroupBuyHeadDO bean);

    List<GroupBuyHeadItemRespVO> convertList(List<GroupBuyHeadDO> list);

    default PageResult<GroupBuyHeadItemRespVO> convertPage(PageResult<GroupBuyHeadDO> page) {
        if (page == null) {
            return null;
        }
        PageResult<GroupBuyHeadItemRespVO> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setList(convertList(page.getList()));
        return result;
    }
}
