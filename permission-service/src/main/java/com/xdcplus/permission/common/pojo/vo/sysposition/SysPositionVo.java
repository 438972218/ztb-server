package com.xdcplus.permission.common.pojo.vo.sysposition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 岗位表(SysPosition)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@ApiModel("岗位Vo")
public class SysPositionVo implements Serializable {
    private static final long serialVersionUID = -88913980179924816L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 岗位简称
     */
    @ApiModelProperty("岗位简称")
    private String shortName;
    /**
     * 岗位全称
     */
    @ApiModelProperty("岗位全称")
    private String fullName;
    /**
     * 所属职务
     */
    @ApiModelProperty("所属职务id")
    private Long jobId;
    @ApiModelProperty("所属职务名称")
    private String jobName;
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
