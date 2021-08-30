package com.xdcplus.workflow.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 流程配置 DTO
 *
 * @author Rong.Jia
 * @date 2021/07/29 17:43:40
 */
@Data
@ApiModel("流程配置(table)参数 对照对象")
public class ProcessConfigTableDTO implements Serializable {

    private static final long serialVersionUID = -6786828546253048337L;

    /**
     * 流程ID
     */
    @NotNull(message = "流程ID 不能为空")
    @ApiModelProperty(value = "流程ID", required = true)
    private Long processId;

    /**
     *  上一个状态标识
     */
    @NotNull(message = "上一个状态标识 不能为空")
    @ApiModelProperty(value = "上一个状态标识", required = true)
    private String fromStatusMask;

    /**
     *  下一个状态标识
     */
    @NotNull(message = "下一个状态标识 不能为空")
    @ApiModelProperty(value = "下一个状态标识", required = true)
    private String toStatusMask;

    /**
     *  条件表达式
     */
    @ApiModelProperty(value = "条件表达式")
    private Expression expression = new Expression();

    /**
     *  超时自动流转
     */
    @ApiModelProperty(value = "超时自动流转")
    private Long timeoutAction;

    /**
     *  去向角色ID
     */
    @ApiModelProperty(value = "去向角色ID")
    private Long toRoleId;

    /**
     *  去向用户ID
     */
    @ApiModelProperty(value = "去向用户ID")
    private Long toUserId;

    /**
     *  去向用户类型
     */
    @NotNull(message = "去向用户类型 不能为空")
    @ApiModelProperty(value = "去向用户类型", required = true)
    private Long userTo;

    /**
     * 节点类型-》开始: 0;结束: -1;一般: 1;
     * 会签节点:2;条件判断节点：3;
     * 查阅节点：4;子流程节点：5
     */
    @NotNull(message = "节点类型 不能为空")
    @ApiModelProperty(value = "节点类型 （详见数据字典）", required = true)
    private Integer type;

    @Data
    @ApiModel("条件表达式信息")
    public static class Expression implements Serializable {

        private static final long serialVersionUID = -3416081345594877189L;

        /**
         *  条件表达式
         */
        @ApiModelProperty("条件表达式")
        private String expression;

        /**
         *  描述
         */
        @ApiModelProperty("描述")
        private String description;



    }






}
