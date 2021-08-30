package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目(ProjectSheet)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:00
 */
@Data
@SuppressWarnings("serial")
public class ProjectSheetQuery implements Serializable {
    private static final long serialVersionUID = 123672707544945722L;

    private Long id;

    private String code;

    private String title;

    private String department;

    private String status;

    private String type;

    private Long activationTime;

    private Long startTime;

    private Long finishTime;

    private String items;

    private String explanation;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

    private Long userId;

    private String keyword;

}
