package cn.iocoder.yudao.module.groupbuy.service.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.groupbuy.service.activity.GroupBuyActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
@Slf4j
public class GroupBuyActivityStatusJob implements JobHandler {

    @Resource
    private GroupBuyActivityService activityService;

    @Override
    @TenantJob
    public String execute(String param) {
        int updated = activityService.refreshActivityStatus();
        log.info("[GroupBuyActivityStatusJob] refreshed activities={}", updated);
        return String.format("刷新活动状态 %d 个", updated);
    }
}
