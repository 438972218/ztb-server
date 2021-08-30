package com.xdcplus.workflow.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.workflow.remote.PermRemote;
import com.xdcplus.workflow.service.LdapDomainInfoService;
import com.xdcplus.workflow.service.LdapService;
import com.xdcplus.workflow.service.SyncService;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysDepartmentDTO;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysEmployeeDTO;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.validator.ValidList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 同步业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private LdapService ldapService;

    @Autowired
    private LdapDomainInfoService ldapDomainInfoService;

    @Autowired
    private PermRemote permRemote;

    @Override
    public void syncDepartment() {

        LdapVO ldapVO = ldapService.findLdap();
        Byte syncOrganization = ldapVO.getSyncOrganization();

        if (Validator.equal(Convert.toByte(NumberConstant.ONE), syncOrganization)) {
            List<Map<String, Object>> adDeptList = ldapDomainInfoService.findAdDept();
            syncDepartment(adDeptList);
        }

    }

    @Override
    public void syncUser() {

        LdapVO ldapVO = ldapService.findLdap();
        Byte syncPerson = ldapVO.getSyncPerson();

        if (Validator.equal(Convert.toByte(NumberConstant.ONE), syncPerson)) {
            List<Map<String, Object>> adDeptCache = ldapDomainInfoService.getAdCache(CacheConstant.AD_DEPARTMENT_CACHE);
            if (CollectionUtil.isNotEmpty(adDeptCache)) {
                for (Map<String, Object> adDept : adDeptCache) {
                    List<Map<String, Object>> adUserList = ldapDomainInfoService.findAdUserByDept(adDept);
                    ValidList<SysEmployeeDTO> employeeList = JSONObject.parseObject(JSON.toJSONString(adUserList), new TypeReference<ValidList<SysEmployeeDTO>>() {
                    });
                    permRemote.batchSyncInsertSysEmployee(employeeList);
                }
            }
        }
    }

    @Override
    public void syncOrganization() {

    }

    /**
     * 同步部门
     *
     * @param adDeptList Ldap 部门信息
     */
    private void syncDepartment(List<Map<String, Object>> adDeptList) {

        if (CollectionUtil.isNotEmpty(adDeptList)) {

            // 调用推送接口

            for (Map<String, Object> objectMap : adDeptList) {
                List<Map<String, Object>> adDeptListSub = ldapDomainInfoService.findAdDeptByParent(objectMap);
                if (CollectionUtil.isNotEmpty(adDeptListSub)) {
                    ValidList<SysDepartmentDTO> validList = JSONObject.parseObject(JSON.toJSONString(adDeptListSub), new TypeReference<ValidList<SysDepartmentDTO>>() {
                    });
                    permRemote.batchSyncInsert(validList);
                    syncDepartment(adDeptListSub);
                }

            }
        }
    }


}
