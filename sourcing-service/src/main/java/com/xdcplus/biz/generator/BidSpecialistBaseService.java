package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistDTO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;

import java.util.List;

/**
 * 专家(BidSpecialist)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:22:58
 */
public interface BidSpecialistBaseService<S, T, E> extends BaseService<BidSpecialist, BidSpecialistVO, BidSpecialist> {

    /**
     * 添加专家(BidSpecialist)
     *
     * @param bidSpecialistDTO 专家(BidSpecialistDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveBidSpecialist(BidSpecialistDTO bidSpecialistDTO);

    /**
     * 修改专家(BidSpecialist)
     *
     * @param bidSpecialistDTO 专家(BidSpecialistDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateBidSpecialist(BidSpecialistDTO bidSpecialistDTO);

    /**
     * 批量保存或更新专家(BidSpecialist)
     *
     * @param bidSpecialistList 专家(BidSpecialistList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<BidSpecialist> bidSpecialistList);

    /**
     * 批量保存或更新专家(BidSpecialistDTOList)
     *
     * @param bidSpecialistDTOList 专家(BidSpecialistDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<BidSpecialistDTO> bidSpecialistDTOList);

    /**
     * 删除专家(BidSpecialist)
     *
     * @param id 专家(BidSpecialist)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteBidSpecialistLogical(Long id);

    /**
     * 查询专家(BidSpecialist)
     *
     * @param bidSpecialistFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSpecialistVO>} 状态信息
     */
    List<BidSpecialistVO> queryBidSpecialistVOList(BidSpecialistFilterDTO bidSpecialistFilterDTO);

    /**
     * 查询专家(BidSpecialist)
     *
     * @param bidSpecialistFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<BidSpecialistVO>} 状态信息
     */
    PageVO<BidSpecialistVO> queryBidSpecialist(BidSpecialistFilterDTO bidSpecialistFilterDTO);

    /**
     * 查询一个
     *
     * @param id 专家(BidSpecialist)主键
     * @return {@link BidSpecialistVO} 专家(BidSpecialist)信息
     */
    BidSpecialistVO queryBidSpecialistById(Long id);


}
