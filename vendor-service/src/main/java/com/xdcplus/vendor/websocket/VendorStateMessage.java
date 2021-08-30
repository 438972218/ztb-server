package com.xdcplus.vendor.websocket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商状态消息
 *
 * @author Rong.Jia
 * @date 2021/08/17 16:57:30
 */
@Data
@ApiModel("供应商状态消息")
public class VendorStateMessage {

    /**
     * 供应商ID
     */
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    /**
     * 状态，在线：1，0：离线
     */
    @ApiModelProperty("状态，在线：1，0：离线")
    private Integer state;

}
