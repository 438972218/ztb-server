package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价单附件(PaidAttachment)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-30 13:40:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidAttachmentVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -97178576222247850L;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

    @ApiModelProperty("竞价供应商id")
    private Long paidVendorId;

    @ApiModelProperty("类型(用户、供应商)")
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

    @ApiModelProperty("供应商是否可见")
    private Integer vendorVisible;

}
