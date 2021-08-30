package com.xdcplus.workflow.quartz;

import com.xdcplus.workflow.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 同步- 部门任务
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
public class DepartmentTask extends QuartzJobBean {

    @Autowired
    private SyncService syncService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        try {
            syncService.syncDepartment();
        } catch (Exception e) {
            log.error("syncDepartment {}", e.getMessage());
        }

    }
}
