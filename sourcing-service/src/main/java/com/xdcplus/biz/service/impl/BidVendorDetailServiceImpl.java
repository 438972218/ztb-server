package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.biz.generator.impl.BidVendorDetailBaseServiceImpl;
import com.xdcplus.biz.mapper.BidVendorDetailMapper;
import com.xdcplus.biz.common.pojo.entity.BidVendorDetail;
import com.xdcplus.biz.common.pojo.vo.BidVendorDetailVO;
import com.xdcplus.biz.service.BidVendorDetailService;
import com.xdcplus.biz.service.BidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:42
 */
@Slf4j
@Service("bidVendorDetailService")
public class BidVendorDetailServiceImpl extends BidVendorDetailBaseServiceImpl<BidVendorDetail, BidVendorDetailVO, BidVendorDetail, BidVendorDetailMapper> implements BidVendorDetailService {

    @Autowired
    BidVendorService bidVendorService;

//    @Override
//    public void batchUpdateBidDetailDTO(BidVendorDTO bidVendorDTO) {
//        //修改供应商文件明细（状态为提交）
//        //修改供应商状态为已回复
//        List<BidVendorDetailDTO> bidVendorDetailDTOS = bidVendorDTO.getBidVendorDetailDTOS();
//        saveOrUpdateBatchByDTOList(bidVendorDetailDTOS);
//        bidVendorDTO.setVendorStatus(ZtbConstant.VENDOR_REPLIED);
//        bidVendorService.updateBidVendorStatus(bidVendorDTO);
//    }
}
