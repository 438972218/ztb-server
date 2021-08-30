package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidDetailTemplate;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidDetailTemplateVO;

import java.util.List;

/**
 * 内容模板(BidDetailTemplate)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:17
 */
public interface BidDetailTemplateBaseService<S, T, E> extends BaseService<BidDetailTemplate, BidDetailTemplateVO, BidDetailTemplate> {

    /**
     * 添加内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateDTO 内容模板(BidDetailTemplateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidDetailTemplate(BidDetailTemplateDTO bidDetailTemplateDTO);

    /**
     * 修改内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateDTO 内容模板(BidDetailTemplateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidDetailTemplate(BidDetailTemplateDTO bidDetailTemplateDTO);

    /**
     * 批量保存或更新内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateList 内容模板(BidDetailTemplateList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidDetailTemplate> bidDetailTemplateList);

    /**
     * 批量保存或更新内容模板(BidDetailTemplateDTOList)
     *
     * @param bidDetailTemplateDTOList 内容模板(BidDetailTemplateDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidDetailTemplateDTO> bidDetailTemplateDTOList);

    /**
     * 删除内容模板(BidDetailTemplate)
     *
     * @param id 内容模板(BidDetailTemplate)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidDetailTemplateLogical(Long id);

    /**
     * 查询内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidDetailTemplateVO>} 状态信息
     */
    List<BidDetailTemplateVO> queryBidDetailTemplateVOList(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO);

    /**
     * 查询内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidDetailTemplateVO>} 状态信息
     */
    PageVO<BidDetailTemplateVO> queryBidDetailTemplate(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO);

    /**
     * 查询一个
     *
     * @param id 内容模板(BidDetailTemplate)主键
     * @return {@link BidDetailTemplateVO} 内容模板(BidDetailTemplate)信息
     */
    BidDetailTemplateVO queryBidDetailTemplateById(Long id);


}
