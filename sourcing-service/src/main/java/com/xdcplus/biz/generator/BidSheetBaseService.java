package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;

import java.util.List;

/**
 * bid招标单(BidSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:24:02
 */
public interface BidSheetBaseService<S, T, E> extends BaseService<BidSheet, BidSheetVO, BidSheet> {

    /**
     * 添加bid招标单(BidSheet)
     *
     * @param bidSheetDTO bid招标单(BidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidSheet(BidSheetDTO bidSheetDTO);

    /**
     * 修改bid招标单(BidSheet)
     *
     * @param bidSheetDTO bid招标单(BidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidSheet(BidSheetDTO bidSheetDTO);

    /**
     * 批量保存或更新bid招标单(BidSheet)
     *
     * @param bidSheetList bid招标单(BidSheetList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidSheet> bidSheetList);

    /**
     * 批量保存或更新bid招标单(BidSheetDTOList)
     *
     * @param bidSheetDTOList bid招标单(BidSheetDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidSheetDTO> bidSheetDTOList);

    /**
     * 删除bid招标单(BidSheet)
     *
     * @param id bid招标单(BidSheet)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidSheetLogical(Long id);

    /**
     * 查询bid招标单(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSheetVO>} 状态信息
     */
    List<BidSheetVO> queryBidSheetVOList(BidSheetFilterDTO bidSheetFilterDTO);

    /**
     * 查询bid招标单(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSheetVO>} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheet(BidSheetFilterDTO bidSheetFilterDTO);

    /**
     * 查询一个
     *
     * @param id bid招标单(BidSheet)主键
     * @return {@link BidSheetVO} bid招标单(BidSheet)信息
     */
    BidSheetVO queryBidSheetById(Long id);


}
