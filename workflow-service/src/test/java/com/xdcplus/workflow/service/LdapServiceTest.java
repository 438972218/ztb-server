package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.LdapDTO;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ldap业务层接口test
 *
 * @author Rong.Jia
 * @date 2021/06/24
 */
class LdapServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private LdapService ldapService;

    @Test
    void syncLdap() {

        LdapDTO ldapDTO = new LdapDTO();
        ldapDTO.setAddress("ldap://10.20.1.11:389");
        ldapDTO.setDomain("ou=XDCPLUS,dc=CORP,dc=XDCPLUS,dc=com");
        ldapDTO.setSyncOrganization((byte)0);
        ldapDTO.setEnabled((byte)1);
        ldapDTO.setPassword("qwerasdf!1234");
        ldapDTO.setUsername("corp\\Rong.Jia");
        ldapDTO.setConditions("(&(objectCategory=person)(objectClass=user))");

        Boolean aBoolean = ldapService.syncLdap(ldapDTO);
        System.out.println(aBoolean);


    }

    @Test
    void findLdap() {

        LdapVO ldap = ldapService.findLdap();
        System.out.println(ldap.toString());

    }

















}