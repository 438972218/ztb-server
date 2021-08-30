package com.xdcplus.workflow.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 定时器工具类
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
public class QuartzUtils {

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     *
     * @param scheduler  调度器
     * @param quartzTask 定时任务信息类
     */
    public static void createScheduleJob(Scheduler scheduler, QuartzTask quartzTask) {
        try {
            //获取到定时任务的执行类  必须是类的绝对路径名称
            //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzTask.getJobClass());
            // 构建定时任务信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(quartzTask.getJobName()).build();
            // 设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzTask.getCronExpression());
            // 构建触发器trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzTask.getJobName()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            log.error("定时任务类路径出错：请输入类的绝对路径 {}", e.getMessage());
        } catch (SchedulerException e) {
            log.error("创建定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 根据任务名称暂停定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void pauseScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 根据任务名称恢复定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void resumeScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("启动定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void runOnce(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("运行定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 更新定时任务
     *
     * @param scheduler  调度器
     * @param quartzTask 定时任务信息类
     */
    public static void updateScheduleJob(Scheduler scheduler, QuartzTask quartzTask) {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzTask.getJobName());
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzTask.getCronExpression());
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     *
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("删除定时任务出错 {}", e.getMessage());
        }
    }

    /**
     * 根据定时任务名称判断调度器中是否存在指定任务
     *
     * @param scheduler 调度器
     * @param jobName   作业名
     * @return 是否存在
     */
    public static Boolean checkExistsScheduleJob(Scheduler scheduler, String jobName) {

        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            return scheduler.checkExists(jobKey);
        } catch (SchedulerException e) {
            log.error("检查定时任务出错 {}", e.getMessage());
        }

        return Boolean.FALSE;
    }


}
