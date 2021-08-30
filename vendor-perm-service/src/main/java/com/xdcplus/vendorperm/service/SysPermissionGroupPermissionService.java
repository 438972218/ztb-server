package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.syspermissiongrouppermission.SysPermissionGroupPermissionDto;
import com.xdcplus.vendorperm.common.pojo.vo.syspermissiongrouppermission.SysPermissionGroupPermissionVo;

import java.util.List;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
public interface SysPermissionGroupPermissionService {

 /**
  * 根据条件获取权限组关联权限信息
  *
  * @param sysPermissionGroupPermissionDto 系统权限组权限dto
  * @return {@link List<SysPermissionGroupPermissionVo>}
  */
 List<SysPermissionGroupPermissionVo> getPermissionGroupPermissionByCondition(SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto);


 /**
  * 插入
  *
  * @param sysPermissionGroupPermissionDto 系统权限组权限dto
  * @param loginUser                       登录用户
  */
 void insert(SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto, String loginUser);

 /**
  * 根据权限组id删除权限组及权限关联信息
  *
  * @param sysPermissionGroupId id
  */
 void deleteBySysPermissionGroupId(Long sysPermissionGroupId);

}
