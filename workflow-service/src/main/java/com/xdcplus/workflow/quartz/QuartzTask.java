package com.xdcplus.workflow.quartz;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 定时器任务
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Data
@Builder
public class QuartzTask implements Serializable {

    private static final long serialVersionUID = 5290730181686025905L;

    /**
     * 任务id
     */
    private String id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务执行类
     */
    private String jobClass;

    /**
     * 任务状态 启动还是暂停
     */
    private Integer status;

    /**
     * 任务运行时间表达式
     */
    private String cronExpression;

}
