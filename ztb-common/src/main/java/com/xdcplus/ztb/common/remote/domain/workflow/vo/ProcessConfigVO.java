package com.xdcplus.ztb.common.remote.domain.workflow.vo;

import cn.hutool.core.lang.Validator;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程配置签证官
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProcessConfigVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 8736687621031018695L;

    /**
     * 用户去向
     */
    @ApiModelProperty(value = "用户去向")
    private Long userTo;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long toUserId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long toRoleId;

    /**
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 上一个流程状态
     */
    @ApiModelProperty("上一个流程状态 ")
    private ProcessStatusVO fromStatus;

    /**
     * 下一个流程状态
     */
    @ApiModelProperty("下一个流程状态 ")
    private ProcessStatusVO toStatus;

    /**
     * 流程规则
     */
    @ApiModelProperty("流程规则 ")
    private QualifierVO qualifier;

    /**
     *  超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
     */
    @ApiModelProperty("超时时间（超时后可流转下一节点）默认24小时")
    private Long timeoutAction;

    /**
     *  版本号
     */
    @ApiModelProperty("版本号")
    private String version;

    /**
     * 节点-表单数量
     */
    @ApiModelProperty("节点-表单数量")
    private Integer requestNumber;

    public Integer getRequestNumber() {
        return Validator.isNull(requestNumber)
                ? NumberConstant.ZERO : requestNumber;
    }
}
