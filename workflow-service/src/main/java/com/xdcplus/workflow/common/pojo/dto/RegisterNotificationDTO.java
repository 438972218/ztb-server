package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.workflow.common.pojo.bean.MailBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 注册通知 DTO
 *
 * @author Rong.Jia
 * @date 2021/08/11 10:37:30
 */
@Data
@ApiModel("注册通知 对照对象")
public class RegisterNotificationDTO implements Serializable {

    private static final long serialVersionUID = 6792284857640265220L;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称 不能为空")
    @ApiModelProperty(value = "用户名称", required = true)
    private String username;

    /**
     * 系统登录账号
     */
    @NotBlank(message = "系统登录账号 不能为空")
    @ApiModelProperty(value = "系统登录账号", required = true)
    private String account;

    /**
     * 系统登录密码
     */
    @NotBlank(message = "系统登录密码 不能为空")
    @ApiModelProperty(value = "系统登录密码", required = true)
    private String password;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    private String mailSubject;

    /**
     * 发送人
     */
    @ApiModelProperty(value = "发送人")
    private MailBean from;

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
