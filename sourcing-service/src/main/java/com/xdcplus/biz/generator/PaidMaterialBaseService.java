package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidMaterialVO;

import java.util.List;

/**
 * 竞价物品(PaidMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 14:11:11
 */
public interface PaidMaterialBaseService<S, T, E> extends BaseService<PaidMaterial, PaidMaterialVO, PaidMaterial> {

    /**
     * 添加竞价物品(PaidMaterial)
     *
     * @param paidMaterialDTO 竞价物品(PaidMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean savePaidMaterial(PaidMaterialDTO paidMaterialDTO);

    /**
     * 修改竞价物品(PaidMaterial)
     *
     * @param paidMaterialDTO 竞价物品(PaidMaterialDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidMaterial(PaidMaterialDTO paidMaterialDTO);

    /**
     * 批量保存或更新竞价物品(PaidMaterial)
     *
     * @param paidMaterialList 竞价物品(PaidMaterialList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<PaidMaterial> paidMaterialList);

    /**
     * 批量保存或更新竞价物品(PaidMaterialDTOList)
     *
     * @param paidMaterialDTOList 竞价物品(PaidMaterialDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<PaidMaterialDTO> paidMaterialDTOList);

    /**
     * 删除竞价物品(PaidMaterial)
     *
     * @param id 竞价物品(PaidMaterial)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deletePaidMaterialLogical(Long id);

    /**
     * 查询竞价物品(PaidMaterial)
     *
     * @param paidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidMaterialVO>} 状态信息
     */
    List<PaidMaterialVO> queryPaidMaterialVOList(PaidMaterialFilterDTO paidMaterialFilterDTO);

    /**
     * 查询竞价物品(PaidMaterial)
     *
     * @param paidMaterialFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<PaidMaterialVO>} 状态信息
     */
    PageVO<PaidMaterialVO> queryPaidMaterial(PaidMaterialFilterDTO paidMaterialFilterDTO);

    /**
     * 查询一个
     *
     * @param id 竞价物品(PaidMaterial)主键
     * @return {@link PaidMaterialVO} 竞价物品(PaidMaterial)信息
     */
    PaidMaterialVO queryPaidMaterialById(Long id);


}
