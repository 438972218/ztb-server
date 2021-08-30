package com.xdcplus.workflow.common.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 过程配置验证DTO
 *
 * @author Rong.Jia
 * @date 2021/07/29 18:28:36
 */
@Data
@Builder
public class ProcessConfigValidationDTO implements Serializable {

    private static final long serialVersionUID = 7639023871278119261L;

    /**
     * 上一个
     */
    private String from;

    /**
     * 下一个
     */
    private String to;

    /**
     * 节点类型-》开始: 0;结束: -1;一般: 1;
     * 会签节点:2;条件判断节点：3;
     * 查阅节点：4;子流程节点：5
     */
    private Integer type;
















}
