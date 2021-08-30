package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.PaidVendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidVendorVO;

import java.util.List;

/**
 * 竞价供应商(PaidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:05
 */
public interface PaidVendorBaseService<S, T, E> extends BaseService<PaidVendor, PaidVendorVO, PaidVendor> {

    /**
     * 添加竞价供应商(PaidVendor)
     *
     * @param paidVendorDTO 竞价供应商(PaidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidVendor(PaidVendorDTO paidVendorDTO);

    /**
     * 修改竞价供应商(PaidVendor)
     *
     * @param paidVendorDTO 竞价供应商(PaidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendor(PaidVendorDTO paidVendorDTO);

    /**
     * 批量保存或更新竞价供应商(PaidVendor)
     *
     * @param paidVendorList 竞价供应商(PaidVendorList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidVendor> paidVendorList);

    /**
     * 批量保存或更新竞价供应商(PaidVendorDTOList)
     *
     * @param paidVendorDTOList 竞价供应商(PaidVendorDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidVendorDTO> paidVendorDTOList);

    /**
     * 删除竞价供应商(PaidVendor)
     *
     * @param id 竞价供应商(PaidVendor)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidVendorLogical(Long id);

    /**
     * 查询竞价供应商(PaidVendor)
     *
     * @param paidVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorVO>} 状态信息
     */
    List<PaidVendorVO> queryPaidVendorVOList(PaidVendorFilterDTO paidVendorFilterDTO);

    /**
     * 查询竞价供应商(PaidVendor)
     *
     * @param paidVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidVendorVO>} 状态信息
     */
    PageVO<PaidVendorVO> queryPaidVendor(PaidVendorFilterDTO paidVendorFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价供应商(PaidVendor)主键
     * @return {@link PaidVendorVO} 竞价供应商(PaidVendor)信息
     */
    PaidVendorVO queryPaidVendorById(Long id);


}
