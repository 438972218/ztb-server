package com.xdcplus.permission.common.pojo.query.syspermissionGroup;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限组表(SysPermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("过滤查询 参数对照对象")
public class SysPermissionGroupFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = 117479615058440287L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限组标识
     */
    private String code;
    /**
     * 权限组说明
     */
    private String description;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Long updatedTime;



}
