package com.xdcplus.vendorperm.common.pojo.dto.sysjob;

import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 职务表(SysJob)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("职务Dto 参数对照对象")
public class SysJobDto implements Serializable {
    private static final long serialVersionUID = 663078267299608989L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 职务简称
     */
    @ApiModelProperty("职务简称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "职务简称不能为空")
    private String shortName;
    /**
     * 职务全称
     */
    @ApiModelProperty("职务全称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "职务全称不能为空")
    private String fullName;
    /**
     * 所属职务类别
     */
    @ApiModelProperty("所属职务类别")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "所属职务类别不能为空")
    private Long jobTypeId;
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
