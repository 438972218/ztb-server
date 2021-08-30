package com.xdcplus.vendor.utils;

import com.xdcplus.vendor.VendorApplicationTest;
import com.xdcplus.vendor.remote.PermRemote;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestFlowTest extends VendorApplicationTest {

    @Autowired
    PermRemote permRemote;

    @Data
    private static class FlowCondition {
        private Integer age;
    }

    @Test
    void test2() {
//        List<RequestVO> responseVO = IeRequestResponseUtils.queryRequestByParentId(1413340059109814274L);
//        System.out.println("");

    }

//    @Test
//    void test3() {
//        RegisterUserDTO registerUserDto=new RegisterUserDTO();
//        registerUserDto.setName("供应商4");
//        registerUserDto.setPassword("123456");
//        ResponseVO a= permUserRemote.registerUser(registerUserDto);
//    }
//    @Test
//    void test4() {
//        ResponseVO<List<SysRoleVO>> a= permRemote.queryByUserId(1L);
//    }


}
