package com.xdcplus.workflow.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 额外的邮件通知DTO
 *
 * @author Rong.Jia
 * @date 2021/08/25
 */
@ApiModel("邮件通知参数 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class ExtraMailNotificationDTO extends MailNotificationDTO implements Serializable {

    private static final long serialVersionUID = 4663164026372890533L;

    /**
     * 邮件额外信息
     */
    @NotNull(message = "邮件额外信息 不能为空")
    @ApiModelProperty(value = "邮件额外信息", required = true)
    private Object extra;






















}
