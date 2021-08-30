package com.xdcplus.vendor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.vendor.common.constants.ZtbConstant;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDTO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailFilterDTO;
import com.xdcplus.vendor.common.pojo.query.BidVendorDetailQuery;
import com.xdcplus.vendor.generator.impl.BidVendorDetailBaseServiceImpl;
import com.xdcplus.vendor.mapper.BidVendorDetailMapper;
import com.xdcplus.vendor.common.pojo.entity.BidVendorDetail;
import com.xdcplus.vendor.common.pojo.vo.BidVendorDetailVO;
import com.xdcplus.vendor.service.BidVendorDetailService;
import com.xdcplus.vendor.service.BidVendorService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-26 09:41:39
 */
@Slf4j
@Service("bidVendorDetailService")
public class BidVendorDetailServiceImpl extends BidVendorDetailBaseServiceImpl<BidVendorDetail, BidVendorDetailVO, BidVendorDetail, BidVendorDetailMapper> implements BidVendorDetailService {

    @Autowired
    BidVendorService bidVendorService;

    @Override
    public void updateBidVendorDetailByOperation(Long bidVendorId, String operation) {
        if (ZtbConstant.HAS_REPLY.equals(operation)) {
            List<BidVendorDetail> bidVendorDetails = selectBidVendorDetailByStatus(bidVendorId, ZtbConstant.SAVE);
            bidVendorDetails.forEach(bidVendorDetail -> bidVendorDetail.setStatus(ZtbConstant.SUBMIT));
            saveOrUpdateBatch(bidVendorDetails);
        } else if (ZtbConstant.HAS_REFUSED.equals(operation)) {
            List<BidVendorDetail> bidVendorDetails = selectBidVendorDetailByStatus(bidVendorId, ZtbConstant.SAVE);
            bidVendorDetails.forEach(bidVendorDetail -> bidVendorDetail.setStatus(ZtbConstant.HISTORY));
            saveOrUpdateBatch(bidVendorDetails);
        } else if (ZtbConstant.HAS_WITHDRAWN.equals(operation)) {
            List<BidVendorDetail> bidVendorDetails = selectBidVendorDetailByStatus(bidVendorId, ZtbConstant.SUBMIT);
            bidVendorDetails.forEach(bidVendorDetail -> bidVendorDetail.setStatus(ZtbConstant.SAVE));
            saveOrUpdateBatch(bidVendorDetails);
        }

        //修改供应商状态
        BidVendorDTO bidVendorDTO = new BidVendorDTO();
        bidVendorDTO.setId(bidVendorId);
        bidVendorDTO.setVendorStatus(operation);
        bidVendorService.updateBidVendor(bidVendorDTO);
    }

    @Override
    public void batchUpdateBidDetailDTO(List<BidVendorDetailDTO> bidVendorDetailDTOS) {
        saveOrUpdateBatchByDTOList(bidVendorDetailDTOS);
        double totalPrice = bidVendorDetailDTOS.stream()
                .filter(bidVendorDetailDTO -> bidVendorDetailDTO.getTotalPrice() != null)
                .mapToDouble(BidVendorDetailDTO::getTotalPrice).sum();
        long bidVendorId = bidVendorDetailDTOS.get(NumberConstant.ZERO).getBidVendorId();
        BidVendorDTO bidVendorDTO = new BidVendorDTO();
        bidVendorDTO.setId(bidVendorId);
        bidVendorDTO.setTotalPrice(totalPrice);
        bidVendorService.updateBidVendor(bidVendorDTO);
    }

    private List<BidVendorDetail> selectBidVendorDetailByStatus(long bidVendorId, String status) {
        BidVendorDetailQuery bidVendorDetailQuery = new BidVendorDetailQuery();
        bidVendorDetailQuery.setBidVendorId(bidVendorId);
        bidVendorDetailQuery.setStatus(status);
        List<BidVendorDetail> bidVendorDetails = bidVendorDetailMapper.queryBidVendorDetail(bidVendorDetailQuery);
        if (CollectionUtil.isEmpty(bidVendorDetails)) {
            log.error("updateBidVendorDetailByOperation() The BidVendorDetail select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_DETAIL_SELECT_FAIL);
        }

        return bidVendorDetails;
    }
}
