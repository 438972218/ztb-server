package com.xdcplus.permission.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.enums.IsDeletedEnum;
import com.xdcplus.permission.common.enums.OrganizationStructureType;
import com.xdcplus.permission.common.pojo.dto.sysemployee.GetSysEmployeeByNoBindUser;
import com.xdcplus.permission.common.pojo.vo.sysemployee.GetSysEmployeeByNoBindUserVO;
import com.xdcplus.permission.common.pojo.vo.sysuser.SysUserVo;
import com.xdcplus.permission.dao.SysEmployeeDao;
import com.xdcplus.permission.common.pojo.dto.sysemployee.QueryByCompanyIdOrDepartIdPageDto;
import com.xdcplus.permission.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.permission.common.pojo.dto.sysemployee.SysEmployeeFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysEmployee;
import com.xdcplus.permission.common.pojo.query.sysemployee.SysEmployeeFilterQuery;
import com.xdcplus.permission.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.permission.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.permission.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.permission.common.pojo.vo.sysposition.SysPositionVo;
import com.xdcplus.permission.dao.SysUserDao;
import com.xdcplus.permission.service.*;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 员工信息表(SysEmployee)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
@Service("sysEmployeeService")
public class SysEmployeeServiceImpl
        extends BaseServiceImpl<SysEmployee, SysEmployeeVo, SysEmployee, SysEmployeeDao>
        implements SysEmployeeService {
    @Resource
    private SysEmployeeDao sysEmployeeDao;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysPositionService sysPositionService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Override
    public PageVO<SysEmployeeVo> getSysEmployeePageByCondition(SysEmployeeFilterDto sysEmployeeFilterDto) {
        PageVO<SysEmployeeVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysEmployeeFilterQuery sysEmployeeFilterQuery = new SysEmployeeFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysEmployeeFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysEmployeeFilterDto, sysEmployeeFilterQuery);
        //4.查询
        List<SysEmployee> sysEmployeeList = sysEmployeeDao.getSysEmployeeByCondition(sysEmployeeFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysEmployeeList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysEmployeeList));
        if(pageVo.getRecords()!=null){
            for (SysEmployeeVo sysEmployeeVo:pageVo.getRecords()) {
                if(sysEmployeeVo.getDepartmentId()!=null){
                    SysDepartmentVo sysDepartmentVo= sysDepartmentService.queryById(sysEmployeeVo.getDepartmentId());
                    if(sysDepartmentVo!=null){
                        sysEmployeeVo.setDepartmentName(sysDepartmentVo.getShortName());
                    }
                }
                if(sysEmployeeVo.getManagerId()!=null){
                    SysEmployeeVo sysEmployeeManagerVo= this.queryById(sysEmployeeVo.getManagerId());
                    if(sysEmployeeManagerVo!=null){
                        sysEmployeeVo.setManagerName(sysEmployeeManagerVo.getRealName());
                    }
                }
            }
        }
        return pageVo;
    }

    /**
     * 根据员工信息id查询 员工信息
     * @param id 员工信息id
     * @return {@link SysEmployeeVo}
     */
    @Override
    public SysEmployeeVo queryById(Long id) {
        SysEmployee sysEmployee = this.getById(id);
        if (sysEmployee != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysEmployee.getDeleted())) {
            SysEmployeeVo sysEmployeeVo = this.objectConversion(sysEmployee);
            return sysEmployeeVo;
        }
        return null;
    }

    @Override
    public PageVO<SysEmployeeVo> queryByCompanyIdOrDepartId(QueryByCompanyIdOrDepartIdPageDto queryByCompanyIdOrDepartIdPageDto) {
        if(!(OrganizationStructureType.COMPANY.getCode().equals(queryByCompanyIdOrDepartIdPageDto.getType()) || OrganizationStructureType.DEPARTMENT.getCode().equals(queryByCompanyIdOrDepartIdPageDto.getType()))){
            throw new ZtbWebException(ResponseEnum.REQUEST_PARAMETER_IS_ERROR.getCode(), ResponseEnum.REQUEST_PARAMETER_IS_ERROR.getMessage());
        }
        PageVO<SysEmployeeVo> pageVo = new PageVO<>();
        PageableUtils.basicPage(queryByCompanyIdOrDepartIdPageDto);
        List<SysEmployee> sysEmployeeList=null;
        if(OrganizationStructureType.COMPANY.getCode().equals(queryByCompanyIdOrDepartIdPageDto.getType())){
            //公司
            sysEmployeeList=sysEmployeeDao.getEmployeeByCompanyId(queryByCompanyIdOrDepartIdPageDto.getId());
        }else if(OrganizationStructureType.DEPARTMENT.getCode().equals(queryByCompanyIdOrDepartIdPageDto.getType())){
            //部门
            sysEmployeeList=sysEmployeeDao.getEmployeeByDepartmentId(queryByCompanyIdOrDepartIdPageDto.getId());
        }
        PageInfo pageInfo = new PageInfo<>(sysEmployeeList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysEmployeeList));
        if(pageVo.getRecords()!=null){
            for (SysEmployeeVo sysEmployeeVo:pageVo.getRecords()) {
                if(sysEmployeeVo.getDepartmentId()!=null){
                    SysDepartmentVo sysDepartmentVo= sysDepartmentService.queryById(sysEmployeeVo.getDepartmentId());
                    if(sysDepartmentVo!=null){
                        sysEmployeeVo.setDepartmentName(sysDepartmentVo.getShortName());
                    }
                }
                if(sysEmployeeVo.getManagerId()!=null){
                    SysEmployeeVo sysEmployeeManagerVo= this.queryById(sysEmployeeVo.getManagerId());
                    if(sysEmployeeManagerVo!=null){
                        sysEmployeeVo.setManagerName(sysEmployeeManagerVo.getRealName());
                    }
                }
            }
        }
        return pageVo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysEmployeeDto sysEmployeeDto,String loginUser) {
        //1.检验员工编号不能重复
        SysEmployee sysEmployeeNoNameExists= sysEmployeeDao.getSysEmployeeByEmployeeNoAndNoId(sysEmployeeDto.getEmployeeNo(),null);
        if(sysEmployeeNoNameExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(), ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        //2.检验岗位id的有效性
        if(sysEmployeeDto.getPosition()!=null){
            SysPositionVo sysPositionVo=sysPositionService.queryById(sysEmployeeDto.getPosition());
            if(sysPositionVo==null){
                throw new ZtbWebException(ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getMessage());
            }
        }
        //3.检验所属部门的有效性
        if(sysEmployeeDto.getDepartmentId()!=null){
            SysDepartmentVo sysDepartmentVo=sysDepartmentService.queryById(sysEmployeeDto.getDepartmentId());
            if(sysDepartmentVo==null){
                throw new ZtbWebException(ResponseEnum.SYS_DEPARTMENT_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_DEPARTMENT_IS_NOT_EXISTS.getMessage());
            }
        }
        //4.检验直级上级的有效性
//        if(sysEmployee.getManagerId()!=null){
//
//        }
        SysEmployee sysEmployee = new SysEmployee();
        org.springframework.beans.BeanUtils.copyProperties(sysEmployeeDto, sysEmployee);
        long currentTime=DateUtil.current();
        sysEmployee.setUpdatedTime(currentTime);
        sysEmployee.setCreatedTime(currentTime);
        sysEmployee.setUpdatedUser(loginUser);
        sysEmployee.setCreatedUser(loginUser);
        sysEmployee.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysEmployee);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchSyncInsert(List<SysEmployeeDto> sysEmployeeDtoList,String loginUser) {
        if(sysEmployeeDtoList!=null && sysEmployeeDtoList.size()>0){
            for (SysEmployeeDto sysEmployeeDto:sysEmployeeDtoList) {
                SysEmployee sysEmployee = sysEmployeeDao.getSysEmployeeByEmployeeNo(sysEmployeeDto.getEmployeeNo());
                long currentTime= DateUtil.current();
                if(sysEmployee==null){
                    syncDepartmentInsert(loginUser, sysEmployeeDto, currentTime);
                }else{
                    syncDepartmentUpdate(loginUser, sysEmployeeDto, sysEmployee, currentTime);
                }
            }
            //根据parent_code更新parent_id
            sysEmployeeDao.updateManagerIdByEmployeeNo();
            sysEmployeeDao.updateDepartIdByDepartCode();

        }
    }

    /**
     * 部门同步更新
     *
     * @param loginUser      登录用户
     * @param sysEmployeeDto sys员工dto
     * @param sysEmployee    系统的员工
     * @param currentTime    当前时间
     */
    private void syncDepartmentUpdate(String loginUser, SysEmployeeDto sysEmployeeDto, SysEmployee sysEmployee, long currentTime) {
        org.springframework.beans.BeanUtils.copyProperties(sysEmployeeDto, sysEmployee,"id");
        sysEmployee.setDepartmentCode(sysEmployee.getDepartmentCode());
        sysEmployee.setUpdatedTime(currentTime);
        sysEmployee.setUpdatedUser(loginUser);
        //约定的字段 begin bullion.yan
        sysEmployee.setDepartmentCode(sysEmployeeDto.getDept());
        //约定的字段 end bullion.yan
        sysEmployeeDao.updateById(sysEmployee);
    }

    /**
     * 同步部门插入
     *
     * @param loginUser      登录用户
     * @param sysEmployeeDto sys员工dto
     * @param currentTime    当前时间
     */
    private void syncDepartmentInsert(String loginUser, SysEmployeeDto sysEmployeeDto, long currentTime) {
        SysEmployee sysEmployee;
        sysEmployee=new SysEmployee();
        org.springframework.beans.BeanUtils.copyProperties(sysEmployeeDto, sysEmployee);
        //约定的字段 begin bullion.yan
        sysEmployee.setDepartmentCode(sysEmployeeDto.getDept());
        //约定的字段 end bullion.yan
         sysEmployee.setCreatedUser(loginUser);
        sysEmployee.setCreatedTime(currentTime);
        if(sysEmployee.getDeleted()==null){
            sysEmployee.setDeleted(0);
        }
        sysEmployeeDao.insert(sysEmployee);
    }

    @Override
    public List<TableColumnVo> getEmployeeTableColumns() {
        List<TableColumnVo> tableColumnVoList= sysCompanyService.getTableColumns("sys_employee");
        Iterator<TableColumnVo> it = tableColumnVoList.iterator();
        Map<String,String> deleteColumenMap=new HashMap<>();
        deleteColumenMap.put("manager_code","manager_code");
        deleteColumenMap.put("real_name","real_name");
        deleteColumenMap.put("employee_no","employee_no");
        deleteColumenMap.put("sex","sex");
        deleteColumenMap.put("status","status");
        deleteColumenMap.put("office_address","office_address");
        deleteColumenMap.put("speciality","speciality");
        deleteColumenMap.put("hobby","hobby");
        deleteColumenMap.put("phone","phone");
        deleteColumenMap.put("office_phone","office_phone");
        deleteColumenMap.put("mail","mail");
        deleteColumenMap.put("office","office");
        while(it.hasNext()){
            TableColumnVo tableColumnVo = it.next();
            if(StringUtils.isBlank(deleteColumenMap.get(tableColumnVo.getColumnName()))){
                it.remove();
            }
        }
        return tableColumnVoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysEmployeeDto sysEmployeeDto,String loginUser) {
        SysEmployee sysEmployee = this.getById(sysEmployeeDto.getId());
        if (sysEmployee == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //1.检验员工编号不能重复
        SysEmployee sysEmployeeNoNameExists= sysEmployeeDao.getSysEmployeeByEmployeeNoAndNoId(sysEmployeeDto.getEmployeeNo(),sysEmployeeDto.getId());
        if(sysEmployeeNoNameExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(), ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        //2.检验岗位id的有效性
        if(sysEmployeeDto.getPosition()!=null){
            SysPositionVo sysPositionVo=sysPositionService.queryById(sysEmployeeDto.getPosition());
            if(sysPositionVo==null){
                throw new ZtbWebException(ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_POSITION_IS_NOT_EXISTS.getMessage());
            }
        }
        //3.检验所属部门的有效性
        if(sysEmployeeDto.getDepartmentId()!=null){
            SysDepartmentVo sysDepartmentVo=sysDepartmentService.queryById(sysEmployeeDto.getDepartmentId());
            if(sysDepartmentVo==null){
                throw new ZtbWebException(ResponseEnum.SYS_DEPARTMENT_IS_NOT_EXISTS.getCode(), ResponseEnum.SYS_DEPARTMENT_IS_NOT_EXISTS.getMessage());
            }
        }
        //4.检验直级上级的有效性
        if(sysEmployee.getManagerId()!=null){

        }
        org.springframework.beans.BeanUtils.copyProperties(sysEmployeeDto, sysEmployee);
        long currentTime=DateUtil.current();
        sysEmployee.setUpdatedTime(currentTime);
        sysEmployee.setUpdatedUser(loginUser);
        this.updateById(sysEmployee);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        SysEmployee sysEmployee = this.getById(id);
        if (sysEmployee == null) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //检验是否有用户绑定该员工
        long currentTime=DateUtil.current();
        sysEmployee.setUpdatedTime(currentTime);
        sysEmployee.setUpdatedUser(loginUser);
        sysEmployee.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysEmployee);
    }
    @Override
    public List<SysEmployeeVo> getSysEmployeeListByPosition(Long positionId){
        List<SysEmployee> sysEmployeeList= sysEmployeeDao.getSysEmployeeListByPositionId(positionId);
        return this.objectConversion(sysEmployeeList);
    }

    @Override
    public List<SysEmployeeVo> getSysEmployeeListByDepartmentId(Long departmentId) {
        List<SysEmployee> sysEmployeeList= sysEmployeeDao.getSysEmployeeListByDepartmentId(departmentId);
        return this.objectConversion(sysEmployeeList);
    }
    @Override
    public SysEmployeeVo getGeneralManagerEmployee(){
        SysEmployeeVo sysEmployeeVo =new SysEmployeeVo();
        SysEmployee sysEmployee=sysEmployeeDao.getGeneralManagerEmployee();
        org.springframework.beans.BeanUtils.copyProperties(sysEmployee, sysEmployeeVo);
        return sysEmployeeVo;
    }

    /**
     * 根据用户名，查询员工信息
     *
     * @param userName 用户名
     * @return {@link List<SysEmployeeVo>}
     */
    @Override
    public SysEmployeeVo getEmployeeVoByUserName(String userName) {
      SysUserVo sysUserVo=sysUserService.queryByUserName(userName);
      if(sysUserVo!=null && sysUserVo.getEmployeeId()!=null){
          SysEmployeeVo sysEmployeeVo=queryById(sysUserVo.getEmployeeId());
          if(sysEmployeeVo!=null && sysEmployeeVo.getDepartmentId()!=null){
              SysDepartmentVo sysDepartmentVo= sysDepartmentService.queryById(sysEmployeeVo.getDepartmentId());
              if(sysDepartmentVo!=null){
                  sysEmployeeVo.setDepartmentName(sysDepartmentVo.getShortName());
              }
          }
          return sysEmployeeVo;
      }
        return null;
    }

    @Override
    public PageVO<GetSysEmployeeByNoBindUserVO> getSysEmployeeByBindUser(GetSysEmployeeByNoBindUser getSysEmployeeByNoBindUser) {
        PageVO<GetSysEmployeeByNoBindUserVO> pageVo = new PageVO<>();
        //1.设置查询对象
        SysEmployeeFilterQuery sysEmployeeFilterQuery = new SysEmployeeFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(getSysEmployeeByNoBindUser);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(getSysEmployeeByNoBindUser, sysEmployeeFilterQuery);
        //4.查询
        List<GetSysEmployeeByNoBindUserVO> sysEmployeeList = sysEmployeeDao.getSysEmployeeByNoBindUser(sysEmployeeFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysEmployeeList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(sysEmployeeList);
        if(pageVo.getRecords()!=null){
            for (GetSysEmployeeByNoBindUserVO sysEmployeeVo:pageVo.getRecords()) {
                if(sysEmployeeVo.getDepartmentId()!=null){
                    SysDepartmentVo sysDepartmentVo= sysDepartmentService.queryById(sysEmployeeVo.getDepartmentId());
                    if(sysDepartmentVo!=null){
                        sysEmployeeVo.setDepartmentName(sysDepartmentVo.getShortName());
                    }
                }
                if(sysEmployeeVo.getManagerId()!=null){
                    SysEmployeeVo sysEmployeeManagerVo= this.queryById(sysEmployeeVo.getManagerId());
                    if(sysEmployeeManagerVo!=null){
                        sysEmployeeVo.setManagerName(sysEmployeeManagerVo.getRealName());
                    }
                }
            }
        }
        return pageVo;
    }



}
