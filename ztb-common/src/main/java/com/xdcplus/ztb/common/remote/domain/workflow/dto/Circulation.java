package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 循环
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class Circulation implements Serializable {

    private static final long serialVersionUID = -91167096272246573L;

    private String description;

    /**
     * 流转条件,
     */
    @ApiModelProperty(value = "流转条件")
    private Object flowConditions;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

}
