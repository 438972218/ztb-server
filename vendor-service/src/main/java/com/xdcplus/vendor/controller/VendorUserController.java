package com.xdcplus.vendor.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.dto.VendorUserDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorUserFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;
import com.xdcplus.vendor.service.VendorUserService;
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
 * 供应商用户(VendorUser)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
@Api(tags = "供应商用户(VendorUser)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorUser")
public class VendorUserController extends AbstractController {

    @Autowired
    private VendorUserService vendorUserService;

    @ApiOperation("新增供应商用户")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorUser(@RequestBody VendorUserDTO vendorUserDTO) {

        log.info("saveVendorUser {}", vendorUserDTO.toString());

        vendorUserDTO.setCreatedUser(getAccount());
        vendorUserService.saveVendorUser(vendorUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改供应商用户")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorUser(@RequestBody VendorUserDTO vendorUserDTO) {

        log.info("updateVendorUser {}", vendorUserDTO.toString());

        vendorUserDTO.setUpdatedUser(getAccount());
        vendorUserService.updateVendorUser(vendorUserDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除供应商用户")
    @DeleteMapping(value = "/{vendorUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorUserId", dataType = "Long", value = "供应商用户ID", required = true),
    })
    public ResponseVO deleteVendorUserLogical(@PathVariable("vendorUserId")
                                              @NotNull(message = "供应商用户ID不能为空") Long vendorUserId) {

        log.info("deleteVendorUserLogical {}", vendorUserId);

        vendorUserService.deleteVendorUserLogical(vendorUserId);

        return ResponseVO.success();
    }

    @ApiOperation("查询供应商用户")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorUserVO>> findVendorUser(VendorUserFilterDTO vendorUserFilterDTO) {

        log.info("findVendorUser {}", vendorUserFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorUserFilterDTO);

        return ResponseVO.success(vendorUserService.queryVendorUser(vendorUserFilterDTO));
    }


}
