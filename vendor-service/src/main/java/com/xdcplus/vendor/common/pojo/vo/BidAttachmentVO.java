package com.xdcplus.vendor.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标附件(BidAttachment)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidAttachmentVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -49152979629673533L;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("招标供应商id")
    private Long bidVendorId;

    @ApiModelProperty("类型(用户、供应商)")
    private String type;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("供应商是否可见")
    private Integer vendorVisible;

    @ApiModelProperty("附件类型")
    private String attachmentType;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @ApiModelProperty("上传人")
    private String uploadingUser;

    @ApiModelProperty("上传时间")
    private Long uploadingDate;

}
