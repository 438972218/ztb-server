package com.xdcplus.workflow.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.CirculationBeginDTO;
import com.xdcplus.workflow.common.pojo.dto.HandleMattersFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.RequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/02
 */
class RequestServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private RequestService requestService;

    @Test
    void saveRequest() {

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setProcessId(1412588961516351490L);
        requestDTO.setRuleId(1L);
        requestDTO.setTitle("请假测试2");

        CirculationBeginDTO circulationBeginDTO = new CirculationBeginDTO();
        circulationBeginDTO.setDescription("12312312321");
        circulationBeginDTO.setUserId(121L);

        Map<String, Object> flowOptions = new HashMap<>();
        flowOptions.put("requestRelationRequestStatusId", 2);

        circulationBeginDTO.setFlowConditions(flowOptions);

        RequestVO saveRequest = requestService.saveRequest(requestDTO);
        System.out.println(saveRequest);

    }

    @Test
    void saveRequest2() {

        String request = "{\n" +
                "  \"circulation\": {\n" +
                "    \"description\": \"\",\n" +
                "    \"userId\": 1422402499718889473\n" +
                "  },\n" +
                "  \"createdUser\": \"admin\",\n" +
                "  \"description\": \"\",\n" +
                "  \"ruleId\": 1,\n" +
                "  \"strategy\": {\n" +
                "    \"strategyConditions\": {\"preQualification\":\"1\",\n" +
                "  \"approvalQrocess\":\"1\"},\n" +
                "    \"typeId\": 1424531417540403201\n" +
                "  },\n" +
                "  \"title\": \"招标单-测试1\"\n" +
                "}\n";

        RequestDTO requestDTO = JSONObject.parseObject(request, RequestDTO.class);

        System.out.println(requestService.saveRequest(requestDTO));


    }

    @Test
    void saveRequestRelation() {

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setProcessId(1412588961516351490L);
        requestDTO.setRuleId(1L);
        requestDTO.setTitle("请假测试2");

        RequestVO saveRequest = requestService.saveRequest(requestDTO);
        System.out.println(saveRequest);

    }

    @Test
    void findRequest() {

        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setCurrentPage(1);
        requestFilterDTO.setPageSize(20);
        requestFilterDTO.setOddNumber("QJ");
        requestFilterDTO.setKeyword("123123");

        System.out.println(JSON.toJSONString(requestService.findRequest(requestFilterDTO)));

    }

    @Test
    void handleMatters() {

        HandleMattersFilterDTO handleMattersFilterDTO = new HandleMattersFilterDTO();
        handleMattersFilterDTO.setHandleOption(2);
        handleMattersFilterDTO.setUserId(1L);
        handleMattersFilterDTO.setCurrentPage(1);
        handleMattersFilterDTO.setPageSize(20);

        System.out.println(JSON.toJSONString(requestService.handleMatters(handleMattersFilterDTO)));

    }

    @Test
    void findRequestByParentId() {

        List<RequestVO> requestVOList = requestService.findRequestByParentId(1420269228738043906L);

        System.out.println(requestVOList.toString());

    }


}