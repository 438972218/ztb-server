package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 竞价物品(PaidMaterial)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-30 15:32:10
 */
@Data
@SuppressWarnings("serial")
public class PaidMaterialQuery implements Serializable {
    private static final long serialVersionUID = 111503628550553496L;

    private Long id;

    private Long paidSheetId;

    private Integer materialMark;

    private String materialName;

    private Long beginTime;

    private Long endTime;

    private String unit;

    private Double quantity;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
