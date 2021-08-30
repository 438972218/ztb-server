package com.xdcplus.permission.common.pojo.dto.sysPermission;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限信息表(SysPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限信息分页过滤查询 参数对照对象")
public class SysPermissionFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = 445476638063847848L;
    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;


}
