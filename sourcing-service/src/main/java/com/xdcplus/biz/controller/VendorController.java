package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.biz.common.pojo.dto.VendorFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.VendorVO;
import com.xdcplus.biz.service.VendorService;
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

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 供应商(Vendor)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:16
 */
@Api(tags = "供应商(Vendor)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendor")
public class VendorController extends AbstractController {

    @Autowired
    private VendorService vendorService;

    @ApiOperation("新增供应商")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendor(@RequestBody VendorDTO vendorDTO) {

        log.info("saveVendor {}", vendorDTO.toString());

        vendorDTO.setCreatedUser(getAccount());
        vendorService.saveVendor(vendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendor(@RequestBody VendorDTO vendorDTO) {

        log.info("updateVendor {}", vendorDTO.toString());

        vendorDTO.setUpdatedUser(getAccount());
        vendorService.updateVendor(vendorDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商")
    @DeleteMapping(value = "/{vendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorId", dataType = "Long", value = "供应商ID", required = true),
    })
    public ResponseVO deleteVendorLogical(@PathVariable("vendorId")
                                          @NotNull(message = "供应商ID不能为空") Long vendorId) {

        log.info("deleteVendorLogical {}", vendorId);

        vendorService.deleteVendorLogical(vendorId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorVO>> findVendor(VendorFilterDTO vendorFilterDTO) {

        log.info("findVendor {}", vendorFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorFilterDTO);

        return ResponseVO.success(vendorService.queryVendor(vendorFilterDTO));
    }



//    @ApiOperation("show供应商")
//    @GetMapping(value = "/show/{vendorId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "path", name = "vendorId", dataType = "Long", value = "供应商", required = true),
//    })
//    public ResponseVO<VendorVO> showVendorById(@PathVariable("vendorId")
//                                               @NotNull(message = "供应商ID不能为空") Long vendorId) {
//        log.info("showVendorById {}", vendorId);
//        return ResponseVO.success(vendorService.showVendorById(vendorId));
//    }
//
//    @ApiOperation("修改供应商WithDetail")
//    @PutMapping(value = "/withDetail", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseVO updateVendorQuestionWithDetail(@RequestBody @Valid VendorDTO vendorDTO) {
//
//        log.info("updateVendorWithDetail {}", vendorDTO.toString());
//
//        vendorDTO.setUpdatedUser(getAccount());
//
//        vendorService.updateVendorWithDetail(vendorDTO);
//
//        return ResponseVO.success();
//    }

    @ApiOperation("show供应商ByUserId")
    @GetMapping(value = "/showByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataType = "Long", value = "用户id", required = true),
    })
    public ResponseVO<VendorVO> showVendorByUserId(@PathVariable("userId")
                                                   @NotNull(message = "用户id不能为空") Long userId) {
        log.info("showVendorByUserId {}", userId);
        return ResponseVO.success(vendorService.showVendorByUserId(userId));
    }


}
