package com.xdcplus.vendor.websocket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 投标时间信息
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
@Builder
@ApiModel("投标时间信息")
public class BiddingTimeMessage implements Serializable {

    /**
     *  表单ID
     */
    @ApiModelProperty("表单ID")
    private Long requestId;

    /**
     * 出价物标识
     */
    @ApiModelProperty("出价物标识")
    private String offerGoods;

    /**
     *  当前信息的计算时间
     */
    @ApiModelProperty("当前信息的计算时间")
    private Long computationTime;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Long startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Long endTime;

    /**
     * 持续时间，单位：毫秒
     */
    @ApiModelProperty("持续时间，单位：毫秒")
    private Long timeOfDuration;

    /**
     *  倒计时，单位：毫秒
     */
    @ApiModelProperty(" 倒计时，单位：毫秒")
    private Long countdown;

    /**
     * 已延时时间，单位：毫秒
     */
    @ApiModelProperty("已延时时间，单位：毫秒")
    private Long delayedTime;





}
