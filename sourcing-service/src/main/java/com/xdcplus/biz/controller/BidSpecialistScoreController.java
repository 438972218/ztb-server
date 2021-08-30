package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistScoreVO;
import com.xdcplus.biz.service.BidSpecialistScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 招标专家评分(BidSpecialistScore)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:30:57
 */
@Api(tags = "招标专家评分(BidSpecialistScore)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidSpecialistScore")
public class BidSpecialistScoreController extends AbstractController {

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @ApiOperation("新增招标专家评分")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidSpecialistScore(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("saveBidSpecialistScore {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setCreatedUser(getAccount());
        bidSpecialistScoreService.saveBidSpecialistScore(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标专家评分")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidSpecialistScore(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("updateBidSpecialistScore {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setUpdatedUser(getAccount());
        bidSpecialistScoreService.updateBidSpecialistScore(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标专家评分")
    @DeleteMapping(value = "/{bidSpecialistScoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSpecialistScoreId", dataType = "Long", value = "招标专家评分ID", required = true),
    })
    public ResponseVO deleteBidSpecialistScoreLogical(@PathVariable("bidSpecialistScoreId")
                                                      @NotNull(message = "招标专家评分ID不能为空") Long bidSpecialistScoreId) {

        log.info("deleteBidSpecialistScoreLogical {}", bidSpecialistScoreId);

        bidSpecialistScoreService.deleteBidSpecialistScoreLogical(bidSpecialistScoreId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标专家评分")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSpecialistScoreVO>> findBidSpecialistScore(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {

        log.info("findBidSpecialistScore {}", bidSpecialistScoreFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistScoreFilterDTO);

        return ResponseVO.success(bidSpecialistScoreService.queryBidSpecialistScore(bidSpecialistScoreFilterDTO));
    }


    @ApiOperation("查询招标专家评分(评估阶段)")
    @GetMapping(value = "/assess", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<BidSpecialistScoreVO>> queryBidSpecialistScoreVOBySpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {

        log.info("queryBidSpecialistScoreVOBySpecialist {}", bidSpecialistScoreFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistScoreFilterDTO);

        return ResponseVO.success(bidSpecialistScoreService.queryBidSpecialistScoreVOBySpecialist(bidSpecialistScoreFilterDTO));
    }

    @ApiOperation("查询招标专家评分(根据供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<BidSpecialistScoreVO>> queryBidSpecialistScoreVOByBidVendor(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {

        log.info("queryBidSpecialistScoreVOByBidVendor {}", bidSpecialistScoreFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSpecialistScoreFilterDTO);

        return ResponseVO.success(bidSpecialistScoreService.queryBidSpecialistScoreVOByBidVendor(bidSpecialistScoreFilterDTO));
    }


    @ApiOperation("更新评分中 是否查看 字段并更新供应商中 是否查看 字段")
    @PutMapping(value = "/whetherView", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateWhetherViewBySpecialist(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("updateWhetherViewBySpecialist {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setUpdatedUser(getAccount());
        bidSpecialistScoreService.updateWhetherViewBySpecialist(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }


    @ApiOperation("修改招标专家评分并更新供应商")
    @PutMapping(value = "/updateScore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateScoreWithBidVendor(@RequestBody BidSpecialistScoreDTO bidSpecialistScoreDTO) {

        log.info("updateScoreWithBidVendor {}", bidSpecialistScoreDTO.toString());

        bidSpecialistScoreDTO.setUpdatedUser(getAccount());
        bidSpecialistScoreService.updateScoreWithBidVendor(bidSpecialistScoreDTO);

        return ResponseVO.success();
    }


}
