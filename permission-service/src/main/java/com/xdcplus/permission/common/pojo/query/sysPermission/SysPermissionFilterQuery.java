package com.xdcplus.permission.common.pojo.query.sysPermission;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
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
@ApiModel("过滤查询 参数对照对象")
public class SysPermissionFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = 445476638063847848L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 功能的级别('menu','button')
     */
    private String level;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * url地址
     */
    private String routeUrl;
    /**
     * 是否隐藏  1:是 0：否
     */
    private Integer isHide;
    /**
     * 说明
     */
    private String description;
    /**
     * 图标
     */
    private String icon;
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
    private String permission;


}
