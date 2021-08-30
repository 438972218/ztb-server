package com.xdcplus.permission.common.pojo.dto.sysjob;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 职务表(SysJob)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("职务分页过滤查询 参数对照对象")
public class SysJobFilterDto  extends PageDTO implements Serializable {
    private static final long serialVersionUID = 663078267299608989L;
    /**
     * 职务简称
     */
    @ApiModelProperty("职务简称，支持模糊查询")
    private String shortName;
    /**
     * 职务全称
     */
    @ApiModelProperty("职务全称，支持模糊查询")
    private String fullName;
    /**
     * 所属职务类别
     */
    @ApiModelProperty("所属职务类别")
    private Long jobTypeId;

}
