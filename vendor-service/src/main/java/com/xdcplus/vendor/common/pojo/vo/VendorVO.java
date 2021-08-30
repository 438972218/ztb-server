package com.xdcplus.vendor.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商(Vendor)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -49952407439282388L;

    @ApiModelProperty("供应商编码")
    private String code;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("企业名称")
    private String name;

    @ApiModelProperty("营业执照号")
    private String businessLicense;

    @ApiModelProperty("增值税号")
    private String vatNumber;

    @ApiModelProperty("企业邮箱")
    private String enterpriseMail;

    @ApiModelProperty("注册地址")
    private String registeredAddress;

    @ApiModelProperty("主要电话号码")
    private String mainPhone;

    @ApiModelProperty("邮编")
    private String postcode;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("网站")
    private String website;

}
