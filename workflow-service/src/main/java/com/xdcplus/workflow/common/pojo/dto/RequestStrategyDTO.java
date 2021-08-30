package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 表单流程策略DTO
 *
 * @author Rong.Jia
 * @date 2021/08/09 09:32:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单流程策略参数对照对象")
public class RequestStrategyDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -1831345374804096577L;

    /**
     * 表单类型ID
     */
    @NotBlank(message = "表单类型ID 不能为空")
    @ApiModelProperty(value = "表单类型ID", required = true)
    private Long requestTypeId;

    /**
     * 流程信息ID
     */
    @NotNull(message = "流程信息ID 不能为空")
    @ApiModelProperty(value = "流程信息ID", required = true)
    private Long processId;

    /**
     * 流程配置版本
     */
    @ApiModelProperty(value = "流程配置版本")
    private String configVersion;

    /**
     * 策略脚本
     */
    @NotBlank(message = "策略脚本 不能为空")
    @ApiModelProperty(value = "策略脚本", required = true)
    private String script;



}
