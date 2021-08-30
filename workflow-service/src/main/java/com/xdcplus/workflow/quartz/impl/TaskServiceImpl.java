package com.xdcplus.workflow.quartz.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.workflow.quartz.*;
import com.xdcplus.workflow.service.LdapService;
import com.xdcplus.ztb.common.cron.CronBuilder;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private LdapService ldapService;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void startTask() {

        LdapVO ldapVO = ldapService.findLdap();
        if (ObjectUtil.isNotNull(ldapVO)
                && Validator.equal(Convert.toByte(NumberConstant.ONE), ldapVO.getEnabled())) {

            Byte syncOrganization = ldapVO.getSyncOrganization();
            Byte syncPerson = ldapVO.getSyncPerson();

            if (Validator.equal(Convert.toByte(NumberConstant.ONE), syncOrganization)) {
                startTask(createQuartzTask(DepartmentTask.class,
                        assemblyCron(ldapVO.getFrequency())));
            }

            if (Validator.equal(Convert.toByte(NumberConstant.ONE), syncPerson)) {
                QuartzTask quartzTask = createQuartzTask(UserTask.class,
                        assemblyCron(ldapVO.getFrequency()));
                startTask(quartzTask);
            }

        }

    }

    /**
     * 组装CRON 表达式
     *
     * @param time 时间
     * @return {@link String}  CRON 表达式
     */
    private String assemblyCron(String time) {

        String cron = CronBuilder.builder().minutes(NumberConstant.ZERO, NumberConstant.ONE, Boolean.FALSE).build();
        if (StrUtil.isNotBlank(time)) {
            List<String> timeList = StrUtil.split(time, StrUtil.C_COMMA);
            if (CollectionUtil.isNotEmpty(timeList) && timeList.size() == NumberConstant.SEVEN) {
                cron = CronBuilder.builder()
                        .seconds(getTime(timeList.get(0)))
                        .minutes(getTime(timeList.get(1)))
                        .hours(getTime(timeList.get(2)))
                        .dayOfMonth(getTime(timeList.get(3)))
                        .month(getTime(timeList.get(4)))
                        .dayOfWeek(getTime(timeList.get(5)))
                        .year(getTime(timeList.get(6)))
                        .build();
            }
        }

        return cron;
    }

    /**
     * 查询时间
     *
     * @param time 时间
     * @return {@link Integer} 时间
     */
    private Integer getTime(String time) {

        if (StrUtil.isNotBlank(time) && StrUtil.equals(Convert.toStr(NumberConstant.A_NEGATIVE), time)) {
            return Convert.toInt(time.trim());
        }

        return -1;
    }

    /**
     * 创建定时任务
     *
     * @param clazz     clazz
     * @param frequency 频率
     * @return {@link QuartzTask} 任务信息
     */
    private QuartzTask createQuartzTask(Class<? extends Job> clazz, String frequency) {

        return QuartzTask.builder()
                .cronExpression(frequency)
                .id(IdUtil.fastSimpleUUID())
                .jobClass(clazz.getName())
                .jobName(clazz.getSimpleName())
                .build();

    }

    /**
     * 开始任务
     *
     * @param quartzTask 任务信息
     */
    private void startTask(QuartzTask quartzTask) {

        String jobName = quartzTask.getJobName();
        if (QuartzUtils.checkExistsScheduleJob(scheduler, jobName)) {
            QuartzUtils.deleteScheduleJob(scheduler, jobName);
        }

        QuartzUtils.createScheduleJob(scheduler, quartzTask);
    }

}
