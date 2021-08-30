package com.xdcplus.vendor.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.vendor.common.pojo.entity.VendorUser;
import com.xdcplus.vendor.common.pojo.dto.VendorUserDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorUserFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;

import java.util.List;

/**
 * 供应商用户(VendorUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
public interface VendorUserBaseService<S, T, E> extends BaseService<VendorUser, VendorUserVO, VendorUser> {

    /**
     * 添加供应商用户(VendorUser)
     *
     * @param vendorUserDTO 供应商用户(VendorUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorUser(VendorUserDTO vendorUserDTO);

    /**
     * 修改供应商用户(VendorUser)
     *
     * @param vendorUserDTO 供应商用户(VendorUserDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorUser(VendorUserDTO vendorUserDTO);

    /**
     * 批量保存或更新供应商用户(VendorUser)
     *
     * @param vendorUserList 供应商用户(VendorUserList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorUser> vendorUserList);

    /**
     * 批量保存或更新供应商用户(VendorUserDTOList)
     *
     * @param vendorUserDTOList 供应商用户(VendorUserDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorUserDTO> vendorUserDTOList);

    /**
     * 删除供应商用户(VendorUser)
     *
     * @param id 供应商用户(VendorUser)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorUserLogical(Long id);

    /**
     * 查询供应商用户(VendorUser)
     *
     * @param vendorUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorUserVO>} 状态信息
     */
    List<VendorUserVO> queryVendorUserVOList(VendorUserFilterDTO vendorUserFilterDTO);

    /**
     * 查询供应商用户(VendorUser)
     *
     * @param vendorUserFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorUserVO>} 状态信息
     */
    PageVO<VendorUserVO> queryVendorUser(VendorUserFilterDTO vendorUserFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商用户(VendorUser)主键
     * @return {@link VendorUserVO} 供应商用户(VendorUser)信息
     */
    VendorUserVO queryVendorUserById(Long id);


}
