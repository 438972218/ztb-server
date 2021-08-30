package com.xdcplus.workflow.service;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.RequestStrategyDTO;
import com.xdcplus.workflow.common.pojo.vo.FunctionalStrategyVO;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 功能策略服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/06 13:29:13
 */
class FunctionalStrategyServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private FunctionalStrategyService functionalStrategyService;

    @Test
    void saveStrategy() {

        RequestStrategyDTO requestStrategyDTO = new RequestStrategyDTO();
        requestStrategyDTO.setConfigVersion("1");
        requestStrategyDTO.setProcessId(1412588961516351490L);
        requestStrategyDTO.setRequestTypeId(1423448080393150465L);
        requestStrategyDTO.setScript("xxx=1231");

        functionalStrategyService.syncRequestStrategy(requestStrategyDTO);

    }

    @Test
    void updateStrategy() {
    }

    @Test
    void deleteStrategy() {
    }

    @Test
    void findStrategy() {

        PageDTO pageDTO = new PageDTO();
        pageDTO.setCurrentPage(1);
        pageDTO.setPageSize(20);

        PageVO<FunctionalStrategyVO> pageVO = functionalStrategyService.findStrategy(pageDTO);

        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void calculateFormPolicy() {

        Map<String, Object> conditions = MapUtil.newHashMap();
        conditions.put("xxx", 1231);

        FunctionalStrategyVO functionalStrategyVO = functionalStrategyService.calculateFormPolicy(1423448080393150465L, conditions);

        System.out.println(functionalStrategyVO);

    }


}