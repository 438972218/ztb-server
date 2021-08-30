package com.xdcplus.permission.common.pojo.query.sysPermissiongrouppermission;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("过滤查询 参数对照对象")
public class SysPermissionGroupPermissionFilterQuery extends PageDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限组ID
     */
    private Long permissionGroupId;
    /**
     * 权限id
     */
    private Long permissionId;
}
