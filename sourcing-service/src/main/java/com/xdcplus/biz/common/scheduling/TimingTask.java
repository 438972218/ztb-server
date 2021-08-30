package com.xdcplus.biz.common.scheduling;

import com.xdcplus.biz.service.PaidSheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("pro")
public class TimingTask {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private PaidSheetService paidSheetService;

    /**
     * 刷新（进行中/暂停）竞价单信息
     * 定时：每5秒刷新一次
     */
    @Scheduled(fixedDelay = 5 * 1000)
    public void refreshPaidSheetCache() {

        log.info("refreshPaidSheetCache {}", System.currentTimeMillis());

        taskExecutor.execute(() -> paidSheetService.queryPaidSheetByMonitor());

    }

    /**
     * 监听竞价单开始时间
     * 定时：每1秒刷新一次
     */
    @Scheduled(fixedDelay = 1 * 1000)
    public void refreshStartPaidSheet() {

        log.info("refreshStartPaidSheet {}", System.currentTimeMillis());

        taskExecutor.execute(() -> paidSheetService.startPaidSheet());

    }

}
