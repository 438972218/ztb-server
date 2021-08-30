package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单流转策略DTO
 *
 * @author Rong.Jia
 * @date 2021/08/06 15:09:11
 */
@Data
@ApiModel("表单流转策略 参数对照对象")
public class FormFlowStrategyDTO implements Serializable {

    private static final long serialVersionUID = 1734509033229950578L;

    /**
     *  表单类型ID
     */
    @ApiModelProperty(value = "表单类型ID")
    private Long typeId;

    /**
     * 策略条件
     */
    @ApiModelProperty(value = "策略条件")
    private Object strategyConditions;
}
