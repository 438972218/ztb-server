package com.xdcplus.biz.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 招标专家评分(BidSpecialistScore)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 17:17:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSpecialistScoreFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -28506509110832535L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("供应商名称")
    private String bidVendorName;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("是否查看")
    private Integer whetherView;

    @ApiModelProperty("评分")
    private Double score;

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("状态（最新/历史）")
    private String status;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;



    private Long bidSheetId;

    private List<Long> bidVendorIds;

}
