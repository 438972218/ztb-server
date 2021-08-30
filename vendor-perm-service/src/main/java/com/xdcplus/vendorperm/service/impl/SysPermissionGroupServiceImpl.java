package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.dao.SysPermissionGroupDao;
import com.xdcplus.vendorperm.common.pojo.dto.syspermissionGroup.SysPermissionGroupDto;
import com.xdcplus.vendorperm.common.pojo.dto.syspermissionGroup.SysPermissionGroupFilterDto;
import com.xdcplus.vendorperm.common.pojo.dto.syspermissiongrouppermission.SysPermissionGroupPermissionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysrolepermissiongroup.SysRolePermissionGroupDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysPermissionGroup;
import com.xdcplus.vendorperm.common.pojo.query.syspermissionGroup.SysPermissionGroupFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.syspermissionGroup.SysPermissionGroupVo;
import com.xdcplus.vendorperm.common.pojo.vo.syspermissiongrouppermission.SysPermissionGroupPermissionVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysrolepermissiongroup.SysRolePermissionGroupVo;
import com.xdcplus.vendorperm.service.SysPermissionGroupPermissionService;
import com.xdcplus.vendorperm.service.SysPermissionGroupService;
import com.xdcplus.vendorperm.service.SysPermissionService;
import com.xdcplus.vendorperm.service.SysRolePermissionGroupService;
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
 * 权限组表(SysPermissionGroup)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Service("sysPermissionGroupService")
