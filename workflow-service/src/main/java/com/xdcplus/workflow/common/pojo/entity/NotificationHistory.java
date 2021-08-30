package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知历史
 *
 * @author Rong.Jia
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_notification_history")
public class NotificationHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 通知类型，1：邮件，2：微信
     */
    private Byte type;

    /**
     * 通知发送点
     */
    private String point;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 主题
     */
    private String subject;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 发送人
     */
    private String mailFrom;

    /**
     * 接收人
     */
    private String mailTo;

    /**
     * 抄送人
     */
    private String mailCc;

    /**
     * 密送人
     */
    private String mailBcc;

    /**
     * 回复人
     */
    private String mailReply;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 描述
     */
    private String description;


}
