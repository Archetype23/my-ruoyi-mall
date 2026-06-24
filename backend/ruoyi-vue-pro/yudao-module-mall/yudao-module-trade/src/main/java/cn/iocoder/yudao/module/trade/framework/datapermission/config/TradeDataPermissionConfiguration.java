package cn.iocoder.yudao.module.trade.framework.datapermission.config;

import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import cn.iocoder.yudao.module.trade.dal.dataobject.delivery.DeliveryPickUpStoreDO;
import cn.iocoder.yudao.module.trade.dal.dataobject.order.TradeOrderDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TradeDataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer tradeDeptDataPermissionRuleCustomizer() {
        return rule -> {
            rule.addDeptColumn(TradeOrderDO.class);
            rule.addDeptColumn(DeliveryPickUpStoreDO.class);
        };
    }
}
