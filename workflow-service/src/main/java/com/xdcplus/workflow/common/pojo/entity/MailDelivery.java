package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邮件发送点信息
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_mail_delivery")
public class MailDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 发送节点
     */
    private String sendingPoint;

    /**
     * 邮件主题
     */
    private String mailSubject;

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
