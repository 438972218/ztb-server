package com.xdcplus.permission.service;

import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.pojo.dto.syspermissionGroup.SysPermissionGroupDto;
import com.xdcplus.permission.common.pojo.dto.syspermissionGroup.SysPermissionGroupFilterDto;
import com.xdcplus.permission.common.pojo.vo.syspermissionGroup.SysPermissionGroupVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限组表(SysPermissionGroup)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
public interface SysPermissionGroupService {

    /**
     * 获取权限组分页通过条件
     *
     * @param sysPermissionGroupFilterDto 权限组dto
     * @return {@link PageInfo<SysPermissionGroupVo>}
     */
    PageVO<SysPermissionGroupVo> getSysPermissionGroupPageByCondition(SysPermissionGroupFilterDto sysPermissionGroupFilterDto);

    /**
     * 根据条件查询权限组信息
     *
     * @return {@link List<SysPermissionGroupVo>}
     */
    List<SysPermissionGroupVo> getSysPermissionGroupByCondition();

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysPermissionGroupVo}
     */
    SysPermissionGroupVo queryById(Long id);


    /**
     * 插入
     *
     * @param sysPermissionGroupDto 权限组dto
     */
    void insert(SysPermissionGroupDto sysPermissionGroupDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysPermissionGroupDto 权限组dto
     */
    void updateById(SysPermissionGroupDto sysPermissionGroupDto,String loginUser);

    /**
     * 删除通过id
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

    /**
     * 根据角色id获取权限组信息
     *
     * @param roleId 角色id
     * @return {@link List<SysPermissionGroupVo>}
     */
    List<SysPermissionGroupVo> getPermissionGroupByRoleId(@Param("roleId") Long roleId);

}
