package com.xdcplus.permission.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.permission.common.enums.IsDeletedEnum;
import com.xdcplus.permission.dao.SysRoleDao;
import com.xdcplus.permission.common.pojo.dto.sysrole.SysRoleDto;
import com.xdcplus.permission.common.pojo.dto.sysrole.SysRoleFilterDto;
import com.xdcplus.permission.common.pojo.dto.sysrolepermissiongroup.SysRolePermissionGroupDto;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.entity.SysUserRole;
import com.xdcplus.permission.common.pojo.query.sysrole.SysRoleFilterQuery;
import com.xdcplus.permission.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.permission.common.pojo.vo.sysrole.SysRoleVo;
import com.xdcplus.permission.common.pojo.vo.sysrolepermissiongroup.SysRolePermissionGroupVo;
import com.xdcplus.permission.service.*;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:43
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleVo, SysRole,
        SysRoleDao> implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRolePermissionGroupService sysRolePermissionGroupService;

    @Autowired
    private SysPermissionGroupService sysPermissionGroupService;
    @Autowired
    private SysCompanyService sysCompanyService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageVO<SysRoleVo> getSysRolePageByCondition(SysRoleFilterDto sysRoleFilterDto) {
        PageVO<SysRoleVo> pageVo=new PageVO<>();
        //1.设置查询对象
        SysRoleFilterQuery sysRoleFilterQuery=new SysRoleFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysRoleFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysRoleFilterDto, sysRoleFilterQuery);
        //4.查询
        List<SysRole> sysRoleList=sysRoleDao.getSysRolePageByCondition(sysRoleFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysRoleList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysRoleList));
        //5.将公司id转换为公司名称
        if(pageVo.getRecords()!=null){
            for (SysRoleVo sysRoleVo:pageVo.getRecords()) {
                //5.1设置公司名称
                if(sysRoleVo.getCompanyId()!=null){
                    SysCompanyVo sysCompanyVo= sysCompanyService.queryById(sysRoleVo.getCompanyId());
                    if(sysCompanyVo!=null){
                        sysRoleVo.setCompanyName(sysCompanyVo.getShortName());
                    }
                }
                //5.2 根据角色获取所绑定的权限组
                SysRolePermissionGroupDto sysRolePermissionGroupDto=new SysRolePermissionGroupDto();
                sysRolePermissionGroupDto.setRoleId(sysRoleVo.getId());
                List<SysRolePermissionGroupVo> sysRolePermissionGroupVoList=sysRolePermissionGroupService.getSysRolePermissionGroupByCondition(sysRolePermissionGroupDto);
                if(sysRolePermissionGroupVoList!=null && sysRolePermissionGroupVoList.size()>0){
                    List<Long>sysRolePermissionGroupList=sysRolePermissionGroupVoList.stream().map(SysRolePermissionGroupVo::getPermissionGroupId).collect(Collectors.toList());
                    sysRoleVo.setSysPermissionGroupList(sysRolePermissionGroupList);
                }
            }
        }

        return pageVo;
    }
    @Override
    public List<SysRoleVo> getSysRoleListByCondition(SysRoleFilterDto sysRoleFilterDto) {
        //1.设置查询对象
        SysRoleFilterQuery sysRoleFilterQuery=new SysRoleFilterQuery();
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysRoleFilterDto, sysRoleFilterQuery);
        //4.查询
        List<SysRole> sysRoleList=sysRoleDao.getSysRolePageByCondition(sysRoleFilterQuery);
        List<SysRoleVo> sysRoleVoList= this.objectConversion(sysRoleList);
        //5.将公司id转换为公司名称
        if(sysRoleList!=null){
            for (SysRoleVo sysRoleVo:sysRoleVoList) {
                //5.1设置公司名称
                if(sysRoleVo.getCompanyId()!=null){
                    SysCompanyVo sysCompanyVo= sysCompanyService.queryById(sysRoleVo.getCompanyId());
                    if(sysCompanyVo!=null){
                        sysRoleVo.setCompanyName(sysCompanyVo.getShortName());
                    }
                }
                //5.2 根据角色获取所绑定的权限组
                SysRolePermissionGroupDto sysRolePermissionGroupDto=new SysRolePermissionGroupDto();
                sysRolePermissionGroupDto.setRoleId(sysRoleVo.getId());
                List<SysRolePermissionGroupVo> sysRolePermissionGroupVoList=sysRolePermissionGroupService.getSysRolePermissionGroupByCondition(sysRolePermissionGroupDto);
                if(sysRolePermissionGroupVoList!=null && sysRolePermissionGroupVoList.size()>0){
                    List<Long>sysRolePermissionGroupList=sysRolePermissionGroupVoList.stream().map(SysRolePermissionGroupVo::getPermissionGroupId).collect(Collectors.toList());
                    sysRoleVo.setSysPermissionGroupList(sysRolePermissionGroupList);
                }
            }
        }
        return sysRoleVoList;
    }
    @Override
    public List<SysRoleVo> getSysRoleList() {
        //1.设置查询对象
        SysRoleFilterQuery sysRoleFilterQuery=new SysRoleFilterQuery();
        //3.将dto对象转换为query对象
        //4.查询
        List<SysRole> sysRoleList=sysRoleDao.getSysRolePageByCondition(sysRoleFilterQuery);
        List<SysRoleVo> sysRoleVoList= this.objectConversion(sysRoleList);
        //5.将公司id转换为公司名称
        if(sysRoleList!=null){
            for (SysRoleVo sysRoleVo:sysRoleVoList) {
                //5.1设置公司名称
                if(sysRoleVo.getCompanyId()!=null){
                    SysCompanyVo sysCompanyVo= sysCompanyService.queryById(sysRoleVo.getCompanyId());
                    if(sysCompanyVo!=null){
                        sysRoleVo.setCompanyName(sysCompanyVo.getShortName());
                    }
                }
                //5.2 根据角色获取所绑定的权限组
                SysRolePermissionGroupDto sysRolePermissionGroupDto=new SysRolePermissionGroupDto();
                sysRolePermissionGroupDto.setRoleId(sysRoleVo.getId());
                List<SysRolePermissionGroupVo> sysRolePermissionGroupVoList=sysRolePermissionGroupService.getSysRolePermissionGroupByCondition(sysRolePermissionGroupDto);
                if(sysRolePermissionGroupVoList!=null && sysRolePermissionGroupVoList.size()>0){
                    List<Long>sysRolePermissionGroupList=sysRolePermissionGroupVoList.stream().map(SysRolePermissionGroupVo::getPermissionGroupId).collect(Collectors.toList());
                    sysRoleVo.setSysPermissionGroupList(sysRolePermissionGroupList);
                }
            }
        }
        return sysRoleVoList;
    }

    @Override
    public String getSysRoleMarkByUserId(String userName) {
        List<String> markList=sysRoleDao.getSysRoleMarkByUserId(userName);
        if(CollectionUtil.isNotEmpty(markList)){
            return markList.get(0);
        }
        return "";
    }

    @Override
    public SysRoleVo queryById(Long id) {
        SysRole sysRole= this.getById(id);
        if(sysRole!=null && IsDeletedEnum.NO_DELETED.getCode().equals(sysRole.getDeleted())){
            SysRoleVo sysRoleVo =this.objectConversion(sysRole);
            if(sysRoleVo.getCompanyId()!=null){
                SysCompanyVo sysCompanyVo= sysCompanyService.queryById(sysRoleVo.getCompanyId());
                if(sysCompanyVo!=null){
                    sysRoleVo.setCompanyName(sysCompanyVo.getShortName());
                }
            }
            return sysRoleVo;
        }
        return null;
    }

    @Override
    public List<SysRoleVo> queryByUserId(Long userId) {
        List<SysRole> sysRoleList= sysRoleDao.getByUserId(userId);
        List<SysRoleVo> sysRoleVoList=this.objectConversion(sysRoleList);
        return sysRoleVoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysRoleDto sysRoleDto,String loginUser) {
        //1.检查所属公司id的有效性
        SysCompanyVo sysCompanyVo=sysCompanyService.queryById(sysRoleDto.getCompanyId());
        if(sysCompanyVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getMessage());
        }
        //2.检查角色名称是否已经存在
        SysRole sysRoleNameExistsCheck=sysRoleDao.getSysRoleByRoleNameAndNoId(sysRoleDto.getName(),null);
        if(sysRoleNameExistsCheck!=null){
            throw new ZtbWebException(ResponseEnum.ROLE_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.ROLE_NAME_IS_EXISTS.getMessage());
        }
        //3.保存角色
        SysRole sysRole=new SysRole();
        org.springframework.beans.BeanUtils.copyProperties(sysRoleDto, sysRole);
        long currentTime=DateUtil.current();
        sysRole.setUpdatedTime(currentTime);
        sysRole.setCreatedTime(currentTime);
        sysRole.setUpdatedUser(loginUser);
        sysRole.setCreatedUser(loginUser);
        sysRole.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysRole);
        //4.保存角色权限组信息
        saveRolePermissionGroup(sysRoleDto, loginUser, sysRole);
    }

    /**
     * 保存角色权限组信息
     *
     * @param sysRoleDto 系统角色dto
     * @param loginUser  登录用户
     * @param sysRole    系统的作用
     */
    private void saveRolePermissionGroup(SysRoleDto sysRoleDto, String loginUser, SysRole sysRole) {
        if(sysRoleDto.getSysPermissionGroupList()==null || sysRoleDto.getSysPermissionGroupList().size()==0){
            throw new ZtbWebException(ResponseEnum.PERMISSION_GROUP_IS_NOT_EMPTY.getCode(),
                    ResponseEnum.PERMISSION_GROUP_IS_NOT_EMPTY.getMessage());
        }
        for(Long permissionGroupId: sysRoleDto.getSysPermissionGroupList()){
            if(sysPermissionGroupService.queryById(permissionGroupId)==null){
                throw new ZtbWebException(ResponseEnum.PERMISSION_GROUP_IS_NOT_EXISTS.getCode(), ResponseEnum.PERMISSION_GROUP_IS_NOT_EXISTS.getMessage());
            }
            SysRolePermissionGroupDto sysRolePermissionGroupDto=new SysRolePermissionGroupDto();
            sysRolePermissionGroupDto.setRoleId(sysRole.getId());
            sysRolePermissionGroupDto.setPermissionGroupId(permissionGroupId);
            sysRolePermissionGroupService.save(sysRolePermissionGroupDto, loginUser);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysRoleDto sysRoleDto,String loginUser) {
        //1.检查角色id是否存在
        SysRole sysRole =this.getById(sysRoleDto.getId());
        if(sysRole==null){
            throw new ZtbWebException(ResponseEnum.ROLE_UPDATE_IS_NOT_EXISTS.getCode(), ResponseEnum.ROLE_UPDATE_IS_NOT_EXISTS.getMessage());
        }
        //2.检查公司id是否存在
        SysCompanyVo sysCompanyVo=sysCompanyService.queryById(sysRoleDto.getCompanyId());
        if(sysCompanyVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getMessage());
        }
        //3.检查角色名是否重复
        SysRole sysRoleNameExistsCheck=sysRoleDao.getSysRoleByRoleNameAndNoId(sysRoleDto.getName(),sysRoleDto.getId());
        if(sysRoleNameExistsCheck!=null){
            throw new ZtbWebException(ResponseEnum.ROLE_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.ROLE_NAME_IS_EXISTS.getMessage());
        }
        //4.删除角色之前所绑定的权限组信息
        sysRolePermissionGroupService.deleteByRoleId(sysRole.getId());
        //5.角色绑定用户新选择的权限组信息
        saveRolePermissionGroup(sysRoleDto, loginUser, sysRole);
        org.springframework.beans.BeanUtils.copyProperties(sysRoleDto, sysRole);
        long currentTime=DateUtil.current();
        sysRole.setUpdatedTime(currentTime);
        sysRole.setUpdatedUser(loginUser);
        //6。更新角色信息
        this.updateById(sysRole);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.检查id是否存在
        SysRole sysRole =this.getById(id);
        if(sysRole==null || IsDeletedEnum.DELETED.getCode().equals(sysRole.getDeleted())){
            throw new ZtbWebException(ResponseEnum.ROLE_DELETE_IS_NOT_EXISTS.getCode(), ResponseEnum.ROLE_DELETE_IS_NOT_EXISTS.getMessage());
        }
        //2.判断该角色是否有用户角色绑定
        List<SysUserRole> sysUserRoleList=sysUserRoleService.getByRoleId(id);
        if(sysUserRoleList!=null && sysUserRoleList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime=DateUtil.current();
        sysRole.setUpdatedTime(currentTime);
        sysRole.setUpdatedUser(loginUser);
        //删除
        sysRole.setDeleted(IsDeletedEnum.DELETED.getCode());
        this.updateById(sysRole);
        //3.删除角色与权限组的绑定关系
        sysRolePermissionGroupService.deleteByRoleId(sysRole.getId());
    }

    @Override
    public List<SysRoleVo> getByCompanyId(Long companyId) {
        List<SysRole> sysEmployeeList= sysRoleDao.getByCompanyId(companyId);
        return this.objectConversion(sysEmployeeList);
    }

}
