package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 专家(BidSpecialist)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:22:57
 */
@Data
@SuppressWarnings("serial")
public class BidSpecialistQuery implements Serializable {
    private static final long serialVersionUID = -50523502102958944L;

    private Long id;

    private Long bidSheetId;

    private String bidType;

    private Long userId;

    private String specialistName;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
