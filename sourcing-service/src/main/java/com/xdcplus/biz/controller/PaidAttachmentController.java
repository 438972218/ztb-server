package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.PaidAttachmentVO;
import com.xdcplus.biz.service.PaidAttachmentService;
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
 * 竞价单附件(PaidAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:01:57
 */
@Api(tags = "竞价单附件(PaidAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidAttachment")
public class PaidAttachmentController extends AbstractController {

    @Autowired
    private PaidAttachmentService paidAttachmentService;

    @ApiOperation("新增竞价单附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidAttachment(@RequestBody PaidAttachmentDTO paidAttachmentDTO) {

        log.info("savePaidAttachment {}", paidAttachmentDTO.toString());

        paidAttachmentDTO.setCreatedUser(getAccount());
        paidAttachmentService.savePaidAttachment(paidAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价单附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidAttachment(@RequestBody PaidAttachmentDTO paidAttachmentDTO) {

        log.info("updatePaidAttachment {}", paidAttachmentDTO.toString());

        paidAttachmentDTO.setUpdatedUser(getAccount());
        paidAttachmentService.updatePaidAttachment(paidAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价单附件")
    @DeleteMapping(value = "/{paidAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidAttachmentId", dataType = "Long", value = "竞价单附件ID", required = true),
    })
    public ResponseVO deletePaidAttachmentLogical(@PathVariable("paidAttachmentId")
                                                  @NotNull(message = "竞价单附件ID不能为空") Long paidAttachmentId) {

        log.info("deletePaidAttachmentLogical {}", paidAttachmentId);

        paidAttachmentService.deletePaidAttachmentLogical(paidAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价单附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidAttachmentVO>> findPaidAttachment(PaidAttachmentFilterDTO paidAttachmentFilterDTO) {

        log.info("findPaidAttachment {}", paidAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidAttachmentFilterDTO);

        return ResponseVO.success(paidAttachmentService.queryPaidAttachment(paidAttachmentFilterDTO));
    }


}
