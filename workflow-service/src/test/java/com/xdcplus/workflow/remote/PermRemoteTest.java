package com.xdcplus.workflow.remote;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限收放远程测试类
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:55:12
 */
class PermRemoteTest extends WorkflowServiceApplicationTests {

    @Autowired
    private PermRemote permRemote;

    @Test
    void getGeneralManagerSysUser() {

        ResponseVO<SysUserInfoVO> responseVO = permRemote.getGeneralManagerSysUser();

        System.out.println(responseVO.toString());

    }
}