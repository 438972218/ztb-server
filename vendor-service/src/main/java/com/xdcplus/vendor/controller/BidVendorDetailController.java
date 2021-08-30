package com.xdcplus.vendor.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.vendor.common.pojo.vo.BidVendorDetailVO;
import com.xdcplus.vendor.service.BidVendorDetailService;
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
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-26 09:41:39
 */
@Api(tags = "供应商内容明细（国内报价、国外报价）(BidVendorDetail)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendorDetail")
public class BidVendorDetailController extends AbstractController {

    @Autowired
    private BidVendorDetailService bidVendorDetailService;

    @ApiOperation("新增供应商内容明细（国内报价、国外报价）")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorDetail(@RequestBody BidVendorDetailDTO bidVendorDetailDTO) {

        log.info("saveBidVendorDetail {}", bidVendorDetailDTO.toString());

        bidVendorDetailDTO.setCreatedUser(getAccount());
        bidVendorDetailService.saveBidVendorDetail(bidVendorDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商内容明细（国内报价、国外报价）")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorDetail(@RequestBody BidVendorDetailDTO bidVendorDetailDTO) {

        log.info("updateBidVendorDetail {}", bidVendorDetailDTO.toString());

        bidVendorDetailDTO.setUpdatedUser(getAccount());
        bidVendorDetailService.updateBidVendorDetail(bidVendorDetailDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商内容明细（国内报价、国外报价）")
    @DeleteMapping(value = "/{bidVendorDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorDetailId", dataType = "Long", value = "供应商内容明细（国内报价、国外报价）ID", required = true),
    })
    public ResponseVO deleteBidVendorDetailLogical(@PathVariable("bidVendorDetailId")
                                                   @NotNull(message = "供应商内容明细（国内报价、国外报价）ID不能为空") Long bidVendorDetailId) {

        log.info("deleteBidVendorDetailLogical {}", bidVendorDetailId);

        bidVendorDetailService.deleteBidVendorDetailLogical(bidVendorDetailId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商内容明细（国内报价、国外报价）")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorDetailVO>> findBidVendorDetail(BidVendorDetailFilterDTO bidVendorDetailFilterDTO) {

        log.info("findBidVendorDetail {}", bidVendorDetailFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorDetailFilterDTO);

        return ResponseVO.success(bidVendorDetailService.queryBidVendorDetail(bidVendorDetailFilterDTO));
    }


    @ApiOperation("批量修改供应商内容明细（国内报价、国外报价）")
    @PutMapping(value = "batchUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidVendorDetail(@RequestBody List<BidVendorDetailDTO> bidVendorDetailDTOS) {

        log.info("batchUpdateBidVendorDetail {}", bidVendorDetailDTOS.toString());
        if (CollectionUtil.isNotEmpty(bidVendorDetailDTOS)) {
            bidVendorDetailDTOS.forEach(bidDetailDTO -> {
                bidDetailDTO.setUpdatedUser(getAccount());
            });
        }

        bidVendorDetailService.batchUpdateBidDetailDTO(bidVendorDetailDTOS);

        return ResponseVO.success();
    }

    @ApiOperation("根据操作状态修改供应商内容明细状态")
    @PutMapping(value = "operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorDetailByOperation(@RequestBody BidVendorDetailDTO bidVendorDetailDTO) {

        log.info("updateBidVendorDetailByOperation {}", bidVendorDetailDTO.toString());

        bidVendorDetailDTO.setUpdatedUser(getAccount());
        bidVendorDetailService.updateBidVendorDetailByOperation(bidVendorDetailDTO.getBidVendorId(),bidVendorDetailDTO.getOperation());

        return ResponseVO.success();
    }


}
