package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能策略VO
 *
 * @author Rong.Jia
 * @date 2021/08/06 10:56:00
 */
@ApiModel("功能策略信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionalStrategyVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 3028814107293824264L;

    /**
     * 策略类型， 详见数据字典
     */
    @ApiModelProperty("策略类型, 详见数据字典")
    private Integer type;

    /**
     * 策略脚本
     */
    @ApiModelProperty("策略脚本")
    private String script;

    /**
     * 权重
     */
    @ApiModelProperty("权重")
    private Double weight;

    /**
     * 表单类型信息
     */
    @ApiModelProperty("表单类型信息")
    private RequestTypeVO requestType;

    /**
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 流程配置版本
     */
    @ApiModelProperty("流程配置版本")
    private String configVersion;

















}
