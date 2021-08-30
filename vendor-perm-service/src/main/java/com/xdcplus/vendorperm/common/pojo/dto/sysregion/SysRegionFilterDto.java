package com.xdcplus.vendorperm.common.pojo.dto.sysregion;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 行政区域表(SysRegion)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("行政区域过滤查询 参数对照对象")
public class SysRegionFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -51318149706399898L;
    @ApiModelProperty("行政区域名称")
    private String name;
    @ApiModelProperty("类型（1：省，2：市，3：区）")
    private String type;


}
