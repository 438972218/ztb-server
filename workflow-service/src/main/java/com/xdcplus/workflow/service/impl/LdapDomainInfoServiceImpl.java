package com.xdcplus.workflow.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.common.pojo.vo.FieldsThatVO;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.workflow.common.utils.LdapUtils;
import com.xdcplus.workflow.service.FieldsThatService;
import com.xdcplus.workflow.service.LdapDomainInfoService;
import com.xdcplus.workflow.service.LdapService;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ldap域信息业务层接口
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
@Slf4j
@Service
public class LdapDomainInfoServiceImpl implements LdapDomainInfoService {

    @Autowired
    private LdapService ldapService;

    @Autowired
    private FieldsThatService fieldsThatService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Map<String, Object>> findAdDept() {

        LdapVO ldapVO = ldapService.findLdap();

        List<FieldsThatVO> fieldsThatVOList = fieldsThatService.findFieldsThats(Convert.toByte(NumberConstant.TWO));

        List<Map<String, Object>> adDepartments = LdapUtils.getADDepartments(ldapVO, LdapUtils.ORGANIZATIONAL_UNIT,
                ldapVO.getBaseOu(), fieldsThatVOList);

        if (CollectionUtil.isNotEmpty(adDepartments)) {
            adDepartments.forEach(a -> a.put(LdapUtils.PARENT, NumberConstant.ZERO));
        }

        if (CollectionUtil.isNotEmpty(adDepartments)) {
            redisUtils.set(CacheConstant.AD_DEPARTMENT_CACHE, JSON.toJSONString(adDepartments));
        }

        return adDepartments;
    }

    @Override
    public List<Map<String, Object>> findAdDeptByParent(Map<String, Object> dept) {

        LdapVO ldapVO = ldapService.findLdap();

        String ou = Convert.toStr(dept.get(LdapUtils.DISTINGUISHED_NAME));
        if (StrUtil.isBlank(ou)) {
            ou = ldapVO.getBaseOu();
        }

        List<FieldsThatVO> fieldsThatVOList = fieldsThatService.findFieldsThats(Convert.toByte(NumberConstant.TWO));

        List<Map<String, Object>> adDepartments = LdapUtils.getADDepartments(ldapVO, LdapUtils.ORGANIZATIONAL_UNIT,
                ou, fieldsThatVOList);

        if (CollectionUtil.isNotEmpty(adDepartments)) {
            adDepartments.forEach(a -> {
                Object o = dept.get(LdapUtils.LEVEL);
                a.put(LdapUtils.PARENT, ObjectUtil.isNull(o) ? NumberConstant.ZERO : o);
            });
        }

        setCache(adDepartments, CacheConstant.AD_DEPARTMENT_CACHE);

        return adDepartments;

    }

    @Override
    public List<Map<String, Object>> findAdUserByDept(Map<String, Object> dept) {

        LdapVO ldapVO = ldapService.findLdap();

        List<FieldsThatVO> fieldsThatVOList = fieldsThatService.findFieldsThats(Convert.toByte(NumberConstant.THREE));

        List<Map<String, Object>> adUsers = LdapUtils.getADUsers(ldapVO, ldapVO.getConditions(), Convert.toStr(dept.get(LdapUtils.DISTINGUISHED_NAME)),
                fieldsThatVOList);

        if (CollectionUtil.isNotEmpty(adUsers)) {
            adUsers.forEach(a -> {
                a.put(LdapUtils.DEPT, dept.get(LdapUtils.LEVEL));
            });
        }

        return adUsers;
    }

    @Override
    public List<Map<String, Object>> getAdCache(String cacheKey) {
        return getCache(cacheKey);
    }

    /**
     * 设置缓存
     *
     * @param adDepartments 部门信息
     * @param cacheKey      缓存键
     */
    private void setCache(List<Map<String, Object>> adDepartments, String cacheKey) {

        if (CollectionUtil.isEmpty(adDepartments)) {
            return;
        }

        Object cacheObj = redisUtils.get(cacheKey);

        String cache;
        if (ObjectUtil.isNull(cacheObj)) {
            cache = JSON.toJSONString(adDepartments);
        } else {
            List<Map<String, Object>> mapList = JSONObject.parseObject(cacheObj.toString(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });
            mapList.addAll(adDepartments);
            cache = JSON.toJSONString(mapList);
        }

        redisUtils.set(CacheConstant.AD_DEPARTMENT_CACHE, cache);

    }

    /**
     * 查询缓存
     *
     * @param cacheKey 缓存key
     * @return {@link List<Map<String, Object>>} 部门信息
     */
    private List<Map<String, Object>> getCache(String cacheKey) {
        Object cacheObj = redisUtils.get(cacheKey);
        if (ObjectUtil.isNotNull(cacheObj)) {
            return JSONObject.parseObject(cacheObj.toString(), new TypeReference<List<Map<String, Object>>>() {
            });
        }

        return null;
    }


}
