package com.xdcplus.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.BidDetailDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.BidDetailVO;
import com.xdcplus.biz.service.BidDetailService;
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
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:16
 */
@Api(tags = "招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidDetail")
public class BidDetailController extends AbstractController {

    @Autowired
    private BidDetailService bidDetailService;

    @ApiOperation("新增招标单内容明细（报价须知、国内报价、国外报价）")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidDetail(@RequestBody BidDetailDTO bidDetailDTO) {

        log.info("saveBidDetail {}", bidDetailDTO.toString());

        bidDetailDTO.setCreatedUser(getAccount());
        bidDetailService.saveBidDetail(bidDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标单内容明细（报价须知、国内报价、国外报价）")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidDetail(@RequestBody BidDetailDTO bidDetailDTO) {

        log.info("updateBidDetail {}", bidDetailDTO.toString());

        bidDetailDTO.setUpdatedUser(getAccount());
        bidDetailService.updateBidDetail(bidDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标单内容明细（报价须知、国内报价、国外报价）")
    @DeleteMapping(value = "/{bidDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidDetailId", dataType = "Long", value = "招标单内容明细（报价须知、国内报价、国外报价）ID", required = true),
    })
    public ResponseVO deleteBidDetailLogical(@PathVariable("bidDetailId")
                                             @NotNull(message = "招标单内容明细（报价须知、国内报价、国外报价）ID不能为空") Long bidDetailId) {

        log.info("deleteBidDetailLogical {}", bidDetailId);

        bidDetailService.deleteBidDetailLogical(bidDetailId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标单内容明细（报价须知、国内报价、国外报价）")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidDetailVO>> findBidDetail(BidDetailFilterDTO bidDetailFilterDTO) {

        log.info("findBidDetail {}", bidDetailFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidDetailFilterDTO);

        return ResponseVO.success(bidDetailService.queryBidDetail(bidDetailFilterDTO));
    }

    @ApiOperation("批量新增招标单内容明细（报价须知、国内报价、国外报价）")
    @PostMapping(value = "batchSave", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchSaveBidDetail(@RequestBody List<BidDetailDTO> bidDetailDTOS) {

        log.info("batchSaveBidDetail {}", bidDetailDTOS.toString());
        bidDetailDTOS.forEach(bidDetailDTO -> bidDetailDTO.setCreatedUser(getAccount()));
        bidDetailService.saveOrUpdateBatchByDTOList(bidDetailDTOS);

        return ResponseVO.success();
    }

    @ApiOperation("批量修改招标单内容明细（报价须知、国内报价、国外报价）")
    @PutMapping(value = "batchUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidDetail(@RequestBody BidSheetDTO bidSheetDTO) {

        log.info("batchUpdateBidDetail {}", bidSheetDTO.toString());
        if(CollectionUtil.isNotEmpty(bidSheetDTO.getBidDetailDTOS())){
            bidSheetDTO.getBidDetailDTOS().forEach(bidDetailDTO -> {
                bidDetailDTO.setCreatedUser(getAccount());
                bidDetailDTO.setBidSheetId(bidSheetDTO.getId());
            });
        }

        bidDetailService.batchUpdateBidDetailDTO(bidSheetDTO);

        return ResponseVO.success();
    }


}
