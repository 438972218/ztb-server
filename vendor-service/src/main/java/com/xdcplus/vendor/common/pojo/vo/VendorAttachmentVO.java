package com.xdcplus.vendor.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标附件(VendorAttachment)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorAttachmentVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 278224664914466876L;

    @ApiModelProperty("供应商id")
    private Long vendorId;

    @ApiModelProperty("类型")
    private String type;

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
