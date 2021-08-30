package com.xdcplus.vendorperm.common.pojo.dto.sysposition;

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
 * 岗位表(SysPosition)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("岗位DtO对象 参数对照对象")
public class SysPositionDto implements Serializable {
    private static final long serialVersionUID = -88913980179924816L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 岗位简称
     */
    @ApiModelProperty("岗位简称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "岗位简称不能为空")
    private String shortName;
    /**
     * 岗位全称
     */
    @ApiModelProperty("岗位全称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "岗位全称不能为空")
    private String fullName;
    /**
     * 所属职务
     */
    @ApiModelProperty("所属职务")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "所属职务不能为空")
    private Long jobId;
    /**
     * 岗位职责
     */
    @ApiModelProperty("岗位职责")
    private String positionDuty;
    /**
     * 任职资格
     */
    @ApiModelProperty("任职资格")
    private String positionQualifications;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;


}
