package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 邮件交付过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:29:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("邮件发送点 过滤查询对象")
public class MailDeliveryFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -2182940091502757282L;

    /**
     * 模板ID
     */
    @ApiModelProperty("模板ID")
    private Long templateId;

    /**
     * 签名ID
     */
    @ApiModelProperty("模板ID")
    private Long signatureId;















}
