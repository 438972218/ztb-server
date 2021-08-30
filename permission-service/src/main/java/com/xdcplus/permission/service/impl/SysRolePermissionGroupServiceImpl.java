package com.xdcplus.permission.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.permission.dao.SysRolePermissionGroupDao;
import com.xdcplus.permission.common.pojo.dto.sysrolepermissiongroup.SysRolePermissionGroupDto;
import com.xdcplus.permission.common.pojo.entity.SysRolePermissionGroup;
import com.xdcplus.permission.common.pojo.query.sysrolepermissiongroup.SysRolePermissionGroupFilterQuery;
import com.xdcplus.permission.common.pojo.vo.sysrolepermissiongroup.SysRolePermissionGroupVo;
import com.xdcplus.permission.service.SysRolePermissionGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限组信息表(SysRolePermissionGroup)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Service("sysRolePermissionGroupService")
public class SysRolePermissionGroupServiceImpl
        extends BaseServiceImpl<SysRolePermissionGroup, SysRolePermissionGroupVo, SysRolePermissionGroup, SysRolePermissionGroupDao>
        implements SysRolePermissionGroupService {
    @Resource
    private SysRolePermissionGroupDao sysRolePermissionGroupDao;

    /**
     * 系统角色权限组信息
     *
     * @param sysRolePermissionGroupDto 系统角色权限组dto
     * @return {@link List<SysRolePermissionGroup>}
     */
    public List<SysRolePermissionGroupVo> getSysRolePermissionGroupByCondition(SysRolePermissionGroupDto sysRolePermissionGroupDto) {
        //1.设置查询对象
        SysRolePermissionGroupFilterQuery sysRolePermissionGroupFilterQuery=new SysRolePermissionGroupFilterQuery();
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysRolePermissionGroupDto, sysRolePermissionGroupFilterQuery);
        //4.查询
        List<SysRolePermissionGroup> SysRolePermissionGroupList=sysRolePermissionGroupDao.getSysRolePermissionGroupByCondition(sysRolePermissionGroupFilterQuery);
        return this.objectConversion(SysRolePermissionGroupList);
    }

    /**
     * 保存
     *
     * @param sysRolePermissionGroupDto 系统角色权限组dto
     * @param loginUser                 登录用户
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(SysRolePermissionGroupDto sysRolePermissionGroupDto, String loginUser) {
        SysRolePermissionGroup sysRolePermissionGroup=new SysRolePermissionGroup();
        org.springframework.beans.BeanUtils.copyProperties(sysRolePermissionGroupDto, sysRolePermissionGroup);
        sysRolePermissionGroup.setCreatedTime( DateUtil.current());
        sysRolePermissionGroup.setCreatedUser(loginUser);
        this.save(sysRolePermissionGroup);

    }

    /**
     * 按角色删除id
     *
     * @param roleId 角色id
     */
    public void deleteByRoleId(Long roleId){
        sysRolePermissionGroupDao.deleteByRoleId(roleId);
    }

}
