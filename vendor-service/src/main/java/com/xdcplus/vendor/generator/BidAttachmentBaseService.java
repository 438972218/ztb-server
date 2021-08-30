package com.xdcplus.vendor.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.entity.BidAttachment;
import com.xdcplus.vendor.common.pojo.dto.BidAttachmentDTO;
import com.xdcplus.vendor.common.pojo.dto.BidAttachmentFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.BidAttachmentVO;

import java.util.List;

/**
 * 招标附件(BidAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:03
 */
public interface BidAttachmentBaseService<S, T, E> extends BaseService<BidAttachment, BidAttachmentVO, BidAttachment> {

    /**
     * 添加招标附件(BidAttachment)
     *
     * @param bidAttachmentDTO 招标附件(BidAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidAttachment(BidAttachmentDTO bidAttachmentDTO);

    /**
     * 修改招标附件(BidAttachment)
     *
     * @param bidAttachmentDTO 招标附件(BidAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidAttachment(BidAttachmentDTO bidAttachmentDTO);

    /**
     * 批量保存或更新招标附件(BidAttachment)
     *
     * @param bidAttachmentList 招标附件(BidAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidAttachment> bidAttachmentList);

    /**
     * 批量保存或更新招标附件(BidAttachmentDTOList)
     *
     * @param bidAttachmentDTOList 招标附件(BidAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidAttachmentDTO> bidAttachmentDTOList);

    /**
     * 删除招标附件(BidAttachment)
     *
     * @param id 招标附件(BidAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidAttachmentLogical(Long id);

    /**
     * 查询招标附件(BidAttachment)
     *
     * @param bidAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidAttachmentVO>} 状态信息
     */
    List<BidAttachmentVO> queryBidAttachmentVOList(BidAttachmentFilterDTO bidAttachmentFilterDTO);

    /**
     * 查询招标附件(BidAttachment)
     *
     * @param bidAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidAttachmentVO>} 状态信息
     */
    PageVO<BidAttachmentVO> queryBidAttachment(BidAttachmentFilterDTO bidAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 招标附件(BidAttachment)主键
     * @return {@link BidAttachmentVO} 招标附件(BidAttachment)信息
     */
    BidAttachmentVO queryBidAttachmentById(Long id);


}
