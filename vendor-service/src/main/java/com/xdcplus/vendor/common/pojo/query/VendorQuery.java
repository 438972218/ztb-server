package com.xdcplus.vendor.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商(Vendor)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:52
 */
@Data
@SuppressWarnings("serial")
public class VendorQuery implements Serializable {
    private static final long serialVersionUID = -36943488326382687L;

    private Long id;

    private String code;

    private String status;

    private String name;

    private String businessLicense;

    private String vatNumber;

    private String enterpriseMail;

    private String registeredAddress;

    private String mainPhone;

    private String postcode;

    private String city;

    private String country;

    private String website;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
