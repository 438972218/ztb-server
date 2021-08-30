package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.FieldsThatDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 字段那业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/25
 */
class FieldsThatServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private FieldsThatService fieldsThatService;

    @Test
    void deleteFieldsThat() {
    }

    @Test
    void findFieldsThats() {

        System.out.println(fieldsThatService.findFieldsThats((byte) 3));

    }

    @Test
    void syncFieldsThat() {

        FieldsThatDTO fieldsThatDTO = new FieldsThatDTO();
        fieldsThatDTO.setFieldName("account");
        fieldsThatDTO.setLdapName("cn");
        fieldsThatDTO.setType((byte)3);

        System.out.println(fieldsThatService.syncFieldsThat(fieldsThatDTO));

    }
}