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
 * 邮件通知点信息VO
 *
 * @author Rong.Jia
 * @date 2021/08/10 10:24:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("邮件通知点信息 对照对象")
public class MailDeliveryVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 95327279325364789L;

    /**
     * 模板信息
     */
    @ApiModelProperty("模板信息")
    private MailTemplateVO mailTemplate;

    /**
     * 发送节点
     */
    @ApiModelProperty("发送节点")
    private String sendingPoint;

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
