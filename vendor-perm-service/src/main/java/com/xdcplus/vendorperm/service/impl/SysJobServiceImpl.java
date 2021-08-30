package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.dao.SysJobDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysjob.SysJobDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysjob.SysJobFilterDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysJob;
import com.xdcplus.vendorperm.common.pojo.query.sysjob.SysJobFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysjob.SysJobVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysjobtype.SysJobTypeVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysposition.SysPositionVo;
import com.xdcplus.vendorperm.service.SysJobService;
import com.xdcplus.vendorperm.service.SysJobTypeService;
import com.xdcplus.vendorperm.service.SysPositionService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职务表(SysJob)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
@Service("sysJobService")
public class SysJobServiceImpl
        extends BaseServiceImpl<SysJob, SysJobVo, SysJob, SysJobDao>
        implements SysJobService {
    @Resource
    private SysJobDao sysJobDao;

    @Autowired
    private SysJobTypeService sysJobTypeService;

    @Autowired
    private SysPositionService sysPositionService;

    @Override
    public PageVO<SysJobVo> getSysJobPageByCondition(SysJobFilterDto sysJobFilterDto) {
        PageVO<SysJobVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysJobFilterQuery sysJobFilterQuery = new SysJobFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysJobFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysJobFilterDto, sysJobFilterQuery);
        //4.查询
        List<SysJob> sysJobList = sysJobDao.getSysJobByCondition(sysJobFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysJobList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysJobList));

        //5.将职务类别id转换为职务类别名称
        if(pageVo.getRecords()!=null){
            for (SysJobVo sysJobVo:pageVo.getRecords()) {
                if(sysJobVo.getJobTypeId()!=null){
                    SysJobTypeVo sysJobTypeVo= sysJobTypeService.queryById(sysJobVo.getJobTypeId());
                    if(sysJobTypeVo!=null){
                        sysJobVo.setJobTypeName(sysJobTypeVo.getShortName());
                    }
                }
            }
        }
        return pageVo;
    }
    @Override
    public List<SysJobVo> getSysJobByCondition(SysJobFilterDto sysJobFilterDto) {
        //1.设置查询对象
        SysJobFilterQuery sysJobFilterQuery = new SysJobFilterQuery();
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysJobFilterDto, sysJobFilterQuery);
        //4.查询
        List<SysJob> sysJobList = sysJobDao.getSysJobByCondition(sysJobFilterQuery);
        return this.objectConversion(sysJobList);
    }

    @Override
    public SysJobVo queryById(Long id) {
        SysJob sysJob = this.getById(id);
        if (sysJob != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysJob.getDeleted())) {
            SysJobVo sysJobVo = this.objectConversion(sysJob);
            return sysJobVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysJobDto sysJobDto,String loginUser) {
        //1.验证职位名称是否重复
        SysJob sysJobNameIsExists=sysJobDao.getSysJobByShortNameAndNoId(sysJobDto.getShortName(),null);
        if(sysJobNameIsExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_NAME_IS_EXISTS.getMessage());
        }
        //2.验证职务类别id是否存在
        SysJobTypeVo sysJobTypeVo= sysJobTypeService.queryById(sysJobDto.getJobTypeId());
        if(sysJobTypeVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getMessage());
        }
        SysJob sysJob = new SysJob();
        org.springframework.beans.BeanUtils.copyProperties(sysJobDto, sysJob);
        long currentTime=DateUtil.current();
        sysJob.setUpdatedTime(currentTime);
        sysJob.setCreatedTime(currentTime);
        sysJob.setUpdatedUser(loginUser);
        sysJob.setCreatedUser(loginUser);
        sysJob.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysJob);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysJobDto sysJobDto,String loginUser) {
        SysJob sysJob = this.getById(sysJobDto.getId());
        if (sysJob == null) {
            throw new ZtbWebException(ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getMessage());
        }

        //1.验证职位名称是否重复
        SysJob sysJobNameIsExists=sysJobDao.getSysJobByShortNameAndNoId(sysJobDto.getShortName(),sysJobDto.getId());
        if(sysJobNameIsExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_NAME_IS_EXISTS.getMessage());
        }
        //2.验证职务类别id是否存在
        SysJobTypeVo sysJobTypeVo= sysJobTypeService.queryById(sysJobDto.getJobTypeId());
        if(sysJobTypeVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_TYPE_IS_NOT_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysJobDto, sysJob);
        long currentTime=DateUtil.current();
        sysJob.setUpdatedTime(currentTime);
        sysJob.setUpdatedUser(loginUser);
        this.updateById(sysJob);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.验证职位id是否存在
        SysJob sysJob = this.getById(id);
        if (sysJob == null) {
            throw new ZtbWebException(ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getMessage());
        }
        //2.验证该职位是否有绑定岗位
        List<SysPositionVo> sysPositionVoList= sysPositionService.getByJobId(id);
        if(CollectionUtil.isNotEmpty(sysPositionVoList)){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }

        long currentTime= DateUtil.current();
        sysJob.setUpdatedTime(currentTime);
        sysJob.setUpdatedUser(loginUser);
        sysJob.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysJob);
    }

    @Override
    public List<SysJobVo> getByJobTypeId(Long jobTypeId) {
        List<SysJob> sysJob= sysJobDao.getByJobTypeId(jobTypeId);
        return this.objectConversion(sysJob);
    }


}
