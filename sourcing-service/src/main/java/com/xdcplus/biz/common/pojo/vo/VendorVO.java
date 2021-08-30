package com.xdcplus.biz.common.pojo.vo;

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
 * @since 2021-08-12 15:51:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 492238320311266887L;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("单据名称")
    private String requestName;

    @ApiModelProperty("供应商编码")
    private String code;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("企业名称")
    private String name;

    @ApiModelProperty("法定代表人")
    private String legalRepresentative;

    @ApiModelProperty("注册资本")
    private String registeredCapital;

    @ApiModelProperty("工商注册号")
    private String icrn;

    @ApiModelProperty("企业类型")
    private String type;

    @ApiModelProperty("统一社会信用代码")
    private String uscc;

    @ApiModelProperty("登记状态")
    private String registrationStatus;

    @ApiModelProperty("组织机构代码")
    private String orzCode;

    @ApiModelProperty("纳税人识别号")
    private String taxpayNum;

    @ApiModelProperty("所属地区")
    private String area;

    @ApiModelProperty("所属行业")
    private String industry;

    @ApiModelProperty("注册地址")
    private String registeredAddress;

    @ApiModelProperty("经营范围")
    private String businessScope;

    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人职位")
    private String contactTitle;

    @ApiModelProperty("联系人手机号")
    private String contactMobile;

    @ApiModelProperty("联系人邮箱")
    private String contactEmail;

    @ApiModelProperty("联系人证件类型")
    private String contactIdentityType;

    @ApiModelProperty("联系人证件号码")
    private String contactIdentityNum;

    @ApiModelProperty("证件正面照片")
    private String identityFront;

    @ApiModelProperty("证件反面照片")
    private String identityReverse;

    @ApiModelProperty("商业模式")
    private String businessModel;

    @ApiModelProperty("代理品牌")
    private String agentBrand;

    @ApiModelProperty("供货品类备注")
    private String suplyCategoryRemark;

    @ApiModelProperty("主营品类")
    private String mainCategory;

    @ApiModelProperty("主要客户")
    private String mainCustomer;

    @ApiModelProperty("厂房面积")
    private String factoryArea;

    @ApiModelProperty("厂房性质")
    private String factoryNature;

}
