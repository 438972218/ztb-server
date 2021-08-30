package com.xdcplus.workflow.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 组织的任务
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
public class OrganizationTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

    }
}
