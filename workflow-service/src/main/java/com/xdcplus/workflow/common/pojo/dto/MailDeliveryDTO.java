package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 邮件通知点信息DTO
 *
 * @author Rong.Jia
 * @date 2021/08/10 10:26:37
 */
@ApiModel("邮件通知点信息 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class MailDeliveryDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3171856067199496319L;

    /**
     * 模板ID
     */
    @NotNull(message = "模板ID 不能为空")
    @ApiModelProperty(value = "模板ID", required = true)
    private Long mailTemplateId;

    /**
     * 发送节点
     */
    @NotBlank(message = "发送节点 不能为空")
    @ApiModelProperty(value = "发送节点", required = true)
    private String sendingPoint;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    private String mailSubject;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人")
    private List<MailBean> to;

    /**
     * 抄送人
     */
    @ApiModelProperty(value = "抄送人")
    private List<MailBean> cc;

    /**
     * 密送人
     */
    @ApiModelProperty(value = "密送人")
    private List<MailBean> bcc;

    /**
     * 回复人
     */
    @ApiModelProperty(value = "回复人")
    private List<MailBean> reply;

















}
