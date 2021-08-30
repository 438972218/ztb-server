package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.dto.BulletinBoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公告板服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/16 10:38:37
 */
class BulletinBoardServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private BulletinBoardService bulletinBoardService;

    @Test
    void syncBulletinBoard() {

        BulletinBoardDTO bulletinBoardDTO = new BulletinBoardDTO();
        bulletinBoardDTO.setName("测试");
        bulletinBoardDTO.setDescription("1");
        System.out.println(bulletinBoardService.syncBulletinBoard(bulletinBoardDTO));

    }

    @Test
    void deleteBulletinBoard() {

        System.out.println(bulletinBoardService.deleteBulletinBoard(1427098220534374401L ));

    }

    @Test
    void findBulletinBoard() {

//        PageDTO pageDTO = new PageDTO();
//        pageDTO.setPageSize(20);
//        pageDTO.setCurrentPage(1);
//
//        PageVO<BulletinBoardVO> pageVO = bulletinBoardService.findBulletinBoard(pageDTO);
//        System.out.println(pageVO.toString());

    }
}