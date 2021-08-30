package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendorperm.common.enums.IsDeletedEnum;
import com.xdcplus.vendorperm.common.enums.IsEndNodeCompany;
import com.xdcplus.vendorperm.dao.SysDepartmentDao;
import com.xdcplus.vendorperm.common.pojo.dto.sysdepartment.SysDepartmentDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysdepartment.SysDepartmentFilterDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysDepartment;
import com.xdcplus.vendorperm.common.pojo.query.sysdepartment.SysDepartmentFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.vendorperm.service.SysCompanyService;
import com.xdcplus.vendorperm.service.SysDepartmentService;
import com.xdcplus.vendorperm.service.SysEmployeeService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 部门表(SysDepartment)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl
        extends BaseServiceImpl<SysDepartment, SysDepartmentVo, SysDepartment, SysDepartmentDao>
        implements SysDepartmentService {
    @Resource
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private SysCompanyService sysCompanyService;

    @Autowired
    private SysEmployeeService sysEmployeeService;

    @Override
    public PageVO<SysDepartmentVo> getSysDepartmentPageByCondition(SysDepartmentFilterDto sysDepartmentFilterDto) {
        PageVO<SysDepartmentVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysDepartmentFilterQuery sysDepartmentFilterQuery = new SysDepartmentFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysDepartmentFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysDepartmentFilterDto, sysDepartmentFilterQuery);
        //4.查询
        List<SysDepartment> sysDepartmentList = sysDepartmentDao.getSysDepartmentPageByCondition(sysDepartmentFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysDepartmentList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysDepartmentList));
        return pageVo;
    }
    @Override
    public List<SysDepartmentVo> getSysDepartmentByCondition() {
        //1.设置查询对象
        SysDepartmentFilterQuery sysDepartmentFilterQuery = new SysDepartmentFilterQuery();
        //3.将dto对象转换为query对象
        //4.查询
        List<SysDepartment> sysDepartmentList = sysDepartmentDao.getSysDepartmentPageByCondition(sysDepartmentFilterQuery);
       return this.objectConversion(sysDepartmentList);
    }

    @Override
    public SysDepartmentVo queryById(Long id) {
        SysDepartment sysDepartment = this.getById(id);
        if (sysDepartment != null) {
            SysDepartmentVo sysDepartmentVo = this.objectConversion(sysDepartment);
            if(sysDepartmentVo.getManager()!=null){
                SysEmployeeVo sysEmployeeVo=  sysEmployeeService.queryById(sysDepartmentVo.getManager());
                if(sysEmployeeVo!=null){
                    sysDepartmentVo.setManagerVo(sysEmployeeVo);
                }
            }
            return sysDepartmentVo;
        }
        return null;
    }

    @Override
    public List<TableColumnVo> getDepartmentTableColumns(){
        List<TableColumnVo> tableColumnVoList= sysCompanyService.getTableColumns("sys_department");
        Iterator<TableColumnVo> it = tableColumnVoList.iterator();
        Map<String,String> deleteColumenMap=new HashMap<>();
        deleteColumenMap.put("short_name","short_name");
        deleteColumenMap.put("full_name","full_name");
        deleteColumenMap.put("company_code","company_code");
        deleteColumenMap.put("code","code");
        deleteColumenMap.put("parent_code","parent_code");
        deleteColumenMap.put("manager","manager");
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
    public void insert(SysDepartmentDto sysDepartmentDto,String loginUser) {
        //1.检查部门code不能为空
        checkSysDepartmentNotNull(sysDepartmentDto, null);
        //2.验证上级部门的有效性
        checkDepartmentParent(sysDepartmentDto);
        //3.验证所属公司的有效性
        checkDepartmentCompany(sysDepartmentDto);
        //4.验证部门负责人是否存在
        checkDepartmentManager(sysDepartmentDto);
        SysDepartment sysDepartment = new SysDepartment();
        org.springframework.beans.BeanUtils.copyProperties(sysDepartmentDto, sysDepartment);
        long currentTime=DateUtil.current();
        sysDepartment.setUpdatedTime(currentTime);
        sysDepartment.setCreatedTime(currentTime);
        sysDepartment.setUpdatedUser(loginUser);
        sysDepartment.setCreatedUser(loginUser);
        sysDepartment.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysDepartment);
    }

    private void checkDepartmentCompany(SysDepartmentDto sysDepartmentDto) {
        SysCompanyVo sysCompanyVo = sysCompanyService.queryById(sysDepartmentDto.getCompanyId());
        if (sysCompanyVo == null) {
            throw new ZtbWebException(ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.SYS_COMPANY_IS_NOT_EXISTS.getMessage());
        }
        //3.验证所属公司是否是末节点公司，只有末节点公司下面才可以添加部门
        Integer isGroupCompany = sysCompanyService.judgeGroupCompany(sysDepartmentDto.getCompanyId());
        if (IsEndNodeCompany.NO_END_NODE.getCode().equals(isGroupCompany)) {
            throw new ZtbWebException(ResponseEnum.SYS_COMPANY_IS_NOT_GROUP_COMPANY.getCode(),
                    ResponseEnum.SYS_COMPANY_IS_NOT_GROUP_COMPANY.getMessage());
        }
    }

    private void checkDepartmentParent(SysDepartmentDto sysDepartmentDto) {
        if (sysDepartmentDto.getParentId() != 0) {
            SysDepartmentVo parentSysDepartmentVo = this.queryById(sysDepartmentDto.getParentId());
            if (parentSysDepartmentVo == null) {
                throw new ZtbWebException(ResponseEnum.SYS_PARENT_DEPARTMENT_IS_EXIST.getCode(),
                        ResponseEnum.SYS_PARENT_DEPARTMENT_IS_EXIST.getMessage());
            }
        }
    }

    private void checkSysDepartmentNotNull(SysDepartmentDto sysDepartmentDto, Long id) {
        SysDepartment sysDepartmentCodeIsExists = sysDepartmentDao.getSysDepartmentByCodeAndNoId(sysDepartmentDto.getCode(), id);
        if (sysDepartmentCodeIsExists != null) {
            throw new ZtbWebException(ResponseEnum.SYS_DEPARTMENT_IS_EXIST.getCode(),
                    ResponseEnum.SYS_DEPARTMENT_IS_EXIST.getMessage());
        }
    }

    /**
     * 批量插入
     *
     * @param sysDepartmentDtoList 部门dto
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchSyncInsert(List<SysDepartmentDto> sysDepartmentDtoList,String loginUser){
        if(sysDepartmentDtoList!=null && sysDepartmentDtoList.size()>0){
            for (SysDepartmentDto sysDepartmentDto:sysDepartmentDtoList) {
                SysDepartment sysDepartment = sysDepartmentDao.getDepartmentByCode(sysDepartmentDto.getLevel());
                long currentTime= DateUtil.current();
                if(sysDepartment==null){
                    sysDepartment=new SysDepartment();
                    org.springframework.beans.BeanUtils.copyProperties(sysDepartmentDto, sysDepartment);
                    //约定的字段 begin bullion.yan
                    sysDepartment.setCode(sysDepartmentDto.getLevel());
                    sysDepartment.setParentCode(sysDepartmentDto.getParent());
                    //约定的字段 end bullion.yan
                    sysDepartment.setCompanyId(Long.parseLong("1"));//临死写死
                    sysDepartment.setCreatedUser(loginUser);
                    sysDepartment.setCreatedTime(currentTime);
                    if(sysDepartment.getDeleted()==null){
                        sysDepartment.setDeleted(0);
                    }
                    sysDepartmentDao.insert(sysDepartment);

                }else{
                    org.springframework.beans.BeanUtils.copyProperties(sysDepartmentDto, sysDepartment,"id");

                    sysDepartment.setUpdatedTime(currentTime);
                    sysDepartment.setUpdatedUser(loginUser);
                    sysDepartmentDao.updateById(sysDepartment);
                }
            }

            //根据部门上级code，更新上级id
            sysDepartmentDao.updateParentIdByParentCode();
            sysDepartmentDao.updateCompanyIdByCompanyCode();

        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysDepartmentDto sysDepartmentDto,String loginUser) {
        SysDepartment sysDepartment = this.getById(sysDepartmentDto.getId());
        if (sysDepartment == null || IsDeletedEnum.DELETED.getCode().equals(sysDepartment.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(),
                    ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //1.检查部门code不能为空
        checkSysDepartmentNotNull(sysDepartmentDto, sysDepartmentDto.getId());
        //2.验证上级部门的有效性
        checkDepartmentParent(sysDepartmentDto);
        //3.验证所属公司的有效性
        checkDepartmentCompany(sysDepartmentDto);
        //4.验证部门负责人是否存在
        checkDepartmentManager(sysDepartmentDto);
        org.springframework.beans.BeanUtils.copyProperties(sysDepartmentDto, sysDepartment);
        long currentTime=DateUtil.current();
        sysDepartment.setUpdatedTime(currentTime);
        sysDepartment.setUpdatedUser(loginUser);
        this.updateById(sysDepartment);
    }

    private void checkDepartmentManager(SysDepartmentDto sysDepartmentDto) {
        if (sysDepartmentDto.getManager() != null) {
            SysEmployeeVo sysEmployeeVo = sysEmployeeService.queryById(sysDepartmentDto.getManager());
            if (sysEmployeeVo == null) {
                throw new ZtbWebException(ResponseEnum.MANAGER_IS_NULL.getCode(),
                        ResponseEnum.MANAGER_IS_NULL.getMessage());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.验证记录是否存在
        SysDepartment sysDepartment = this.getById(id);
        if (sysDepartment == null || IsDeletedEnum.DELETED.getCode().equals(sysDepartment.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证部门是否有用户被绑定
        List<SysEmployeeVo> sysEmployeeVoList=sysEmployeeService.getSysEmployeeListByDepartmentId(id);
        if(sysEmployeeVoList!=null && sysEmployeeVoList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime=DateUtil.current();
        sysDepartment.setUpdatedTime(currentTime);
        sysDepartment.setUpdatedUser(loginUser);
        sysDepartment.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysDepartment);
    }

    @Override
    public List<SysDepartmentVo> getByCompanyId(Long companyId){
        List<SysDepartment> sysDepartmentList= sysDepartmentDao.getByCompanyId(companyId);
        return this.objectConversion(sysDepartmentList);
    }

    @Override
    public List<SysDepartmentVo> getDepartmentTree() {
        SysDepartmentFilterQuery sysDepartmentFilterQuery =new SysDepartmentFilterQuery();
        List<SysDepartment> sysDepartmentList=sysDepartmentDao.getSysDepartmentPageByCondition(sysDepartmentFilterQuery);

        List<SysDepartmentVo> sysDepartmentVoList=this.objectConversion(sysDepartmentList);
        return bulid(sysDepartmentVoList);
    }
    public <T extends SysDepartmentVo> List<T> bulid(List<T> treeNodes) {
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

}
