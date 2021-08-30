package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.common.enums.RegionEnum;
import com.xdcplus.vendorperm.dao.SysRegionDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysregion.SysRegionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysregion.SysRegionFilterDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysRegion;
import com.xdcplus.vendorperm.common.pojo.query.sysregion.SysRegionFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysregion.SysRegionVo;
import com.xdcplus.vendorperm.service.SysRegionService;
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
 * 行政区域表(SysRegion)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Service("sysRegionService")
public class SysRegionServiceImpl
        extends BaseServiceImpl<SysRegion, SysRegionVo, SysRegion, SysRegionDao>
        implements SysRegionService {
    @Resource
    private SysRegionDao sysRegionDao;

    @Override
    public PageVO<SysRegionVo> getSysRegionPageByCondition(SysRegionFilterDto sysRegionFilterDto) {
        PageVO<SysRegionVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysRegionFilterQuery sysRegionFilterQuery = new SysRegionFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysRegionFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysRegionFilterDto, sysRegionFilterQuery);
        //4.查询
        List<SysRegion> sysRegionList = sysRegionDao.getSysRegionByCondition(sysRegionFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysRegionList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysRegionList));
        return pageVo;
    }

    @Override
    public List<SysRegionVo> getSysRegionByCondition(SysRegionFilterDto sysRegionFilterDto) {
        //1.设置查询对象
        SysRegionFilterQuery sysRegionFilterQuery = new SysRegionFilterQuery();
        org.springframework.beans.BeanUtils.copyProperties(sysRegionFilterDto, sysRegionFilterQuery);
        //4.查询
        List<SysRegion> sysRegionList = sysRegionDao.getSysRegionByCondition(sysRegionFilterQuery);
        return this.objectConversion(sysRegionList);
    }

    @Override
    public List<SysRegionVo> getRegionTree() {
        SysRegionFilterQuery sysRegionFilterQuery =new SysRegionFilterQuery();
        List<SysRegion> sysRegionList=sysRegionDao.getSysRegionByCondition(sysRegionFilterQuery);

        List<SysRegionVo> sysRegionVoList=this.objectConversion(sysRegionList);

        return bulid(sysRegionVoList);
    }
    public <T extends SysRegionVo> List<T> bulid(List<T> treeNodes) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            //找到根节点
            if (treeNode.getParentId() == null|| treeNode.getParentId().equals(0L)) {
                trees.add(treeNode);
            }
            //再次遍历list，找到user的子节点
            for (T it : treeNodes) {
                if (it.getParentId()!=null && it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildrens() == null) {
                        treeNode.setChildrens(new ArrayList<>());
                    }
                    treeNode.getChildrens().add(it);
                }
            }
        }
        return trees;
    }
    @Override
    public SysRegionVo queryById(Long id) {
        SysRegion sysRegion = this.getById(id);
        if (sysRegion != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysRegion.getDeleted())) {
            SysRegionVo sysRegionVo = this.objectConversion(sysRegion);
            return sysRegionVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysRegionDto sysRegionDto,String loginUser) {
        SysRegion sysRegion = new SysRegion();
        //1.校验行政区域的父级是否有效
        SysRegionVo parentSysRegionVo= this.queryById(sysRegionDto.getParentId());
        if(parentSysRegionVo==null){
                throw new ZtbWebException(ResponseEnum.SYS_REGION_PARENT_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_REGION_PARENT_IS_NOT_EXISTS.getMessage());
        }
        //2.校验行政区域的type的有效性
        if(RegionEnum.PROVINCE.getCode().equals(Integer.valueOf(parentSysRegionVo.getType()))){
            if(!RegionEnum.CITY.getCode().equals(Integer.valueOf(sysRegionDto.getType()))){
                throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
            }
        }else if(RegionEnum.CITY.getCode().equals(Integer.valueOf(parentSysRegionVo.getType()))){
            if(!RegionEnum.AREA.getCode().equals(Integer.valueOf(sysRegionDto.getType()))){
                throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
            }
        }else{
                throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
        }
        //3.检验同一个父类下名称是否有重复的
        SysRegion sysRegionNameIsExits= sysRegionDao.getSysRegionByNameAndNoId(sysRegionDto.getName(),null,sysRegionDto.getParentId());
        if(sysRegionNameIsExits!=null){
            throw new ZtbWebException(ResponseEnum.SYS_REGION_NAME_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_REGION_NAME_IS_NOT_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysRegionDto, sysRegion);
        long currentTime=DateUtil.current();
        //设置祖级
        sysRegion.setAncestors(parentSysRegionVo.getAncestors()+","+sysRegionDto.getParentId());
        sysRegion.setUpdatedTime(currentTime);
        sysRegion.setCreatedTime(currentTime);
        sysRegion.setUpdatedUser(loginUser);
        sysRegion.setCreatedUser(loginUser);
        sysRegion.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysRegion);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysRegionDto sysRegionDto,String loginUser) {
        //1.检验待更新的id是否有效
        SysRegion sysRegion = this.getById(sysRegionDto.getId());
        if (sysRegion == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.校验行政区域的父级是否有效
        SysRegionVo newParentSysRegionVo= this.queryById(sysRegionDto.getParentId());
        if(newParentSysRegionVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_REGION_PARENT_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_REGION_PARENT_IS_NOT_EXISTS.getMessage());
        }
        //3.获取更新前的父级
        SysRegionVo oldParentSysRegionVo= this.queryById(sysRegion.getParentId());
        if(oldParentSysRegionVo==null){
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }

        //4.校验行政区域的type的有效性
        if(RegionEnum.PROVINCE.getCode().equals(Integer.valueOf(newParentSysRegionVo.getType()))){
            if(!RegionEnum.CITY.getCode().equals(Integer.valueOf(sysRegionDto.getType()))){
                throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
            }
        }else if(RegionEnum.CITY.getCode().equals(Integer.valueOf(newParentSysRegionVo.getType()))){
            if(!RegionEnum.AREA.getCode().equals(Integer.valueOf(sysRegionDto.getType()))){
                throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
            }
        }else{
            throw new ZtbWebException(ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getCode(), ResponseEnum.SYS_REGION_TYPE_IS_INVALID.getMessage());
        }
        //4.检验同一个父类下名称是否有重复的
        SysRegion sysRegionNameIsExits= sysRegionDao.getSysRegionByNameAndNoId(sysRegionDto.getName(),sysRegionDto.getId(),sysRegionDto.getParentId());
        if(sysRegionNameIsExits!=null){
            throw new ZtbWebException(ResponseEnum.SYS_REGION_NAME_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_REGION_NAME_IS_NOT_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysRegion, sysRegion);
        long currentTime=DateUtil.current();
        sysRegion.setUpdatedTime(currentTime);
        sysRegion.setUpdatedUser(loginUser);

        //5.遍历下面的所有子节点，更改
            String newAncestors = newParentSysRegionVo.getAncestors() + "," + newParentSysRegionVo.getId();
            String oldAncestors = oldParentSysRegionVo.getAncestors();
        sysRegion.setAncestors(newAncestors);
        updateRegionChildren(sysRegion.getId(), newAncestors, oldAncestors);
        this.updateById(sysRegion);

    }

    public void updateRegionChildren(Long regionId, String newAncestors, String oldAncestors)
    {
        List<SysRegion> children = sysRegionDao.getChildrenRegionById(regionId);
        for (SysRegion child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            sysRegionDao.updateSysRegionChildren(children);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        SysRegion sysRegion = this.getById(id);
        //1.验证删除的数据的有效性
        if (sysRegion == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证该节点下是否有子级
        //1.设置查询对象
        SysRegionFilterQuery sysRegionFilterQuery = new SysRegionFilterQuery();
        sysRegionFilterQuery.setParentId(id);
        //4.查询
        List<SysRegion> sysRegionList = sysRegionDao.getSysRegionByCondition(sysRegionFilterQuery);
        if(sysRegionList!=null && sysRegionList.size()>0){
            throw new ZtbWebException(ResponseEnum.REGION_EXISTS_CHILD.getCode(), ResponseEnum.REGION_EXISTS_CHILD.getMessage());
        }
        long currentTime= DateUtil.current();
        sysRegion.setUpdatedTime(currentTime);
        sysRegion.setUpdatedUser(loginUser);
        sysRegion.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysRegion);
    }
}
