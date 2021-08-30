package com.xdcplus.biz.controller;


import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.biz.common.pojo.vo.VendorVO;
import com.xdcplus.biz.service.VendorService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.biz.service.BidVendorService;
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
 * 招标投标方(BidVendor)表服务控制层
 *
 * @author makejava
 * @since 2021-08-12 14:45:47
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

    @ApiOperation("新增招标投标方带默认值(新建阶段)")
    @PostMapping(value = "insertWithDefault", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorWithDefault(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("saveBidVendorWithDefault {}", bidVendorDTO.toString());

        bidVendorDTO.setCreatedUser(getAccount());
        bidVendorService.saveBidVendorWithDefault(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("新增招标投标方withDetail(正在运行)")
    @PostMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidVendorWithDetail(@RequestBody BidVendorDTO bidVendorDTO) {

        log.info("saveBidVendorWithDetail {}", bidVendorDTO.toString());

        bidVendorDTO.setCreatedUser(getAccount());
        bidVendorService.saveBidVendorWithDetail(bidVendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("批量修改招标投标方")
    @PutMapping(value = "batchUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchUpdateBidVendor(@RequestBody List<BidVendorDTO> bidVendorDTOS) {

        log.info("batchUpdateBidVendor {}", bidVendorDTOS.toString());
        bidVendorDTOS.forEach(bidVendorDTO -> bidVendorDTO.setUpdatedUser(getAccount()));
        bidVendorService.saveOrUpdateBatchByDTOList(bidVendorDTOS);

        return ResponseVO.success();
    }

}
