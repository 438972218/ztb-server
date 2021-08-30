package com.xdcplus.workflow.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ldap域信息业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
class LdapDomainInfoServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private LdapDomainInfoService ldapDomainInfoService;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void findAdDept() {

        List<Map<String, Object>> dept = ldapDomainInfoService.findAdDept();

        System.out.println(JSON.toJSONString(dept));
    }

    @Test
    void findAdDeptByParent() {

        Object object = redisUtils.get(CacheConstant.AD_DEPARTMENT_CACHE);
        List<Map<String, Object>> mapList = JSONObject.parseObject(object.toString(), new TypeReference<List<Map<String, Object>>>() {
        });

        mapList.forEach(map -> {
            List<Map<String, Object>> adDeptByParent = ldapDomainInfoService.findAdDeptByParent(map);
            System.out.println(JSON.toJSONString(adDeptByParent));

        });



    }

    @Test
    void findAdUserByDept() {

        Map<String, Object> map = new HashMap<>();
        map.put("distinguishedName", "OU=工业智造研究室,OU=产品研发中心,OU=总经理室,OU=XDCPLUS");
        map.put("level", "0500edac-0074-721c-efbf-bd362446efbf");

        List<Map<String, Object>> userByDept = ldapDomainInfoService.findAdUserByDept(map);
        System.out.println(JSON.toJSONString(userByDept));

    }









}