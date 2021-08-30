package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商(Vendor)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:14
 */
@Data
@SuppressWarnings("serial")
public class VendorQuery implements Serializable {
    private static final long serialVersionUID = -56951074896196605L;

    private Long id;

    private Long requestId;

    private String requestName;

    private String code;

    private String status;

    private String name;

    private String legalRepresentative;

    private String registeredCapital;

    private String icrn;

    private String type;

    private String uscc;

    private String registrationStatus;

    private String orzCode;

    private String taxpayNum;

    private String area;

    private String industry;

    private String registeredAddress;

    private String businessScope;

    private String contactName;

    private String contactTitle;

    private String contactMobile;

    private String contactEmail;

    private String contactIdentityType;

    private String contactIdentityNum;

    private String identityFront;

    private String identityReverse;

    private String businessModel;

    private String agentBrand;

    private String suplyCategoryRemark;

    private String mainCategory;

    private String mainCustomer;

    private String factoryArea;

    private String factoryNature;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
