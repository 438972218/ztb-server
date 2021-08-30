package com.xdcplus.vendor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.vendor.common.constants.ZtbConstant;
import com.xdcplus.vendor.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.vendor.common.pojo.entity.BidVendor;
import com.xdcplus.vendor.common.pojo.query.BidSheetQuery;
import com.xdcplus.vendor.generator.impl.BidSheetBaseServiceImpl;
import com.xdcplus.vendor.mapper.BidSheetMapper;
import com.xdcplus.vendor.common.pojo.entity.BidSheet;
import com.xdcplus.vendor.common.pojo.vo.BidSheetVO;
import com.xdcplus.vendor.remote.service.IeService;
import com.xdcplus.vendor.service.BidSheetService;
import com.xdcplus.vendor.service.BidVendorService;
import com.xdcplus.vendor.service.VendorService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * bid招标单(BidSheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:37:40
 */
@Slf4j
@Service("bidSheetService")
public class BidSheetServiceImpl extends BidSheetBaseServiceImpl<BidSheet, BidSheetVO, BidSheet, BidSheetMapper> implements BidSheetService {

    @Autowired
    VendorService vendorService;

    @Autowired
    BidVendorService bidVendorService;

    @Autowired
    IeService ieService;

    @Override
    public PageVO<BidSheetVO> queryBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO) {
        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        //查询出表单信息
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setKeyword(bidSheetFilterDTO.getKeyword());
        requestFilterDTO.setStatusId(bidSheetFilterDTO.getStatusId());
        requestFilterDTO.setCurrentPage(-1);

        PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
        List<RequestVO> requestVOS = requestVOPageVO.getRecords();

        if (CollectionUtil.isNotEmpty(requestVOS)) {
            List<Long> requestIds = requestVOS.stream().map(RequestVO::getId).collect(Collectors.toList());
            bidSheetFilterDTO.setRequestIds(requestIds);
        }

        //查询出招标单信息
        List<BidSheet> bidSheetList = queryBidSheetListByVendor(bidSheetFilterDTO);

        PageInfo<BidSheet> pageInfo = new PageInfo<>(bidSheetList);
        List<BidSheetVO> bidSheetVOS = CollectionUtil.newArrayList();

        for (BidSheet bidSheet : bidSheetList) {
            BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);
            if (bidSheetVO.getRequestId() == null || bidSheetVO.getRequestId() == 0) {
                continue;
            }

            //供应商状态
            List<BidVendor> bidVendors = bidVendorService.list(new QueryWrapper<BidVendor>()
                    .lambda().eq(BidVendor::getVendorUserId, bidSheetFilterDTO.getVendorUserId())
                    .eq(BidVendor::getBidSheetId, bidSheet.getId()));

            if (CollUtil.isEmpty(bidVendors)) {
                log.error("queryBidSheetByVendor() bidVendors select faild");
                throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
            }
            BidVendor bidVendor = bidVendors.get(NumberConstant.ZERO);

            //表单状态（待回复、已回复、未回复）
            RequestVO requestVO = ieService.findRequestById(bidSheetVO.getRequestId());
            bidSheetVO.setOddNumber(requestVO.getOddNumber());
            bidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            bidSheetVO.setRequestTitle(requestVO.getTitle());
            if (StrUtil.equals(requestVO.getStatus().getName(), ZtbConstant.RUNNING)) {
                if (bidVendor.getVendorStatus() == null) {
                    bidSheetVO.setVendorStatus(ZtbConstant.TO_REPLY);
                } else {
                    bidSheetVO.setVendorStatus(bidVendor.getVendorStatus());
                }
            } else {
                if (bidVendor.getVendorStatus() == null) {
                    bidSheetVO.setVendorStatus(ZtbConstant.NO_REPLY);
                } else {
                    bidSheetVO.setVendorStatus(bidVendor.getVendorStatus());
                }
            }
            bidSheetVOS.add(bidSheetVO);
        }
        PropertyUtils.copyProperties(pageInfo, pageVO, bidSheetVOS);

        return pageVO;
    }

    @Override
    public PageVO<BidSheetVO> queryBidSheetByVendor1(BidSheetFilterDTO bidSheetFilterDTO) {

        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        //查询出表单信息
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setKeyword(bidSheetFilterDTO.getKeyword());
        //正在运行
        requestFilterDTO.setStatusId(ZtbConstant.RUNNING_STATUS_ID);
        requestFilterDTO.setCurrentPage(-1);

        PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
        List<RequestVO> requestVOS = requestVOPageVO.getRecords();

        if (CollectionUtil.isNotEmpty(requestVOS)) {
            List<Long> requestIds = requestVOS.stream().map(RequestVO::getId).collect(Collectors.toList());
            bidSheetFilterDTO.setRequestIds(requestIds);
        }

        //查询出招标单信息
        bidSheetFilterDTO.setAgainStatus(ZtbConstant.INVITED);
        List<BidSheet> bidSheetList = queryBidSheetListByVendor(bidSheetFilterDTO);

        PageInfo<BidSheet> pageInfo = new PageInfo<>(bidSheetList);
        List<BidSheetVO> bidSheetVOS = CollectionUtil.newArrayList();

        for (BidSheet bidSheet : bidSheetList) {
            BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);
            if (bidSheetVO.getRequestId() == null || bidSheetVO.getRequestId() == 0) {
                continue;
            }

            //供应商状态
            List<BidVendor> bidVendors = bidVendorService.list(new QueryWrapper<BidVendor>()
                    .lambda().eq(BidVendor::getVendorUserId, bidSheetFilterDTO.getVendorUserId())
                    .eq(BidVendor::getBidSheetId, bidSheet.getId()));

            if (CollUtil.isEmpty(bidVendors)) {
                log.error("queryBidSheetByVendor() bidVendors select faild");
                throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
            }
            BidVendor bidVendor = bidVendors.get(NumberConstant.ZERO);
            bidSheetVO.setVendorStatus(bidVendor.getVendorStatus());

            //表单状态（未回复，已回复，已拒绝，已撤回）
            RequestVO requestVO = ieService.findRequestById(bidSheetVO.getRequestId());
            bidSheetVO.setOddNumber(requestVO.getOddNumber());
            bidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            bidSheetVO.setRequestTitle(requestVO.getTitle());
            bidSheetVOS.add(bidSheetVO);
        }
        PropertyUtils.copyProperties(pageInfo, pageVO, bidSheetVOS);

        return pageVO;
    }

    protected List<BidSheet> queryBidSheetListByVendor(BidSheetFilterDTO bidSheetFilterDTO) {
        bidSheetFilterDTO.setDeleted(0);
        BidSheetQuery bidSheetQuery = BeanUtil.copyProperties(bidSheetFilterDTO, BidSheetQuery.class);

        return bidSheetMapper.queryBidSheetByVendor(bidSheetQuery);
    }

}
