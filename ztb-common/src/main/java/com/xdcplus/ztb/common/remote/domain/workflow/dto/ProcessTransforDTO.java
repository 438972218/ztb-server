package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 流转DTO
 *
 * @author Fish.Fei
 * @date 2021/06/04
 */
@Data
@ApiModel("流转 参数对照对象")
public class ProcessTransforDTO implements Serializable {

    private static final long serialVersionUID = 6593860846844381019L;

    /**
     * 表单ID
     */
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     *  流程操作 ，详见流程操作信息
     */
    @ApiModelProperty(value = "流程操作", required = true)
    private Integer flowOption;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     *  同意操作
     */
    @ApiModelProperty("同意操作")
    private Agree agree = new Agree();

    /**
     *  退回操作
     */
    @ApiModelProperty("退回操作")
    private SendBack sendBack = new SendBack();

    /**
     * 加签操作
     */
    @ApiModelProperty("加签操作")
    private AdditionalSign additional = new AdditionalSign();

    /**
     * 同意操作参数信息
     */
    @Data
    @ApiModel("同意操作参数信息")
    public static class Agree implements Serializable {

        private static final long serialVersionUID = 878241430984786813L;

        /**
         *   用户拥有角色
         */
        @ApiModelProperty(value = "用户拥有角色")
        private List<Long> roleIds;

        /**
         * 流转条件,
         */
        @ApiModelProperty(value = "流转条件")
        private Object flowConditions;

    }

    /**
     * 退回操作参数信息
     */
    @Data
    @ApiModel("退回操作参数信息")
    public static class SendBack implements Serializable {

        private static final long serialVersionUID = -2128087880387998380L;

        /**
         *  退回状态ID
         */
        @ApiModelProperty(value = "退回状态ID")
        private Long toStatusId;

        /**
         *  去向用户标识
         */
        @ApiModelProperty(value = "去向用户标识")
        private Long toUserId;

    }

    /**
     * 加签操作参数信息
     */
    @Data
    public static class AdditionalSign implements Serializable {

        private static final long serialVersionUID = -7048054086815373051L;

        /**
         *  去向用户标识集合
         */
        @ApiModelProperty(value = "去向用户标识集合")
        private List<Long> toUserIds;

    }


}
