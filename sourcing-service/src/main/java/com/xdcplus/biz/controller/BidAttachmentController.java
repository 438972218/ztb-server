package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.BidAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.BidAttachmentFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.BidAttachmentVO;
import com.xdcplus.biz.service.BidAttachmentService;
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
 * 招标附件(BidAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:13
 */
@Api(tags = "招标附件(BidAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidAttachment")
public class BidAttachmentController extends AbstractController {

    @Autowired
    private BidAttachmentService bidAttachmentService;

    @ApiOperation("新增招标附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidAttachment(@RequestBody BidAttachmentDTO bidAttachmentDTO) {

        log.info("saveBidAttachment {}", bidAttachmentDTO.toString());

        bidAttachmentDTO.setCreatedUser(getAccount());
        bidAttachmentService.saveBidAttachment(bidAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改招标附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidAttachment(@RequestBody BidAttachmentDTO bidAttachmentDTO) {

        log.info("updateBidAttachment {}", bidAttachmentDTO.toString());

        bidAttachmentDTO.setUpdatedUser(getAccount());
        bidAttachmentService.updateBidAttachment(bidAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除招标附件")
    @DeleteMapping(value = "/{bidAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidAttachmentId", dataType = "Long", value = "招标附件ID", required = true),
    })
    public ResponseVO deleteBidAttachmentLogical(@PathVariable("bidAttachmentId")
                                                 @NotNull(message = "招标附件ID不能为空") Long bidAttachmentId) {

        log.info("deleteBidAttachmentLogical {}", bidAttachmentId);

        bidAttachmentService.deleteBidAttachmentLogical(bidAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询招标附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidAttachmentVO>> findBidAttachment(BidAttachmentFilterDTO bidAttachmentFilterDTO) {

        log.info("findBidAttachment {}", bidAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidAttachmentFilterDTO);

        return ResponseVO.success(bidAttachmentService.queryBidAttachment(bidAttachmentFilterDTO));
    }


}
