package com.xdcplus.workflow.service;

import java.util.List;
import java.util.Map;

/**
 * ldap域信息业务层接口
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
public interface LdapDomainInfoService {

    /**
     * 查询 AD Ldap 部门信息
     *
     * @return {@link List<Map<String, Object>>} 部门信息集合
     */
    List<Map<String, Object>> findAdDept();

    /**
     * 查询 AD Ldap 部门信息
     *
     * @param dept 部门层级信息
     * @return {@link List<Map<String, Object>>} 部门信息集合
     */
    List<Map<String, Object>> findAdDeptByParent(Map<String, Object> dept);

    /**
     * 查询 AD Ldap 人员信息
     *
     * @param dept 部门信息
     * @return {@link List<Map<String, Object>>} 人员信息集合
     */
    List<Map<String, Object>> findAdUserByDept(Map<String, Object> dept);

    /**
     * 查询 AD Ldap 缓存信息
     *
     * @param cacheKey 缓存Key
     * @return {@link List<Map<String, Object>>} 缓存信息集合
     */
    List<Map<String, Object>> getAdCache(String cacheKey);


}
