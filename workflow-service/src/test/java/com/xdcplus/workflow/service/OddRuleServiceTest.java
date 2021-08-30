package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.OddRuleDTO;
import com.xdcplus.workflow.common.pojo.dto.OddRuleFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.OddRuleVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 单号规则业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
class OddRuleServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private OddRuleService oddRuleService;

    @Test
    void updateAutoNumber() {


    }

    @Test
    void saveOddRule() {

        OddRuleDTO oddRuleDTO = new OddRuleDTO();
        oddRuleDTO.setAlgorithm(1);
        oddRuleDTO.setName("请假");
        oddRuleDTO.setPrefix("QJ");

        Boolean aBoolean = oddRuleService.saveOddRule(oddRuleDTO);
        System.out.println(aBoolean);
    }

    @Test
    void updateOddRule() {

        OddRuleDTO oddRuleDTO = new OddRuleDTO();
        oddRuleDTO.setAlgorithm(2);
        oddRuleDTO.setName("请假");
        oddRuleDTO.setPrefix("QJ");
        oddRuleDTO.setId(1399283274551631873L);

        Boolean aBoolean = oddRuleService.updateOddRule(oddRuleDTO);
        System.out.println(aBoolean);
    }

    @Test
    void deleteOddRule() {
        Boolean aBoolean = oddRuleService.deleteOddRule(1399283274551631873L);
        System.out.println(aBoolean);
    }

    @Test
    void findOddRule() {
        OddRuleFilterDTO oddRuleFilterDTO = new OddRuleFilterDTO();
        oddRuleFilterDTO.setCurrentPage(1);
        oddRuleFilterDTO.setPageSize(20);
        PageVO<OddRuleVO> pageVO = oddRuleService.findOddRule(oddRuleFilterDTO);
        System.out.println(pageVO);
    }
}