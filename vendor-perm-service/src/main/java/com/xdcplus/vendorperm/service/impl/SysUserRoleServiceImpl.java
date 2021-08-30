package com.xdcplus.vendorperm.service.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.dao.SysUserRoleDao;
import com.xdcplus.vendorperm.common.pojo.entity.SysUserRole;
import com.xdcplus.vendorperm.common.pojo.vo.sysuserrole.SysUserRoleVo;
import com.xdcplus.vendorperm.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限组信息表(SysUserRole)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-07-08 09:18:21
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl
        extends BaseServiceImpl<SysUserRole, SysUserRoleVo, SysUserRole,
        SysUserRoleDao> implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole queryById(Long id) {
        return this.sysUserRoleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRole insert(SysUserRole sysUserRole) {
        this.save(sysUserRole);
        return sysUserRole;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserRoleDao.deleteById(id) > 0;
    }

    /**
     * 按用户id删除
     *
     * @param userId 用户id
     * @return {@link Integer}
     */
    @Override
    public Integer deleteByUserId(Long userId) {
        return this.sysUserRoleDao.deleteByUserId(userId) ;
    }

    @Override
    public List<SysUserRole> getByRoleId(Long roleId) {
        return this.sysUserRoleDao.getByRoleId(roleId) ;
    }
    public List<SysUserRole> getByUserId(Long userId){
        return this.sysUserRoleDao.getByUserId(userId) ;
    }
}
