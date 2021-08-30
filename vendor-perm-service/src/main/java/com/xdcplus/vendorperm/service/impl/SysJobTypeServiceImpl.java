package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.dao.SysJobTypeDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysjobtype.SysJobTypeDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysjobtype.SysJobTypeFilterDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysJobType;
import com.xdcplus.vendorperm.common.pojo.query.sysjobtype.SysJobTypeFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysjob.SysJobVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysjobtype.SysJobTypeVo;
import com.xdcplus.vendorperm.service.SysJobService;
import com.xdcplus.vendorperm.service.SysJobTypeService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职务类别表(SysJobType)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Service("sysJobTypeService")
public class SysJobTypeServiceImpl
        extends BaseServiceImpl<SysJobType, SysJobTypeVo, SysJobType, SysJobTypeDao>
        implements SysJobTypeService {
    @Resource
    private SysJobTypeDao sysJobTypeDao;

    @Resource
    private SysJobService sysJobService;

    @Override
    public PageVO<SysJobTypeVo> getSysJobTypePageByCondition(SysJobTypeFilterDto sysJobTypeFilterDto) {
        PageVO<SysJobTypeVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysJobTypeFilterQuery sysJobTypeFilterQuery = new SysJobTypeFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysJobTypeFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysJobTypeFilterDto, sysJobTypeFilterQuery);
        //4.查询
        List<SysJobType> sysJobTypeList = sysJobTypeDao.getSysJobTypePageByCondition(sysJobTypeFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysJobTypeList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysJobTypeList));
        return pageVo;
    }
    @Override
    public  List<SysJobTypeVo> getSysJobTypeByCondition(){
        SysJobTypeFilterQuery sysJobTypeFilterQuery = new SysJobTypeFilterQuery();
//        org.springframework.beans.BeanUtils.copyProperties(sysJobTypeFilterDto, sysJobTypeFilterQuery);
        //4.查询
        List<SysJobType> sysJobTypeList = sysJobTypeDao.getSysJobTypePageByCondition(sysJobTypeFilterQuery);
        return this.objectConversion(sysJobTypeList);
    }

    @Override
    public SysJobTypeVo queryById(Long id) {
        SysJobType sysJobType = this.getById(id);
        if (sysJobType != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysJobType.getDeleted())) {
            SysJobTypeVo sysJobTypeVo = this.objectConversion(sysJobType);
            return sysJobTypeVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysJobTypeDto sysJobTypeDto,String loginUser) {
        //1.验证简称名称是否重复
        SysJobType sysJobTypeNameIsExists= sysJobTypeDao.getSysJobTypeByShortNameAndNoId(sysJobTypeDto.getShortName(),null);
        if(sysJobTypeNameIsExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_TYPE_NAME_IS_EXISTS.getMessage());
        }
        SysJobType sysJobType = new SysJobType();
        org.springframework.beans.BeanUtils.copyProperties(sysJobTypeDto, sysJobType);
        long currentTime=DateUtil.current();
        sysJobType.setUpdatedTime(currentTime);
        sysJobType.setCreatedTime(currentTime);
        sysJobType.setUpdatedUser(loginUser);
        sysJobType.setCreatedUser(loginUser);
        sysJobType.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysJobType);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysJobTypeDto sysJobTypeDto,String loginUser) {
        //1.验证id是否存在
        SysJobType sysJobType = this.getById(sysJobTypeDto.getId());
        if (sysJobType == null) {
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getMessage());
        }
        //2.验证职位类别名称是否已经存在
        SysJobType sysJobTypeNameIsExists= sysJobTypeDao.getSysJobTypeByShortNameAndNoId(sysJobTypeDto.getShortName(),sysJobTypeDto.getId());
        if(sysJobTypeNameIsExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_TYPE_NAME_IS_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysJobTypeDto, sysJobType);
        long currentTime=DateUtil.current();
        sysJobType.setUpdatedTime(currentTime);
        sysJobType.setUpdatedUser(loginUser);
        this.updateById(sysJobType);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.验证id是否存在
        SysJobType sysJobType = this.getById(id);
        if (sysJobType == null ||  IsDeletedEnum.DELETED.getCode().equals(sysJobType.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getMessage());
        }
        //2.验证职务类别是否被职务绑定了
        List<SysJobVo> sysJobVoList= sysJobService.getByJobTypeId(id);
        if(CollectionUtil.isNotEmpty(sysJobVoList)){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime= DateUtil.current();
        sysJobType.setUpdatedTime(currentTime);
        sysJobType.setUpdatedUser(loginUser);
        sysJobType.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysJobType);
    }
}
