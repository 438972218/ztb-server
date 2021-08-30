package com.xdcplus.workflow.service;

import com.alibaba.fastjson.JSON;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.PostDiscussionsDTO;
import com.xdcplus.workflow.common.pojo.vo.DiscussRecordVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 讨论分发服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/19 10:22:11
 */
class DiscussDistributionServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private DiscussDistributionService discussDistributionService;

    @Test
    void postDiscussions() {

        PostDiscussionsDTO discussionsDTO = new PostDiscussionsDTO();
        discussionsDTO.setContent("1244");
        discussionsDTO.setRequestId(1L);
        discussionsDTO.setSubject("13123123123");
        discussionsDTO.setToUsers(Arrays.asList("a", "b"));

        discussDistributionService.postDiscussions(discussionsDTO);

    }

    @Test
    void findDiscussGroup() {
    }

    @Test
    void findDiscussRecordsByGroupId() {

        List<DiscussRecordVO> discussRecordVOList = discussDistributionService.findDiscussRecordsByGroupId(1428181886412914689L);

        System.out.println(JSON.toJSONString(discussRecordVOList));
    }

    @Test
    void findDiscussRecordsByRequestId() {

        List<DiscussRecordVO> discussRecordVOList = discussDistributionService.findDiscussRecordsByRequestId(1430934989762027521L);

        System.out.println(JSON.toJSONString(discussRecordVOList));
    }













}