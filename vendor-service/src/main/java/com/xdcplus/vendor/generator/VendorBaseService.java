package com.xdcplus.vendor.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.entity.Vendor;
import com.xdcplus.vendor.common.pojo.dto.VendorDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.VendorVO;

import java.util.List;

/**
 * 供应商(Vendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:52
 */
public interface VendorBaseService<S, T, E> extends BaseService<Vendor, VendorVO, Vendor> {

    /**
     * 添加供应商(Vendor)
     *
     * @param vendorDTO 供应商(VendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendor(VendorDTO vendorDTO);

    /**
     * 修改供应商(Vendor)
     *
     * @param vendorDTO 供应商(VendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendor(VendorDTO vendorDTO);

    /**
     * 批量保存或更新供应商(Vendor)
     *
     * @param vendorList 供应商(VendorList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<Vendor> vendorList);

    /**
     * 批量保存或更新供应商(VendorDTOList)
     *
     * @param vendorDTOList 供应商(VendorDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorDTO> vendorDTOList);

    /**
     * 删除供应商(Vendor)
     *
     * @param id 供应商(Vendor)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorLogical(Long id);

    /**
     * 查询供应商(Vendor)
     *
     * @param vendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorVO>} 状态信息
     */
    List<VendorVO> queryVendorVOList(VendorFilterDTO vendorFilterDTO);

    /**
     * 查询供应商(Vendor)
     *
     * @param vendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorVO>} 状态信息
     */
    PageVO<VendorVO> queryVendor(VendorFilterDTO vendorFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商(Vendor)主键
     * @return {@link VendorVO} 供应商(Vendor)信息
     */
    VendorVO queryVendorById(Long id);


}
