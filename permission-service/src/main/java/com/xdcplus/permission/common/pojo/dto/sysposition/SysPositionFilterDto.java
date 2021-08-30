package com.xdcplus.permission.common.pojo.dto.sysposition;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 岗位表(SysPosition)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("岗位管理分页请求查询 参数对照对象")
public class SysPositionFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -88913980179924816L;
    /**
     * 岗位简称
     */
    @ApiModelProperty("岗位简称,支持模糊查询")
    private String shortName;
    /**
     * 岗位全称
     */
    @ApiModelProperty("岗位全称,支持模糊查询")
    private String fullName;
    /**
     * 所属职务
     */
    @ApiModelProperty("所属职务")
    private Long jobId;

}
