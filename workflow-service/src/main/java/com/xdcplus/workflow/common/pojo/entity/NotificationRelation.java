package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 通知关系
 *
 * @author Rong.Jia
 * @date 2021/08/12 10:40:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_notification_relation")
public class NotificationRelation implements Serializable {

    private static final long serialVersionUID = 1724313240644167272L;

    /**
     *  ID
     */
    private Long id;

    /**
     *   表单ID
     */
    private Long requestId;

    /**
     *   流转ID
     */
    private Long flowId;

    /**
     *  通知历史ID
     */
    private Long historyId;
















}
