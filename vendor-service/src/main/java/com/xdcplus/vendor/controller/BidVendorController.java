package com.xdcplus.vendor.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDTO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.vendor.common.pojo.vo.BidVendorVO;
import com.xdcplus.vendor.service.BidVendorService;
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
 * 招标投标方(BidVendor)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:42:09
 */
@Api(tags = "招标投标方(BidVendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidVendor")
public class BidVendorController extends AbstractController {

    @Autowired
    private BidVendorService bidVendorService;

    @ApiOperation("新增招标投标方")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendor(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("saveBidVendor {}", bidVendorDTO.toString());

        bidVendorDTO.setCreatedUser(getAccount());
        bidVendorService.saveBidVendor(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标投标方")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendor(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendor {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendor(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标投标方")
    @DeleteMapping(value = "/{bidVendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidVendorId", dataType = "Long", value = "招标投标方ID", required = true),
    })
    public ResponseVO deleteBidVendorLogical(@PathVariable("bidVendorId")
                                             @NotNull(message = "招标投标方ID不能为空") Long bidVendorId) {

        log.info("deleteBidVendorLogical {}", bidVendorId);

        bidVendorService.deleteBidVendorLogical(bidVendorId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标投标方")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidVendorVO>> findBidVendor(BidVendorFilterDTO bidVendorFilterDTO) {

        log.info("findBidVendor {}", bidVendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidVendorFilterDTO);

        return ResponseVO.success(bidVendorService.queryBidVendor(bidVendorFilterDTO));
    }



    

    @ApiOperation("供应商修改招标投标方（状态）(已回复/已拒绝/已撤回)")
    @PutMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorStatus(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendorStatus {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendorStatusByVendorUserId(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("供应商修改招标投标方（查看时间）")
    @PutMapping(value = "/checkTime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidVendorCheckTime(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("updateBidVendorCheckTime {}", bidVendorDTO.toString());

        bidVendorDTO.setUpdatedUser(getAccount());
        bidVendorService.updateBidVendorCheckTimeByVendorUserId(bidVendorDTO);

        return ResponseVO.success();
    }

}
