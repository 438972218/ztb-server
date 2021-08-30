package com.xdcplus.permission.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.enums.*;
import com.xdcplus.permission.dao.SysCompanyDao;
import com.xdcplus.permission.common.pojo.dto.syscompany.SysCompanyDto;
import com.xdcplus.permission.common.pojo.dto.syscompany.SysCompanyFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysCompany;
import com.xdcplus.permission.common.pojo.query.syscompany.SysCompanyFilterQuery;
import com.xdcplus.permission.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.permission.common.pojo.vo.syscompany.SysCompanyDepartmentVo;
import com.xdcplus.permission.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.permission.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.permission.common.pojo.vo.sysrole.SysRoleVo;
import com.xdcplus.permission.service.SysCompanyService;
import com.xdcplus.permission.service.SysDepartmentService;
import com.xdcplus.permission.service.SysRoleService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 分部表(公司表)(SysCompany)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Service("sysCompanyService")
public class SysCompanyServiceImpl
        extends BaseServiceImpl<SysCompany, SysCompanyVo, SysCompany, SysCompanyDao>
        implements SysCompanyService {
    @Resource
    private SysCompanyDao sysCompanyDao;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public PageVO<SysCompanyVo> getSysCompanyPageByCondition(SysCompanyFilterDto sysCompanyFilterDto) {
        PageVO<SysCompanyVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysCompanyFilterQuery sysCompanyFilterQuery = new SysCompanyFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysCompanyFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysCompanyFilterDto, sysCompanyFilterQuery);
        //4.查询
        List<SysCompany> sysCompanyList = sysCompanyDao.getSysCompanyByCondition(sysCompanyFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysCompanyList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysCompanyList));

        //5.将公司id转换为公司名称
        if(pageVo.getRecords()!=null){
            for (SysCompanyVo sysCompanyVo:pageVo.getRecords()) {
                if(sysCompanyVo.getParentId()!=null){
                    SysCompanyVo sysCompanyParentVo= this.queryById(sysCompanyVo.getParentId());
                    if(sysCompanyParentVo!=null){
                        sysCompanyVo.setParentName(sysCompanyParentVo.getShortName());
                        sysCompanyVo.setParentCompanyType(sysCompanyParentVo.getCompanyType());
                    }
                }
            }
        }
        return pageVo;
    }

    /**
     * 判断公司是否是最底层的公司，只有最低层的公司，才允许添加部门
     *
     * @param id id
     * @return {@link Integer}
     */
    @Override
    public Integer judgeGroupCompany(Long id) {
        //1.验证id的有效性
        SysCompanyVo sysCompanyVo=this.queryById(id);
        if(sysCompanyVo==null){
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.判断是否是集团公司
        SysCompanyFilterQuery sysCompanyFilterQuery = new SysCompanyFilterQuery();
        sysCompanyFilterQuery.setParentId(id);
        List<SysCompany> sysCompanyList = sysCompanyDao.getSysCompanyByCondition(sysCompanyFilterQuery);
        if(sysCompanyList==null || sysCompanyList.size()==0){
            return IsEndNodeCompany.END_NODE.getCode();  //是最底层的公司
        }else{
            return IsEndNodeCompany.NO_END_NODE.getCode();  //不是最底层的公司
        }
    }

    @Override
    public SysCompanyVo queryById(Long id) {
        SysCompany sysCompany = this.getById(id);
        //数据存在，且是未删除状态
        if (sysCompany != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysCompany.getDeleted())) {
            SysCompanyVo sysCompanyVo = this.objectConversion(sysCompany);
            if(sysCompany.getParentId()!=null){
                SysCompany parentSysCompany=  this.getById(sysCompany.getParentId());
                if(parentSysCompany!=null){
                    sysCompanyVo.setParentName(parentSysCompany.getShortName());
                    sysCompanyVo.setParentCompanyType(parentSysCompany.getCompanyType());
                }
            }
            return sysCompanyVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysCompanyDto sysCompanyDto,String loginUser) {
        //1.验证名称是否存在
        SysCompany sysCompanyNameIsExists=sysCompanyDao.getSysCompanyByShortNameAndNoId(sysCompanyDto.getShortName(),null);
       if(sysCompanyNameIsExists!=null){
           throw new ZtbWebException(ResponseEnum.NAME_ALREADY_EXISTS.getCode(), ResponseEnum.NAME_ALREADY_EXISTS.getMessage());
       }
       //2.验证code是否重复
        SysCompany sysCompanyCodeIsExists=sysCompanyDao.getSysCompanyByCodeAndNoId(sysCompanyDto.getCode(),null);
        if(sysCompanyCodeIsExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(), ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        //2.验证parent_id是否存在
        if(sysCompanyDto.getParentId()!=0){//0是集团 ，根节点
            SysCompany parentSysCompany=this.getById(sysCompanyDto.getParentId());
            if(parentSysCompany==null){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_NOT_EXISTS.getCode(), ResponseEnum.PARENT_COMPANY_IS_NOT_EXISTS.getMessage());
            }
            //3.公司类型是集团，所属父公司必须是集团 ，不能是子公司
            if(CompanyTypeEnum.GROUP_COMPANY.getCode().equals(sysCompanyDto.getCompanyType())
                    && CompanyTypeEnum.NO_GROUP_COMPANY.getCode().equals(parentSysCompany.getCompanyType())){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_NOT_GROUP_COMPANY.getCode(), ResponseEnum.PARENT_COMPANY_IS_NOT_GROUP_COMPANY.getMessage());
            }
            //3..判断父公司下面是否有部门，如果有部门无法维护子公司。只有末节点公司下面才允许添加部门。
            List<SysDepartmentVo> sysDepartmentVoList=sysDepartmentService.getByCompanyId(sysCompanyDto.getParentId());
            if(sysDepartmentVoList!=null && sysDepartmentVoList.size()>0){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_OWNER_DEPARTMENT.getCode(), ResponseEnum.PARENT_COMPANY_IS_OWNER_DEPARTMENT.getMessage());
            }
        }

        SysCompany sysCompany = new SysCompany();
        org.springframework.beans.BeanUtils.copyProperties(sysCompanyDto, sysCompany);
        long currentTime=DateUtil.current();
        sysCompany.setUpdatedTime(currentTime);
        sysCompany.setCreatedTime(currentTime);
        sysCompany.setUpdatedUser(loginUser);
        sysCompany.setCreatedUser(loginUser);
        sysCompany.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysCompany);
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchSyncInsert(List<SysCompanyDto> sysCompanyDtoList,String loginUser){
    }
    @Override
    public List<TableColumnVo> getTableColumns(String tableName){
        return sysCompanyDao.getTableColumns(tableName);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysCompanyDto sysCompanyDto,String loginUser) {
        //1.验证记录是否存在
        SysCompany sysCompany = this.getById(sysCompanyDto.getId());
        if (sysCompany == null ||  IsDeletedEnum.DELETED.getCode().equals(sysCompany.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证名称是否已经存在了
        SysCompany sysCompanyNameIsExists=sysCompanyDao.getSysCompanyByShortNameAndNoId(sysCompanyDto.getShortName(),sysCompanyDto.getId());
        if(sysCompanyNameIsExists!=null){
            throw new ZtbWebException(ResponseEnum.NAME_ALREADY_EXISTS.getCode(), ResponseEnum.NAME_ALREADY_EXISTS.getMessage());
        }
        SysCompany sysCompanyCodeIsExists=sysCompanyDao.getSysCompanyByCodeAndNoId(sysCompanyDto.getCode(),sysCompanyDto.getId());
        if(sysCompanyCodeIsExists!=null){
            throw new ZtbWebException(ResponseEnum.CODE_ALREADY_EXISTS.getCode(), ResponseEnum.CODE_ALREADY_EXISTS.getMessage());
        }
        //3.验证parent_id是否存在
        if(sysCompanyDto.getParentId()!=0){//0是集团 ，根节点
            SysCompany parentSysCompany=this.getById(sysCompanyDto.getParentId());
            if(parentSysCompany==null){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_NOT_EXISTS.getCode(), ResponseEnum.PARENT_COMPANY_IS_NOT_EXISTS.getMessage());
            }
            //3.公司类型是集团，所属父公司必须是集团 ，不能是子公司
            if(CompanyTypeEnum.GROUP_COMPANY.getCode().equals(sysCompanyDto.getCompanyType())
                    && CompanyTypeEnum.NO_GROUP_COMPANY.getCode().equals(parentSysCompany.getCompanyType())){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_NOT_GROUP_COMPANY.getCode(), ResponseEnum.PARENT_COMPANY_IS_NOT_GROUP_COMPANY.getMessage());
            }
            //4.判断父公司下面是否有部门，如果有部门无法维护子公司。只有末节点公司下面才允许添加部门。
            List<SysDepartmentVo> sysDepartmentVoList=sysDepartmentService.getByCompanyId(sysCompanyDto.getParentId());
            if(sysDepartmentVoList!=null && sysDepartmentVoList.size()>0){
                throw new ZtbWebException(ResponseEnum.PARENT_COMPANY_IS_OWNER_DEPARTMENT.getCode(), ResponseEnum.PARENT_COMPANY_IS_OWNER_DEPARTMENT.getMessage());
            }
        }
        org.springframework.beans.BeanUtils.copyProperties(sysCompanyDto, sysCompany);
        long currentTime=DateUtil.current();
        sysCompany.setUpdatedTime(currentTime);
        sysCompany.setUpdatedUser(loginUser);
        this.updateById(sysCompany);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        //1.验证id是否存在
        SysCompany sysCompany = this.getById(id);
        if (sysCompany == null||  IsDeletedEnum.DELETED.getCode().equals(sysCompany.getDeleted())) {
            throw new ZtbWebException(ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getCode(), ResponseEnum.DATA_RECORD_IS_NOT_EXISTS.getMessage());
        }
        //2.验证公司是否已经被绑定了
        List<SysDepartmentVo> sysDepartmentVoList=sysDepartmentService.getByCompanyId(id);
        if(sysDepartmentVoList!=null && sysDepartmentVoList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        //3.验证公司是否有被角色绑定在使用
        List<SysRoleVo>sysRoleVoList=sysRoleService.getByCompanyId(id);
        if(sysRoleVoList!=null && sysRoleVoList.size()>0){
            throw new ZtbWebException(ResponseEnum.DATA_QUOTE.getCode(),
                    ResponseEnum.DATA_QUOTE.getMessage());
        }
        long currentTime= DateUtil.current();
        sysCompany.setUpdatedTime(currentTime);
        sysCompany.setUpdatedUser(loginUser);
        sysCompany.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysCompany);
    }

    @Override
    public List<SysCompanyVo> getSysCompanyTree() {
        SysCompanyFilterQuery sysCompanyFilterQuery = new SysCompanyFilterQuery();
        //3.将dto对象转换为query对象
        //4.查询
        List<SysCompany> sysCompanyList = sysCompanyDao.getSysCompanyByCondition(sysCompanyFilterQuery);

        List<SysCompanyVo> sysCompanyVoList=this.objectConversion(sysCompanyList);
        return bulid(sysCompanyVoList);
    }
    public <T extends SysCompanyVo> List<T> bulid(List<T> treeNodes) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            //找到根节点
            if (treeNode.getParentId() == null|| treeNode.getParentId().equals(0L)) {
                trees.add(treeNode);
            }else{
                SysCompanyVo sysCompanyVo=  queryById(treeNode.getParentId());
                if(sysCompanyVo!=null){
                    treeNode.setParentCompanyType(sysCompanyVo.getCompanyType());
                    treeNode.setParentName(sysCompanyVo.getShortName());
                }
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
    public List<SysCompanyDepartmentVo> getSysCompanyDepartmentTree(Boolean isDisabled){
        List<SysCompanyDepartmentVo> sysCompanyDepartmentVoList = sysCompanyDao.getSysCompanyDepartmentTree();
        return bulidSysCompanyDepartmentTree(sysCompanyDepartmentVoList,isDisabled);
    }
    public <T extends SysCompanyDepartmentVo> List<T> bulidSysCompanyDepartmentTree(List<T> treeNodes,Boolean isDisabled) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            //找到根节点
            if (OrganizationStructureType.COMPANY.getCode().equals(treeNode.getType()) &&(treeNode.getParentId() == null|| treeNode.getParentId().equals(0L)) ) {
                trees.add(treeNode);
            }
            // 主要是给前端使用，控制是否禁用
            if(isDisabled){
                if(OrganizationStructureType.COMPANY.getCode().equals(treeNode.getType())){
                    treeNode.setDisabled(true);
                }else{
                    treeNode.setDisabled(false);
                }
            }

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
