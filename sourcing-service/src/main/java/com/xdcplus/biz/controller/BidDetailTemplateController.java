package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.BidDetailTemplateVO;
import com.xdcplus.biz.service.BidDetailTemplateService;
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
 * 内容模板(BidDetailTemplate)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:18
 */
@Api(tags = "内容模板(BidDetailTemplate)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("bidDetailTemplate")
public class BidDetailTemplateController extends AbstractController {

    @Autowired
    private BidDetailTemplateService bidDetailTemplateService;

    @ApiOperation("新增内容模板")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveBidDetailTemplate(@RequestBody BidDetailTemplateDTO bidDetailTemplateDTO) {

        log.info("saveBidDetailTemplate {}", bidDetailTemplateDTO.toString());

        bidDetailTemplateDTO.setCreatedUser(getAccount());
        bidDetailTemplateService.saveBidDetailTemplate(bidDetailTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改内容模板")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateBidDetailTemplate(@RequestBody BidDetailTemplateDTO bidDetailTemplateDTO) {

        log.info("updateBidDetailTemplate {}", bidDetailTemplateDTO.toString());

        bidDetailTemplateDTO.setUpdatedUser(getAccount());
        bidDetailTemplateService.updateBidDetailTemplate(bidDetailTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除内容模板")
    @DeleteMapping(value = "/{bidDetailTemplateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "bidDetailTemplateId", dataType = "Long", value = "内容模板ID", required = true),
    })
    public ResponseVO deleteBidDetailTemplateLogical(@PathVariable("bidDetailTemplateId")
                                                     @NotNull(message = "内容模板ID不能为空") Long bidDetailTemplateId) {

        log.info("deleteBidDetailTemplateLogical {}", bidDetailTemplateId);

        bidDetailTemplateService.deleteBidDetailTemplateLogical(bidDetailTemplateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询内容模板")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<BidDetailTemplateVO>> findBidDetailTemplate(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO) {

        log.info("findBidDetailTemplate {}", bidDetailTemplateFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(bidDetailTemplateFilterDTO);

        return ResponseVO.success(bidDetailTemplateService.queryBidDetailTemplate(bidDetailTemplateFilterDTO));
    }


}
