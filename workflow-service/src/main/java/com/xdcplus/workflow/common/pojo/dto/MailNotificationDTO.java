package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.workflow.common.pojo.bean.MailBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 邮件通知DTO
 *
 * @author Rong.Jia
 * @date 2021/08/11 10:37:30
 */
@Data
@ApiModel("邮件通知参数对照对象")
public class MailNotificationDTO implements Serializable {

    private static final long serialVersionUID = 6792284857640265220L;

    /**
     * 邮件通知点, 未知：-1
     */
    @NotNull(message = "邮件通知点 不能为空")
    @ApiModelProperty(value = "邮件通知点, 未知：-1", required = true)
    private String point;

    /**
     * 表单ID, 未知：-1
     */
    @NotNull(message = "表单ID 不能为空")
    @ApiModelProperty(value = "表单ID, 未知：-1", required = true)
    private Long requestId;

    /**
     * 流转ID, 未知：-1
     */
    @NotNull(message = "流转ID 不能为空")
    @ApiModelProperty(value = "流转ID, 未知：-1", required = true)
    private Long flowId;

    /**
     * 接收人
     */
    @NotNull(message = "流转ID 不能为空")
    @ApiModelProperty(value = "接收人", required = true)
    private List<@Valid MailBean> to;

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
