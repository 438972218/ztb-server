package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 通知历史VO
 *
 * @author Rong.Jia
 * @date 2021/08/11 13:07:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("通知历史信息 对照对象")
public class NotificationHistoryVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -4564525586401245769L;

    /**
     * 通知类型，1：邮件，2：微信
     */
    @ApiModelProperty("通知类型，详见字典")
    private Byte type;

    /**
     * 通知发送点
     */
    @ApiModelProperty("通知发送点")
    private String point;

    /**
     * 消息ID
     */
    @ApiModelProperty("消息ID")
    private String messageId;

    /**
     * 通知内容
     */
    @ApiModelProperty("通知内容")
    private String content;

    /**
     * 邮件主题
     */
    @ApiModelProperty("邮件主题")
    private String mailSubject;

    /**
     * 发送人
     */
    @ApiModelProperty("邮件主题")
    private MailBean from;

    /**
     * 接收人
     */
    @ApiModelProperty("接收人")
    private List<MailBean> to;

    /**
     * 抄送人
     */
    @ApiModelProperty("抄送人")
    private List<MailBean> cc;

    /**
     * 密送人
     */
    @ApiModelProperty("密送人")
    private List<MailBean> bcc;

    /**
     * 回复人
     */
    @ApiModelProperty("回复人")
    private List<MailBean> reply;















}
