package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能策略信息
 * @author Rong.Jia
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_functional_strategy")
public class FunctionalStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 策略类型
     */
    private Integer type;

    /**
     * 策略脚本
     */
    private String script;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 表单类型ID
     */
    private Long requestTypeId;

    /**
     * 流程信息ID
     */
    private Long processId;

    /**
     * 流程配置版本
     */
    private String configVersion;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;


}
