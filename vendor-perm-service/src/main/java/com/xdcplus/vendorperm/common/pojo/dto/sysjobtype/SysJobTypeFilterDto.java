package com.xdcplus.vendorperm.common.pojo.dto.sysjobtype;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 职务类别表(SysJobType)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("职务类别分页请求过滤查询 参数对照对象")
public class SysJobTypeFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -22142780018667395L;
    /**
     * 职务类别简称
     */
    @ApiModelProperty("职务类别简称,支持模糊查询")
    private String shortName;
    /**
     * 职务类别全称
     */
    @ApiModelProperty("职务类别全称,支持模糊查询")
    private String fullName;

}
