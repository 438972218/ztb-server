package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.workflow.common.validator.groupvlidator.FunctionalStrategyFormGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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

    private static final long serialVersionUID = 1734509033229950576L;

    /**
     *  表单类型ID
     */
    @NotNull(message = "表单类型ID 不能为空", groups = FunctionalStrategyFormGroupValidator.class)
    @ApiModelProperty(value = "表单类型ID")
    private Long typeId;

    /**
     * 策略条件
     */
    @NotNull(message = "策略条件 不能为空", groups = FunctionalStrategyFormGroupValidator.class)
    @ApiModelProperty(value = "策略条件")
    private Object strategyConditions;








}
