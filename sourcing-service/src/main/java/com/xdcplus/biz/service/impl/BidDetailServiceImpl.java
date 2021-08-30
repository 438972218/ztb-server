package com.xdcplus.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.biz.common.pojo.dto.BidDetailDTO;
import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.generator.impl.BidDetailBaseServiceImpl;
import com.xdcplus.biz.mapper.BidDetailMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetail;
import com.xdcplus.biz.common.pojo.vo.BidDetailVO;
import com.xdcplus.biz.service.BidDetailService;
import com.xdcplus.biz.service.BidSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:15
 */
@Slf4j
@Service("bidDetailService")
public class BidDetailServiceImpl extends BidDetailBaseServiceImpl<BidDetail, BidDetailVO, BidDetail, BidDetailMapper> implements BidDetailService {

    @Override
    public void batchUpdateBidDetailDTO(BidSheetDTO bidSheetDTO) {
        deleteByBidSheetId(bidSheetDTO.getId());
        List<BidDetailDTO> bidDetailDTOS = bidSheetDTO.getBidDetailDTOS();
        if(CollectionUtil.isNotEmpty(bidDetailDTOS)){
            saveOrUpdateBatchByDTOList(bidSheetDTO.getBidDetailDTOS());
        }
    }

    @Override
    public int deleteByBidSheetId(Long bidSheetId) {
        BidDetail bidDetail =new BidDetail();
        bidDetail.setDeleted(0);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("bid_sheet_Id",bidSheetId);

        return bidDetailMapper.update(bidDetail,updateWrapper);
    }
}
