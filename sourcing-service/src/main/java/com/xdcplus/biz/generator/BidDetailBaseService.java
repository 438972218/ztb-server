package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidDetail;
import com.xdcplus.biz.common.pojo.dto.BidDetailDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidDetailVO;

import java.util.List;

/**
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:15
 */
public interface BidDetailBaseService<S, T, E> extends BaseService<BidDetail, BidDetailVO, BidDetail> {

    /**
     * 添加招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailDTO 招标单内容明细（报价须知、国内报价、国外报价）(BidDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidDetail(BidDetailDTO bidDetailDTO);

    /**
     * 修改招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailDTO 招标单内容明细（报价须知、国内报价、国外报价）(BidDetailDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidDetail(BidDetailDTO bidDetailDTO);

    /**
     * 批量保存或更新招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailList 招标单内容明细（报价须知、国内报价、国外报价）(BidDetailList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidDetail> bidDetailList);

    /**
     * 批量保存或更新招标单内容明细（报价须知、国内报价、国外报价）(BidDetailDTOList)
     *
     * @param bidDetailDTOList 招标单内容明细（报价须知、国内报价、国外报价）(BidDetailDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidDetailDTO> bidDetailDTOList);

    /**
     * 删除招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param id 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidDetailLogical(Long id);

    /**
     * 查询招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidDetailVO>} 状态信息
     */
    List<BidDetailVO> queryBidDetailVOList(BidDetailFilterDTO bidDetailFilterDTO);

    /**
     * 查询招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidDetailVO>} 状态信息
     */
    PageVO<BidDetailVO> queryBidDetail(BidDetailFilterDTO bidDetailFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)主键
     * @return {@link BidDetailVO} 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)信息
     */
    BidDetailVO queryBidDetailById(Long id);


}
