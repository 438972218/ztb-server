package com.xdcplus.vendorperm.remote.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 邮件DTO
 *
 * @author Rong.Jia
 * @date 2021/08/10 11:47:57
 */
@Data
@ApiModel("邮件参数信息")
public class MailBean implements Serializable {

    private static final long serialVersionUID = 6022763878646328733L;

    /**
     * 发送人邮箱
     */
    @ApiModelProperty(value = "发送人邮箱")
    private String mail;

    /**
     * 发送人名
     */
    @ApiModelProperty("发送人名")
    private String name;




}
