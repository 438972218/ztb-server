package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeFilterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 表单类型服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/05 15:02:28
 */
class RequestTypeServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private RequestTypeService requestTypeService;

    @Test
    void saveRequestType() {
        RequestTypeDTO requestTypeDTO = new RequestTypeDTO();
        requestTypeDTO.setRequestType("报销类");

        Long aLong = requestTypeService.saveRequestType(requestTypeDTO);
        System.out.println(aLong);

    }

    @Test
    void updateRequestType() {

        RequestTypeDTO requestTypeDTO = new RequestTypeDTO();
        requestTypeDTO.setRequestType("报销类");
        requestTypeDTO.setId(1423178732566982657L);
        requestTypeDTO.setDescription("1232132132");

        Long aLong = requestTypeService.updateRequestType(requestTypeDTO);
        System.out.println(aLong);


    }

    @Test
    void deleteRequestType() {

        requestTypeService.deleteRequestType(1L);

    }

    @Test
    void findRequestType() {

        RequestTypeFilterDTO requestTypeFilterDTO = new RequestTypeFilterDTO();
        requestTypeFilterDTO.setTypeName("请");
        requestTypeFilterDTO.setPageSize(20);
        requestTypeFilterDTO.setCurrentPage(1);

        System.out.println(requestTypeService.findRequestType(requestTypeFilterDTO));

    }

    @Test
    void validationExists() {

        System.out.println(requestTypeService.validationExists("adas"));

    }


}