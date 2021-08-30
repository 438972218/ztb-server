package com.xdcplus.workflow.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigTableDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 过程配置控制服务测试类
 *
 * @author Rong.Jia
 * @date 2021/07/30 13:59:12
 */
class ProcessConfigControlServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private ProcessConfigControlService processConfigControlService;

    @Test
    void findProcessConfig() {
    }

    @Test
    void findProcessConfigTable() {

        System.out.println(JSON.toJSONString(processConfigControlService.findProcessConfigTable(1412588961516351490L, null)));


    }

    @Test
    void saveProcessConfig() {
    }

    @Test
    void testSaveProcessConfig() {

        String request = "[\n" +
                "    {\n" +
                "        \"fromStatusMask\": \"0\",\n" +
                "        \"processId\": \"1412588961516351490\",\n" +
                "        \"expression\": {\n" +
                "            \"description\": \"((招标单类型=标准单据)and(询价单类型=标准询比价))\",\n" +
                "            \"expression\": \"((bidStatus=1)and(inquirySheetType=1))\"\n" +
                "        },\n" +
                "        \"timeoutAction\": \"\",\n" +
                "        \"toRoleId\": \"1\",\n" +
                "        \"toStatusMask\": \"6\",\n" +
                "        \"userTo\": 1,\n" +
                "        \"type\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"fromStatusMask\": \"6\",\n" +
                "        \"processId\": \"1412588961516351490\",\n" +
                "        \"expression\": {\n" +
                "            \"description\": \"\",\n" +
                "            \"expression\": \"\"\n" +
                "        },\n" +
                "        \"timeoutAction\": \"\",\n" +
                "        \"toRoleId\": \"1422122685754175490\",\n" +
                "        \"toStatusMask\": \"2\",\n" +
                "        \"userTo\": 4,\n" +
                "        \"type\": -1\n" +
                "    }\n" +
                "]";


        List<ProcessConfigTableDTO> configTableDTOList = JSONArray.parseArray(request, ProcessConfigTableDTO.class);

        processConfigControlService.saveProcessConfig(configTableDTOList);

    }
}