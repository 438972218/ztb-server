package com.xdcplus.vendor.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.dto.VendorAttachmentDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorAttachmentFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.vendor.common.pojo.vo.VendorAttachmentVO;
import com.xdcplus.vendor.service.VendorAttachmentService;
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
 * 招标附件(VendorAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:55
 */
@Api(tags = "招标附件(VendorAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("vendorAttachment")
public class VendorAttachmentController extends AbstractController {

    @Autowired
    private VendorAttachmentService vendorAttachmentService;

    @ApiOperation("新增招标附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveVendorAttachment(@RequestBody VendorAttachmentDTO vendorAttachmentDTO) {

        log.info("saveVendorAttachment {}", vendorAttachmentDTO.toString());

        vendorAttachmentDTO.setCreatedUser(getAccount());
        vendorAttachmentService.saveVendorAttachment(vendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateVendorAttachment(@RequestBody VendorAttachmentDTO vendorAttachmentDTO) {

        log.info("updateVendorAttachment {}", vendorAttachmentDTO.toString());

        vendorAttachmentDTO.setUpdatedUser(getAccount());
        vendorAttachmentService.updateVendorAttachment(vendorAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标附件")
    @DeleteMapping(value = "/{vendorAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "vendorAttachmentId", dataType = "Long", value = "招标附件ID", required = true),
    })
    public ResponseVO deleteVendorAttachmentLogical(@PathVariable("vendorAttachmentId")
                                                    @NotNull(message = "招标附件ID不能为空") Long vendorAttachmentId) {

        log.info("deleteVendorAttachmentLogical {}", vendorAttachmentId);

        vendorAttachmentService.deleteVendorAttachmentLogical(vendorAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<VendorAttachmentVO>> findVendorAttachment(VendorAttachmentFilterDTO vendorAttachmentFilterDTO) {

        log.info("findVendorAttachment {}", vendorAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(vendorAttachmentFilterDTO);

        return ResponseVO.success(vendorAttachmentService.queryVendorAttachment(vendorAttachmentFilterDTO));
    }


}
