package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 请求流dto
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestFlowDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -91167096272246573L;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("操作人")
    private Long userId;

    @ApiModelProperty("退回指定人")
    private Long toUserId;

    @ApiModelProperty("状态id")
    private Long statusId;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("流转动作")
    private String flowOption;


}
