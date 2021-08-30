package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目附件(ProjectAttachment)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 15:17:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProjectAttachmentVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 826374576965664155L;

    @ApiModelProperty("项目id")
    private Long projectId;

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
