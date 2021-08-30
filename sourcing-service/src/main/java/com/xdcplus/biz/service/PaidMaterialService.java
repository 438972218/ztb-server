package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.biz.generator.PaidMaterialBaseService;
import com.xdcplus.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.biz.common.pojo.vo.PaidMaterialVO;

import java.util.List;


/**
 * 竞价物品(PaidMaterial)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 14:11:11
 */
public interface PaidMaterialService extends PaidMaterialBaseService<PaidMaterial, PaidMaterialVO, PaidMaterial> {

    /**
     * 批量保存支付材料
     *
     * @param paidMaterialDTOS 支付材料dto
     * @return {@link Boolean}
     */
    Boolean batchSavePaidMaterial(List<PaidMaterialDTO> paidMaterialDTOS);

}
