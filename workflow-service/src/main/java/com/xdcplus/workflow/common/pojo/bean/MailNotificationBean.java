package com.xdcplus.workflow.common.pojo.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件通知DTO
 *
 * @author Rong.Jia
 * @date 2021/08/11 10:37:30
 */
@Data
public class MailNotificationBean implements Serializable {

    private static final long serialVersionUID = 6792284857640265220L;

    /**
     * 邮件通知点
     */
    private String point;

    /**
     * 表单ID
     */
    private Long requestId;

    /**
     * 流转ID
     */
    private Long flowId;

    /**
     * 邮件内容
     */
    private String html;

    /**
     * 邮件主题
     */
    private String mailSubject;

    /**
     * 接收人
     */
    private List<MailBean> to;

    /**
     * 抄送人
     */
    private List<MailBean> cc;

    /**
     * 密送人
     */
    private List<MailBean> bcc;

    /**
     * 回复人
     */
    private List<MailBean> reply;


}
