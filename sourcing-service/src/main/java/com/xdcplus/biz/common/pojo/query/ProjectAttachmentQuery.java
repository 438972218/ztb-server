package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目附件(ProjectAttachment)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-26 15:17:10
 */
@Data
@SuppressWarnings("serial")
public class ProjectAttachmentQuery implements Serializable {
    private static final long serialVersionUID = -83514791083357136L;

    private Long id;

    private Long projectId;

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
