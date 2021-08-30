package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidVendor;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidVendorVO;

import java.util.List;

/**
 * 招标投标方(BidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:24:03
 */
public interface BidVendorBaseService<S, T, E> extends BaseService<BidVendor, BidVendorVO, BidVendor> {

    /**
     * 添加招标投标方(BidVendor)
     *
     * @param bidVendorDTO 招标投标方(BidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidVendor(BidVendorDTO bidVendorDTO);

    /**
     * 修改招标投标方(BidVendor)
     *
     * @param bidVendorDTO 招标投标方(BidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidVendor(BidVendorDTO bidVendorDTO);

    /**
     * 批量保存或更新招标投标方(BidVendor)
     *
     * @param bidVendorList 招标投标方(BidVendorList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidVendor> bidVendorList);

    /**
     * 批量保存或更新招标投标方(BidVendorDTOList)
     *
     * @param bidVendorDTOList 招标投标方(BidVendorDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidVendorDTO> bidVendorDTOList);

    /**
     * 删除招标投标方(BidVendor)
     *
     * @param id 招标投标方(BidVendor)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidVendorLogical(Long id);

    /**
     * 查询招标投标方(BidVendor)
     *
     * @param bidVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorVO>} 状态信息
     */
    List<BidVendorVO> queryBidVendorVOList(BidVendorFilterDTO bidVendorFilterDTO);

    /**
     * 查询招标投标方(BidVendor)
     *
     * @param bidVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidVendorVO>} 状态信息
     */
    PageVO<BidVendorVO> queryBidVendor(BidVendorFilterDTO bidVendorFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标投标方(BidVendor)主键
     * @return {@link BidVendorVO} 招标投标方(BidVendor)信息
     */
    BidVendorVO queryBidVendorById(Long id);


}
