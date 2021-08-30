package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.biz.common.pojo.dto.PaidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.PaidSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidSheetVO;

import java.util.List;

/**
 * 竞价单(PaidSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 11:38:44
 */
public interface PaidSheetBaseService<S, T, E> extends BaseService<PaidSheet, PaidSheetVO, PaidSheet> {

    /**
     * 添加竞价单(PaidSheet)
     *
     * @param paidSheetDTO 竞价单(PaidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidSheet(PaidSheetDTO paidSheetDTO);

    /**
     * 修改竞价单(PaidSheet)
     *
     * @param paidSheetDTO 竞价单(PaidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidSheet(PaidSheetDTO paidSheetDTO);

    /**
     * 批量保存或更新竞价单(PaidSheet)
     *
     * @param paidSheetList 竞价单(PaidSheetList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidSheet> paidSheetList);

    /**
     * 批量保存或更新竞价单(PaidSheetDTOList)
     *
     * @param paidSheetDTOList 竞价单(PaidSheetDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidSheetDTO> paidSheetDTOList);

    /**
     * 删除竞价单(PaidSheet)
     *
     * @param id 竞价单(PaidSheet)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidSheetLogical(Long id);

    /**
     * 查询竞价单(PaidSheet)
     *
     * @param paidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidSheetVO>} 状态信息
     */
    List<PaidSheetVO> queryPaidSheetVOList(PaidSheetFilterDTO paidSheetFilterDTO);

    /**
     * 查询竞价单(PaidSheet)
     *
     * @param paidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidSheetVO>} 状态信息
     */
    PageVO<PaidSheetVO> queryPaidSheet(PaidSheetFilterDTO paidSheetFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价单(PaidSheet)主键
     * @return {@link PaidSheetVO} 竞价单(PaidSheet)信息
     */
    PaidSheetVO queryPaidSheetById(Long id);


}
