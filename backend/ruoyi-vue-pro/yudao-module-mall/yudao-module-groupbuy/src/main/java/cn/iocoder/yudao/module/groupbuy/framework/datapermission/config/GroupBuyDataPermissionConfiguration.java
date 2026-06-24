package cn.iocoder.yudao.module.groupbuy.framework.datapermission.config;

import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import cn.iocoder.yudao.module.groupbuy.dal.dataobject.GroupBuyActivityDO;
import cn.iocoder.yudao.module.product.dal.dataobject.spu.ProductSpuDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GroupBuyDataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer groupBuyDeptDataPermissionRuleCustomizer() {
        return rule -> {
            rule.addDeptColumn(ProductSpuDO.class);
            rule.addDeptColumn(GroupBuyActivityDO.class);
        };
    }
}
