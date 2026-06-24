package cn.iocoder.yudao.module.groupbuy.service.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.groupbuy.service.head.GroupBuyHeadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
@Slf4j
public class GroupBuyHeadExpireJob implements JobHandler {

    @Resource
    private GroupBuyHeadService headService;

    @Override
    @TenantJob
    public String execute(String param) {
        int[] result = headService.expireHeads(100);
        log.info("[GroupBuyExpireJob] expired heads={} refunded members={}", result[0], result[1]);
        return String.format("过期团单 %d 个, 退款成员 %d 个", result[0], result[1]);
    }
}
