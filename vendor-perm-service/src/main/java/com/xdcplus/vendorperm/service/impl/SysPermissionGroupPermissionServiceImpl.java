package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.dao.SysPermissionGroupPermissionDao;
import com.xdcplus.vendorperm.common.pojo.dto.syspermissiongrouppermission.SysPermissionGroupPermissionDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysPermissionGroupPermission;
import com.xdcplus.vendorperm.common.pojo.query.sysPermissiongrouppermission.SysPermissionGroupPermissionFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.syspermissiongrouppermission.SysPermissionGroupPermissionVo;
import com.xdcplus.vendorperm.service.SysPermissionGroupPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Service("sysPermissionGroupPermissionService")
public class SysPermissionGroupPermissionServiceImpl
        extends BaseServiceImpl<SysPermissionGroupPermission, SysPermissionGroupPermissionVo, SysPermissionGroupPermission, SysPermissionGroupPermissionDao>
        implements SysPermissionGroupPermissionService {
    @Resource
    private SysPermissionGroupPermissionDao sysPermissionGroupPermissionDao;

    @Override
    public List<SysPermissionGroupPermissionVo> getPermissionGroupPermissionByCondition(SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto) {
        //1.设置查询对象
        SysPermissionGroupPermissionFilterQuery sysRolePermissionGroupFilterQuery=new SysPermissionGroupPermissionFilterQuery();
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionGroupPermissionDto, sysRolePermissionGroupFilterQuery);
        //4.查询
        List<SysPermissionGroupPermission> SysPermissionGroupPermissionList=sysPermissionGroupPermissionDao.getPermissionGroupPermissionByCondition(sysRolePermissionGroupFilterQuery);
        return this.objectConversion(SysPermissionGroupPermissionList);
    }

    @Override
    public void insert(SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto, String loginUser) {
        SysPermissionGroupPermission sysPermissionGroupPermission =new SysPermissionGroupPermission();
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionGroupPermissionDto, sysPermissionGroupPermission);
        sysPermissionGroupPermission.setCreatedTime( DateUtil.current());
        sysPermissionGroupPermission.setCreatedUser(loginUser);
        this.save(sysPermissionGroupPermission);
    }
    @Override
    public void deleteBySysPermissionGroupId(Long permissionGroupId){
        sysPermissionGroupPermissionDao.deleteBySysPermissionGroupId(permissionGroupId);
    }

}
