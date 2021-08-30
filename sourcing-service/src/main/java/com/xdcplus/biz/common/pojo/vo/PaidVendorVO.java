package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价供应商(PaidVendor)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-30 13:37:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidVendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -51227567423058882L;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

    @ApiModelProperty("供应商id")
    private Long vendorId;

    @ApiModelProperty("供应商用户id")
    private Long vendorUserId;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("供应商状态")
    private String vendorStatus;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("起始价格")
    private Double startPrice;

}
