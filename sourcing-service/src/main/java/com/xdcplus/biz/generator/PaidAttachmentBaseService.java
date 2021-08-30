package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.PaidAttachment;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidAttachmentVO;

import java.util.List;

/**
 * 竞价单附件(PaidAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:01:56
 */
public interface PaidAttachmentBaseService<S, T, E> extends BaseService<PaidAttachment, PaidAttachmentVO, PaidAttachment> {

    /**
     * 添加竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentDTO 竞价单附件(PaidAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidAttachment(PaidAttachmentDTO paidAttachmentDTO);

    /**
     * 修改竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentDTO 竞价单附件(PaidAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidAttachment(PaidAttachmentDTO paidAttachmentDTO);

    /**
     * 批量保存或更新竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentList 竞价单附件(PaidAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidAttachment> paidAttachmentList);

    /**
     * 批量保存或更新竞价单附件(PaidAttachmentDTOList)
     *
     * @param paidAttachmentDTOList 竞价单附件(PaidAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidAttachmentDTO> paidAttachmentDTOList);

    /**
     * 删除竞价单附件(PaidAttachment)
     *
     * @param id 竞价单附件(PaidAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidAttachmentLogical(Long id);

    /**
     * 查询竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidAttachmentVO>} 状态信息
     */
    List<PaidAttachmentVO> queryPaidAttachmentVOList(PaidAttachmentFilterDTO paidAttachmentFilterDTO);

    /**
     * 查询竞价单附件(PaidAttachment)
     *
     * @param paidAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidAttachmentVO>} 状态信息
     */
    PageVO<PaidAttachmentVO> queryPaidAttachment(PaidAttachmentFilterDTO paidAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价单附件(PaidAttachment)主键
     * @return {@link PaidAttachmentVO} 竞价单附件(PaidAttachment)信息
     */
    PaidAttachmentVO queryPaidAttachmentById(Long id);


}
