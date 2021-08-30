package com.xdcplus.vendor.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.dto.BidSheetDTO;
import com.xdcplus.vendor.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.vendor.common.pojo.vo.BidSheetVO;
import com.xdcplus.vendor.service.BidSheetService;
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


/**
 * bid招标单(BidSheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:37:40
 */
@Api(tags = "bid招标单(BidSheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidSheet")
public class BidSheetController extends AbstractController {

    @Autowired
    private BidSheetService bidSheetService;

    @ApiOperation("新增bid招标单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidSheet(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("saveBidSheet {}", bidSheetDTO.toString());

        bidSheetDTO.setCreatedUser(getAccount());
        bidSheetService.saveBidSheet(bidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改bid招标单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidSheet(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("updateBidSheet {}", bidSheetDTO.toString());

        bidSheetDTO.setUpdatedUser(getAccount());
        bidSheetService.updateBidSheet(bidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除bid招标单")
    @DeleteMapping(value = "/{bidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidSheetId", dataType = "Long", value = "bid招标单ID", required = true),
    })
    public ResponseVO deleteBidSheetLogical(@PathVariable("bidSheetId")
                                            @NotNull(message = "bid招标单ID不能为空") Long bidSheetId) {

        log.info("deleteBidSheetLogical {}", bidSheetId);

        bidSheetService.deleteBidSheetLogical(bidSheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询bid招标单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSheetVO>> findBidSheet(BidSheetFilterDTO bidSheetFilterDTO) {

        log.info("findBidSheet {}", bidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSheetFilterDTO);

        return ResponseVO.success(bidSheetService.queryBidSheet(bidSheetFilterDTO));
    }


    /**
     * 供应商
     *
     * @param bidSheetFilterDTO 报价表过滤dto
     * @return {@link ResponseVO<PageVO<BidSheetVO>>}
     */
    @ApiOperation("查询招标单(供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidSheetVO>> findBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO) {

        log.info("findBidSheetByVendor {}", bidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidSheetFilterDTO);
        PageVO<BidSheetVO> bidSheetVOPageVO = bidSheetService.queryBidSheetByVendor1(bidSheetFilterDTO);

        return ResponseVO.success(bidSheetVOPageVO);
    }


}
