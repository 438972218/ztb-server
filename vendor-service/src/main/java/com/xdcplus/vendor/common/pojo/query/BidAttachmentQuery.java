package com.xdcplus.vendor.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标附件(BidAttachment)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:02
 */
@Data
@SuppressWarnings("serial")
public class BidAttachmentQuery implements Serializable {
    private static final long serialVersionUID = -86368805826543848L;

    private Long id;

    private Long bidSheetId;

    private Long bidVendorId;

    private String type;

    private String bidType;

    private Integer vendorVisible;

    private String attachmentType;

    private String attachmentName;

    private String attachmentUrl;

    private String uploadingUser;

    private Long uploadingDate;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
