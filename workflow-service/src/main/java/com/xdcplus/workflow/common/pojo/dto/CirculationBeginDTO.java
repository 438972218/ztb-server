package com.xdcplus.workflow.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 流转开始 DTO
 *
 * @author Rong.Jia
 * @date 2021/07/29 14:13:40
 */
@Data
@ApiModel("流转开始 参数对照对象")
public class CirculationBeginDTO implements Serializable {

    private static final long serialVersionUID = 7590293090620850400L;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID 不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 流转条件
     */
    @ApiModelProperty(value = "流转条件")
    private Object flowConditions;

}
