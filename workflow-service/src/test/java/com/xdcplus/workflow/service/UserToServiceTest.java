package com.xdcplus.workflow.service;

import com.alibaba.fastjson.JSON;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.vo.UserToVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户去向信息业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
class UserToServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private UserToService userToService;

    @Test
    void findUserTo() {

        List<UserToVO> userTo = userToService.findUserTo();

        System.out.println(JSON.toJSONString(userTo));

    }
}