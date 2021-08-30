package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * http服务测试类
 *
 * @author Rong.Jia
 * @date 2021/07/29 14:38:24
 */
public class HttpServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private HttpService httpService;

    @Test
    public void getBigBossUser() {

        Long bossUser = httpService.getBigBossUser();
        System.out.println(bossUser);

    }


}
