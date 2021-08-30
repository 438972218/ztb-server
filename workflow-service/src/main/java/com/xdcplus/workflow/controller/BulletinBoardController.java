package com.xdcplus.workflow.controller;


import com.xdcplus.workflow.common.pojo.dto.BulletinBoardDTO;
import com.xdcplus.workflow.common.pojo.dto.BulletinBoardFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.BulletinBoardVO;
import com.xdcplus.workflow.service.BulletinBoardService;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 公告牌信息前端控制器
 *
 * @author Rong.Jia
 * @since 2021-08-16
 */
@Slf4j
@Validated
@Api(tags = "公告牌信息")
@RestController
@RequestMapping("/bulletinBoard")
public class BulletinBoardController extends AbstractController {

    @Autowired
    private BulletinBoardService bulletinBoardService;

    @ApiOperation("同步公告板信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO syncBulletinBoard(@RequestBody BulletinBoardDTO bulletinBoardDTO) {

        log.info("syncBulletinBoard {}", bulletinBoardDTO.toString());

        bulletinBoardService.syncBulletinBoard(bulletinBoardDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除公告板信息")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "公告板信息ID", required = true),
    })
    public ResponseVO deleteBulletinBoard(@PathVariable("id")
                                          @NotNull(message = "公告板信息ID 不能为空")
                                                  Long id) {

        log.info("deleteBulletinBoard {}", id);

        bulletinBoardService.deleteBulletinBoard(id);

        return ResponseVO.success();
    }

    @ApiOperation("查询公告板信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BulletinBoardVO>> findBulletinBoard(BulletinBoardFilterDTO pageDTO) {

        log.info("findBulletinBoard {}", pageDTO.toString());

        PageVO<BulletinBoardVO> pageVO = bulletinBoardService.findBulletinBoard(pageDTO);

        return ResponseVO.success(pageVO);
    }


}
