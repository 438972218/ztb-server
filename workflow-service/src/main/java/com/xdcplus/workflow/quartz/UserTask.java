package com.xdcplus.workflow.quartz;

import com.xdcplus.workflow.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 同步-用户任务
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
public class UserTask extends QuartzJobBean {

    @Autowired
    private SyncService syncService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        try {
            syncService.syncUser();
        } catch (Exception e) {
            log.error("syncUser {}", e.getMessage());
        }
    }
}
