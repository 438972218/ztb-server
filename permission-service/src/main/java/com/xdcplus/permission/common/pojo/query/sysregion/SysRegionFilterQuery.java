package com.xdcplus.permission.common.pojo.query.sysregion;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
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
@ApiModel("过滤查询 参数对照对象")
public class SysRegionFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = -51318149706399898L;
    private String name;
    private Long parentId;
    private String type;
}