public class SysPermissionGroupServiceImpl
        extends BaseServiceImpl<SysPermissionGroup, SysPermissionGroupVo, SysPermissionGroup, SysPermissionGroupDao>
        implements SysPermissionGroupService {
    @Resource
    private SysPermissionGroupDao sysPermissionGroupDao;

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRolePermissionGroupService sysRolePermissionGroupService;

    @Autowired
    private SysPermissionGroupPermissionService sysPermissionGroupPermissionService;

    @Override
    public PageVO<SysPermissionGroupVo> getSysPermissionGroupPageByCondition(SysPermissionGroupFilterDto sysPermissionGroupFilterDto) {
        PageVO<SysPermissionGroupVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysPermissionGroupFilterQuery sysPermissionGroupFilterQuery = new SysPermissionGroupFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysPermissionGroupFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionGroupFilterDto, sysPermissionGroupFilterQuery);
        //4.查询
        List<SysPermissionGroup> sysPermissionGroupList = sysPermissionGroupDao.getSysPermissionGroupByCondition(sysPermissionGroupFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysPermissionGroupList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysPermissionGroupList));
        if(pageVo.getRecords()!=null){
            for (SysPermissionGroupVo sysPermissionGroupVo:pageVo.getRecords()) {
                //4.1 根据权限组id获取所绑定的权限信息
                SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto=new SysPermissionGroupPermissionDto();
                sysPermissionGroupPermissionDto.setPermissionGroupId(sysPermissionGroupVo.getId());
                List<SysPermissionGroupPermissionVo> sysPermissionGroupPermissionVoList=sysPermissionGroupPermissionService.getPermissionGroupPermissionByCondition(sysPermissionGroupPermissionDto);
                if(sysPermissionGroupPermissionVoList!=null && sysPermissionGroupPermissionVoList.size()>0){
                    List<Long>permissionId=sysPermissionGroupPermissionVoList.stream().map(SysPermissionGroupPermissionVo::getPermissionId).collect(Collectors.toList());
                    sysPermissionGroupVo.setPermissionList(permissionId);
                }
            }
        }
        return pageVo;
    }
    @Override
    public List<SysPermissionGroupVo> getSysPermissionGroupByCondition() {
        //1.设置查询对象
        SysPermissionGroupFilterQuery sysPermissionGroupFilterQuery = new SysPermissionGroupFilterQuery();
        //4.查询
        List<SysPermissionGroup> sysPermissionGroupList = sysPermissionGroupDao.getSysPermissionGroupByCondition(sysPermissionGroupFilterQuery);
        return this.objectConversion(sysPermissionGroupList);
    }

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysPermissionGroupVo}
     */
    @Override
    public SysPermissionGroupVo queryById(Long id) {
        SysPermissionGroup sysPermissionGroup = this.getById(id);
        if (sysPermissionGroup != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysPermissionGroup.getDeleted())) {
            SysPermissionGroupVo sysPermissionGroupVo = this.objectConversion(sysPermissionGroup);
            return sysPermissionGroupVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysPermissionGroupDto sysPermissionGroupDto,String loginUser) {
        //1.验证code是否重复
        SysPermissionGroup sysPermissionGroupCodeExists= sysPermissionGroupDao.getCodeByCodeAndNoId(sysPermissionGroupDto.getCode(),null);
        if(sysPermissionGroupCodeExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(),
                    ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        SysPermissionGroup sysPermissionGroup = new SysPermissionGroup();
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionGroupDto, sysPermissionGroup);
        long currentTime=DateUtil.current();
        sysPermissionGroup.setUpdatedTime(currentTime);
        sysPermissionGroup.setCreatedTime(currentTime);
        sysPermissionGroup.setUpdatedUser(loginUser);
        sysPermissionGroup.setCreatedUser(loginUser);
        sysPermissionGroup.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        //2.保存权限组信息
        this.save(sysPermissionGroup);
        //3.保存权限组所绑定的权限信息
        saveSysPermissionGroupPermission(sysPermissionGroupDto, loginUser, sysPermissionGroup.getId());
    }

    /**
     * 保存系统权限组权限信息
     *
     * @param sysPermissionGroupDto 系统权限组dto
     * @param loginUser             登录用户
     * @param permissionGroupId    系统权限组
     */
    private void saveSysPermissionGroupPermission(SysPermissionGroupDto sysPermissionGroupDto, String loginUser, Long permissionGroupId) {
        if(sysPermissionGroupDto.getPermissionList()!=null && sysPermissionGroupDto.getPermissionList().size()==0){
            throw new ZtbWebException(ResponseEnum.PERMISSION_IS_NOT_EMPTY.getCode(),
                    ResponseEnum.PERMISSION_IS_NOT_EMPTY.getMessage());
        }
        if(sysPermissionGroupDto.getPermissionList()!=null){
            for(Long sysPermissionId: sysPermissionGroupDto.getPermissionList()){
                //3.1检查权限是否存在
                if(sysPermissionService.queryById(sysPermissionId)==null){
                    throw new ZtbWebException(ResponseEnum.PERMISSION_IS_NOT_EXISTS.getCode(),
                            ResponseEnum.PERMISSION_IS_NOT_EXISTS.getMessage());
                }
                //3.2保存权限组所绑定的权限信息
                SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto=new SysPermissionGroupPermissionDto();
                sysPermissionGroupPermissionDto.setPermissionId(sysPermissionId);
                sysPermissionGroupPermissionDto.setPermissionGroupId(permissionGroupId);
                sysPermissionGroupPermissionService.insert(sysPermissionGroupPermissionDto, loginUser);

            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysPermissionGroupDto sysPermissionGroupDto,String loginUser) {
        //1.验证更新的id的有效性
        SysPermissionGroup sysPermissionGroup = this.getById(sysPermissionGroupDto.getId());
        if (sysPermissionGroup == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证code是否重复
        SysPermissionGroup sysPermissionGroupCodeExists= sysPermissionGroupDao.getCodeByCodeAndNoId(sysPermissionGroupDto.getCode(),sysPermissionGroupDto.getId());
        if(sysPermissionGroupCodeExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(),
                    ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionGroupDto, sysPermissionGroup);
        long currentTime=DateUtil.current();
        sysPermissionGroup.setUpdatedTime(currentTime);
        sysPermissionGroup.setUpdatedUser(loginUser);
        this.updateById(sysPermissionGroup);
        //3.2 根据权限组id，删除所绑定的权限信息
        sysPermissionGroupPermissionService.deleteBySysPermissionGroupId(sysPermissionGroup.getId());
        //3.3保存权限组所绑定的权限信息
        saveSysPermissionGroupPermission(sysPermissionGroupDto, loginUser, sysPermissionGroup.getId());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.验证待删除的id的有效性
        SysPermissionGroup sysPermissionGroup = this.getById(id);
        if (sysPermissionGroup == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证权限组是否被角色绑定引用
        SysRolePermissionGroupDto sysRolePermissionGroupDto=new SysRolePermissionGroupDto();
        sysRolePermissionGroupDto.setPermissionGroupId(id);
        List<SysRolePermissionGroupVo> sysRolePermissionGroupVoList=  sysRolePermissionGroupService.getSysRolePermissionGroupByCondition(sysRolePermissionGroupDto);
        if(sysRolePermissionGroupVoList!=null && sysRolePermissionGroupVoList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        //3.删除权限组下面所关联的权限信息
        sysPermissionGroupPermissionService.deleteBySysPermissionGroupId(id);;
        long currentTime= DateUtil.current();
        sysPermissionGroup.setUpdatedTime(currentTime);
        sysPermissionGroup.setUpdatedUser(loginUser);
        sysPermissionGroup.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysPermissionGroup);
    }

    /**
     * 根据角色id获取权限组信息
     *
     * @param roleId 角色id
     * @return {@link List<SysPermissionGroupVo>}
     */
    @Override
    public List<SysPermissionGroupVo> getPermissionGroupByRoleId(Long roleId) {
        List<SysPermissionGroup> sysPermissionGroupList  =sysPermissionGroupDao.getPermissionGroupByRoleId(roleId);
        List<SysPermissionGroupVo> sysPermissionGroupVoList = this.objectConversion(sysPermissionGroupList);
        return sysPermissionGroupVoList;
    }
}
