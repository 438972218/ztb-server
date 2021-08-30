package com.xdcplus.vendor.controller;


import cn.hutool.extra.validation.ValidationUtil;
import com.google.inject.internal.cglib.proxy.$NoOp;
import com.xdcplus.vendor.common.pojo.dto.OfferFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.OfferVO;
import com.xdcplus.vendor.service.OfferService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 出价记录 前端控制器
 *
 * @author Rong.Jia
 * @since 2021-08-17
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @ApiOperation("查询出价记录")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<OfferVO>> findOffers(OfferFilterDTO offerFilterDTO) {

        log.info("findOffers {}", offerFilterDTO.toString());

        ValidationUtil.validate(offerFilterDTO);

        PageVO<OfferVO> pageVO = offerService.findOffer(offerFilterDTO);

        return ResponseVO.success(pageVO);
    }















}
