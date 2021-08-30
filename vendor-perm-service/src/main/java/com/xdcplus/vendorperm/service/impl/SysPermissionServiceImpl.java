package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.dao.SysPermissionDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysPermission.SysPermissionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysPermission.SysPermissionFilterDto;
import com.xdcplus.vendorperm.common.pojo.dto.syspermissiongrouppermission.SysPermissionGroupPermissionDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysPermission;
import com.xdcplus.vendorperm.common.pojo.query.sysPermission.SysPermissionFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysPermission.SysPermissionVo;
import com.xdcplus.vendorperm.common.pojo.vo.syspermissiongrouppermission.SysPermissionGroupPermissionVo;
import com.xdcplus.vendorperm.service.SysPermissionGroupPermissionService;
import com.xdcplus.vendorperm.service.SysPermissionService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限信息表(SysPermission)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl
        extends BaseServiceImpl<SysPermission, SysPermissionVo, SysPermission, SysPermissionDao>
        implements SysPermissionService {
    @Resource
    private SysPermissionDao sysPermissionDao;
    @Resource
    private SysPermissionGroupPermissionService sysPermissionGroupPermissionService;

    @Override
    public PageVO<SysPermissionVo> getSysPermissionPageByCondition(SysPermissionFilterDto sysPermissionFilterDto) {
        PageVO<SysPermissionVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysPermissionFilterQuery sysPermissionFilterQuery = new SysPermissionFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysPermissionFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionFilterDto, sysPermissionFilterQuery);
        //4.查询
        List<SysPermission> sysPermissionList = sysPermissionDao.getSysPermissionByCondition(sysPermissionFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysPermissionList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysPermissionList));
        return pageVo;
    }

    @Override
    public List<SysPermissionVo> getSysPermissionTree() {
        //1.设置查询对象
        SysPermissionFilterQuery sysPermissionFilterQuery = new SysPermissionFilterQuery();
        //3.将dto对象转换为query对象
//        org.springframework.beans.BeanUtils.copyProperties(sysPermissionFilterDto, sysPermissionFilterQuery);
        //4.查询
        List<SysPermission> sysPermissionList = sysPermissionDao.getSysPermissionByCondition(sysPermissionFilterQuery);
        List<SysPermissionVo>   sysPermissionVoList=this.objectConversion(sysPermissionList);
      return bulid(sysPermissionVoList);
}
    public <T extends SysPermissionVo> List<T> bulid(List<T> treeNodes) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            //找到根节点
            if (treeNode.getParentId() == null|| treeNode.getParentId().equals(0L)) {
                trees.add(treeNode);
            }
            //再次遍历list，找到user的子节点
            for (T it : treeNodes) {
                if (it.getParentId()!=null && it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }


    @Override
    public SysPermissionVo queryById(Long id) {
        SysPermission sysPermission = this.getById(id);
        if (sysPermission != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysPermission.getDeleted())) {
            SysPermissionVo sysPermissionVo = this.objectConversion(sysPermission);
            return sysPermissionVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysPermissionDto sysPermissionDto,String loginUser) {
        SysPermission sysPermission = new SysPermission();
        //检验 parent_id、level的有效性
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionDto, sysPermission);
        long currentTime=DateUtil.current();
        sysPermission.setUpdatedTime(currentTime);
        sysPermission.setCreatedTime(currentTime);
        sysPermission.setUpdatedUser(loginUser);
        sysPermission.setCreatedUser(loginUser);
        sysPermission.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysPermission);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysPermissionDto sysPermissionDto,String loginUser) {
        //检验 parent_id、level的有效性
        SysPermission sysPermission = this.getById(sysPermissionDto.getId());
        if(sysPermission==null){
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysPermissionDto, sysPermission);
        long currentTime=DateUtil.current();
        sysPermission.setUpdatedTime(currentTime);
        sysPermission.setUpdatedUser(loginUser);
        this.updateById(sysPermission);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        SysPermission sysPermission = this.getById(id);
        if (sysPermission == null || IsDeletedEnum.DELETED.getCode().equals(sysPermission.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.检查权限是否被绑定了
        SysPermissionGroupPermissionDto sysPermissionGroupPermissionDto=new SysPermissionGroupPermissionDto();
        sysPermissionGroupPermissionDto.setPermissionId(id);
        List<SysPermissionGroupPermissionVo> sysPermissionGroupPermissionList=sysPermissionGroupPermissionService.getPermissionGroupPermissionByCondition(sysPermissionGroupPermissionDto);
        if(sysPermissionGroupPermissionList!=null && sysPermissionGroupPermissionList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(), ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime= DateUtil.current();
        sysPermission.setUpdatedTime(currentTime);
        sysPermission.setUpdatedUser(loginUser);
        sysPermission.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysPermission);
    }
    @Override
    public List<SysPermissionVo> getPermissionByPermissionGroupId(Long permissionGroupId){
        List<SysPermission> sysPermissionList  =sysPermissionDao.getPermissionByPermissionGroupId(permissionGroupId);
        List<SysPermissionVo> sysPermissionVoList = this.objectConversion(sysPermissionList);
        return sysPermissionVoList;
    }

    @Override
    public List<SysPermissionVo> getPermissionByRoleId(Long roleId) {
        return this.objectConversion(sysPermissionDao.getPermissionByRoleId(roleId));
    }


}
