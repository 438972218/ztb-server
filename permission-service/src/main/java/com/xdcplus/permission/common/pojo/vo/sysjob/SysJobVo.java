package com.xdcplus.permission.common.pojo.vo.sysjob;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 职务表(SysJob)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
@Data
@ApiModel("职务Vo")
public class SysJobVo implements Serializable {
    private static final long serialVersionUID = 663078267299608989L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 职务简称
     */
    @ApiModelProperty("职务简称")
    private String shortName;
    /**
     * 职务全称
     */
    @ApiModelProperty("职务全称")
    private String fullName;
    /**
     * 所属职务类别
     */
    @ApiModelProperty("所属职务类别")
    private Long jobTypeId;

    @ApiModelProperty("所属职务类别名称")
    private String jobTypeName;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;



}
