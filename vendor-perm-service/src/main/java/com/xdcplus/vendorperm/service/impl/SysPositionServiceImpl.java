package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.dao.SysPositionDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysposition.GetSysPositionByCompanyOrDepartFilterDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysposition.SysPositionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysposition.SysPositionFilterDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysPosition;
import com.xdcplus.vendorperm.common.pojo.query.sysposition.SysPositionFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysjob.SysJobVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysposition.SysPositionVo;
import com.xdcplus.vendorperm.service.SysEmployeeService;
import com.xdcplus.vendorperm.service.SysJobService;
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
 * 岗位表(SysPosition)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Service("sysPositionService")
public class SysPositionServiceImpl
        extends BaseServiceImpl<SysPosition, SysPositionVo, SysPosition, SysPositionDao>
        implements SysPositionService {
    @Resource
    private SysPositionDao sysPositionDao;

    @Autowired
    private SysJobService sysJobService;

    @Autowired
    private SysEmployeeService sysEmployeeService;

    @Override
    public PageVO<SysPositionVo> getSysPositionPageByCondition(SysPositionFilterDto sysPositionFilterDto) {
        PageVO<SysPositionVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysPositionFilterQuery sysPositionFilterQuery = new SysPositionFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysPositionFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysPositionFilterDto, sysPositionFilterQuery);
        //4.查询
        List<SysPosition> sysPositionList = sysPositionDao.getSysPositionPageByCondition(sysPositionFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysPositionList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysPositionList));
        if(pageVo.getRecords()!=null){
            for (SysPositionVo sysPositionVo:pageVo.getRecords()
            ) {
                if(sysPositionVo.getJobId()!=null){
                    SysJobVo sysJobVo=  sysJobService.queryById(sysPositionVo.getJobId());
                    if(sysJobVo!=null){
                        sysPositionVo.setJobName(sysJobVo.getShortName());
                    }
                }
            }
        }

        return pageVo;
    }

    @Override
    public List<SysPositionVo> getSysPositionByCompanyOrDepart(GetSysPositionByCompanyOrDepartFilterDto getSysPositionByCompanyOrDepartFilterDto) {
        //3.将dto对象转换为query对象
//        GetSysPositionByCompanyOrDepartFilterQuery getSysPositionByCompanyOrDepartFilterQuery=new GetSysPositionByCompanyOrDepartFilterQuery();
//        org.springframework.beans.BeanUtils.copyProperties(getSysPositionByCompanyOrDepartFilterDto, getSysPositionByCompanyOrDepartFilterDto);
        return null;
    }

    @Override
    public List<SysPositionVo> getSysPositionByCondition() {
        //1.设置查询对象
        SysPositionFilterQuery sysPositionFilterQuery = new SysPositionFilterQuery();
        //3.将dto对象转换为query对象
        //4.查询
        List<SysPosition> sysPositionList = sysPositionDao.getSysPositionPageByCondition(sysPositionFilterQuery);
        List<SysPositionVo> sysPositionVoList=this.objectConversion(sysPositionList);
        if(sysPositionVoList!=null){
            for (SysPositionVo sysPositionVo:sysPositionVoList
            ) {
                if(sysPositionVo.getJobId()!=null){
                    SysJobVo sysJobVo=  sysJobService.queryById(sysPositionVo.getJobId());
                    if(sysJobVo!=null){
                        sysPositionVo.setJobName(sysJobVo.getShortName());
                    }
                }
            }
        }

        return sysPositionVoList;
    }

    @Override
    public SysPositionVo queryById(Long id) {
        SysPosition sysPosition = this.getById(id);
        if (sysPosition != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysPosition.getDeleted())) {
            SysPositionVo sysPositionVo = this.objectConversion(sysPosition);
            if(sysPosition.getJobId()!=null){
                SysJobVo sysJobVo=sysJobService.queryById(sysPosition.getJobId());
                if(sysJobVo!=null){
                    sysPositionVo.setJobName(sysJobVo.getShortName());
                }
            }
            return sysPositionVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysPositionDto sysPositionDto,String loginUser) {
        //1.检验职位是否存在
        SysJobVo sysJobVo=  sysJobService.queryById(sysPositionDto.getJobId());
        if(sysJobVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getMessage());
        }
        //2.检验岗位名是否存在
        SysPosition sysPositionNameExists=sysPositionDao.getSysPositionByShortNameAndNoId(sysPositionDto.getShortName(),null);
        if(sysPositionNameExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_POSITION_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_POSITION_NAME_IS_EXISTS.getMessage());
        }
        SysPosition sysPosition = new SysPosition();
        org.springframework.beans.BeanUtils.copyProperties(sysPositionDto, sysPosition);
        long currentTime=DateUtil.current();
        sysPosition.setUpdatedTime(currentTime);
        sysPosition.setCreatedTime(currentTime);
        sysPosition.setUpdatedUser(loginUser);
        sysPosition.setCreatedUser(loginUser);
        sysPosition.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysPosition);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysPositionDto sysPositionDto,String loginUser) {
        //1.检验岗位id的有效性
        SysPosition sysPosition = this.getById(sysPositionDto.getId());
        if (sysPosition == null) {
            throw new ZtbWebException(ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getMessage());
        }
        //2.检验职位是否存在
        SysJobVo sysJobVo=  sysJobService.queryById(sysPositionDto.getJobId());
        if(sysJobVo==null){
            throw new ZtbWebException(ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_JOB_IS_NOT_EXISTS.getMessage());
        }
        //3.检验岗位名是否存在
        SysPosition sysPositionNameExists=sysPositionDao.getSysPositionByShortNameAndNoId(sysPositionDto.getShortName(),sysPositionDto.getId());
        if(sysPositionNameExists!=null){
            throw new ZtbWebException(ResponseEnum.SYS_POSITION_NAME_IS_EXISTS.getCode(),
                    ResponseEnum.SYS_POSITION_NAME_IS_EXISTS.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(sysPositionDto, sysPosition);
        long currentTime=DateUtil.current();
        sysPosition.setUpdatedTime(currentTime);
        sysPosition.setUpdatedUser(loginUser);
        this.updateById(sysPosition);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.检验岗位id的有效性
        SysPosition sysPosition = this.getById(id);
        if (sysPosition == null ||  IsDeletedEnum.DELETED.getCode().equals(sysPosition.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getMessage());
        }
        //2.检查岗位是否员工邦定使用
        List<SysEmployeeVo> sysEmployeeVoList= sysEmployeeService.getSysEmployeeListByPosition(id);
        if(CollectionUtil.isNotEmpty(sysEmployeeVoList)){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime= DateUtil.current();
        sysPosition.setUpdatedTime(currentTime);
        sysPosition.setUpdatedUser(loginUser);
        sysPosition.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysPosition);
    }

    @Override
    public List<SysPositionVo> getByJobId(Long jobId){
        List<SysPosition> sysPositionList= sysPositionDao.getByJobId(jobId);
        return this.objectConversion(sysPositionList);
    }
}
