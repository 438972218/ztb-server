package com.xdcplus.permission.common.pojo.vo.sysjobtype;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 职务类别表(SysJobType)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@ApiModel("职务类别Vo")
public class SysJobTypeVo implements Serializable {
    private static final long serialVersionUID = -22142780018667395L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 职务类别简称
     */
    @ApiModelProperty("职务类别简称")
    private String shortName;
    /**
     * 职务类别全称
     */
    @ApiModelProperty("职务类别全称")
    private String fullName;
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
