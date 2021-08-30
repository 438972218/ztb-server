package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidVendorDetail;
import com.xdcplus.biz.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorDetailFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidVendorDetailVO;

import java.util.List;

/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:41
 */
public interface BidVendorDetailBaseService<S, T, E> extends BaseService<BidVendorDetail, BidVendorDetailVO, BidVendorDetail> {

    /**
     * 添加供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailDTO 供应商内容明细（国内报价、国外报价）(BidVendorDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidVendorDetail(BidVendorDetailDTO bidVendorDetailDTO);

    /**
     * 修改供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailDTO 供应商内容明细（国内报价、国外报价）(BidVendorDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendorDetail(BidVendorDetailDTO bidVendorDetailDTO);

    /**
     * 批量保存或更新供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailList 供应商内容明细（国内报价、国外报价）(BidVendorDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidVendorDetail> bidVendorDetailList);

    /**
     * 批量保存或更新供应商内容明细（国内报价、国外报价）(BidVendorDetailDTOList)
     *
     * @param bidVendorDetailDTOList 供应商内容明细（国内报价、国外报价）(BidVendorDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidVendorDetailDTO> bidVendorDetailDTOList);

    /**
     * 删除供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param id 供应商内容明细（国内报价、国外报价）(BidVendorDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidVendorDetailLogical(Long id);

    /**
     * 查询供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorDetailVO>} 状态信息
     */
    List<BidVendorDetailVO> queryBidVendorDetailVOList(BidVendorDetailFilterDTO bidVendorDetailFilterDTO);

    /**
     * 查询供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorDetailVO>} 状态信息
     */
    PageVO<BidVendorDetailVO> queryBidVendorDetail(BidVendorDetailFilterDTO bidVendorDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商内容明细（国内报价、国外报价）(BidVendorDetail)主键
     * @return {@link BidVendorDetailVO} 供应商内容明细（国内报价、国外报价）(BidVendorDetail)信息
     */
    BidVendorDetailVO queryBidVendorDetailById(Long id);


}
