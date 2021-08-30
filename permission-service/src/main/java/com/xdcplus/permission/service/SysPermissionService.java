package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysPermission.SysPermissionDto;
import com.xdcplus.permission.common.pojo.dto.sysPermission.SysPermissionFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.vo.sysPermission.SysPermissionVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 权限信息(SysPermission)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
public interface SysPermissionService {

    /**
     * 获取权限信息分页通过条件
     *
     * @param sysPermissionFilterDto sys dto许可
     * @return {@link <PageInfo<SysPermissionVo>>}
     */
    PageVO<SysPermissionVo> getSysPermissionPageByCondition(SysPermissionFilterDto sysPermissionFilterDto);

    /**
     * 获取权限树
     *
     * @return {@link List<SysPermissionVo>}
     */
    List<SysPermissionVo> getSysPermissionTree();



    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRole}
     */
    SysPermissionVo queryById(Long id);


    /**
     * 插入
     * @param sysPermissionDto 权限信息dto
     */
    void insert(SysPermissionDto sysPermissionDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysPermissionDto 权限信息dto
     */
    void updateById(SysPermissionDto sysPermissionDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

    List<SysPermissionVo> getPermissionByPermissionGroupId(Long permissionGroupId);

    List<SysPermissionVo> getPermissionByRoleId(Long roleIdList);
}
