package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 同步业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
class SyncServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private SyncService syncService;

    @Test
    void syncDepartment() {

        syncService.syncDepartment();

    }

    @Test
    void syncUser() {

        syncService.syncUser();
    }

    @Test
    void syncOrganization() {
    }
}