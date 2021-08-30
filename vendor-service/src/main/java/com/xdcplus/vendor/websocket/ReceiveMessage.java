package com.xdcplus.vendor.websocket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接收消息
 *
 * @author Rong.Jia
 * @date 2021/08/17 13:22:54
 */
@Data
@ApiModel("接收消息信息")
public class ReceiveMessage implements Serializable {

    private static final long serialVersionUID = -1082048284593593572L;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", required = true)
    private String money;

    /**
     * 表单ID
     */
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     * 出价物标识
     */
    @ApiModelProperty(value = "出价物标识", required = true)
    private String offerGoods;

    /**
     * 出价人
     */
    @ApiModelProperty(value = "出价人", required = true)
    private String offerUser;












}
