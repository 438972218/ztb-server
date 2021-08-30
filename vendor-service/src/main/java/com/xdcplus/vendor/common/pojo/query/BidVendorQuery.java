package com.xdcplus.vendor.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标投标方(BidVendor)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-26 16:42:46
 */
@Data
@SuppressWarnings("serial")
public class BidVendorQuery implements Serializable {
    private static final long serialVersionUID = 726837419098234006L;

    private Long id;

    private Long bidSheetId;

    private Long vendorId;

    private Long vendorUserId;

    private String vendorCode;

    private String vendorName;

    private String vendorStatus;

    private String againStatus;

    private Long replyTime;

    private Long checkTime;

    private Integer round;

    private Integer qualificationAttQuantity;

    private Integer technologyAttQuantity;

    private Integer qualificationView;

    private Integer technologyView;

    private Integer commerceView;

    private Double totalPrice;

    private Double qualificationScore;

    private Double technologyScore;

    private Double commerceScore;

    private Double totalScore;

    private Double confirmedPrice;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
