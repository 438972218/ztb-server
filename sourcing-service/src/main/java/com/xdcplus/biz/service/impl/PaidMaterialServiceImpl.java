package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.biz.generator.impl.PaidMaterialBaseServiceImpl;
import com.xdcplus.biz.mapper.PaidMaterialMapper;
import com.xdcplus.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.biz.service.PaidMaterialService;
import com.xdcplus.biz.service.PaidSheetService;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 竞价物品(PaidMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-23 14:11:11
 */
@Slf4j
@Service("paidMaterialService")
public class PaidMaterialServiceImpl extends PaidMaterialBaseServiceImpl<PaidMaterial, PaidMaterialVO, PaidMaterial, PaidMaterialMapper> implements PaidMaterialService {

    @Autowired
    PaidSheetService paidSheetService;

    @Override
    public Boolean batchSavePaidMaterial(List<PaidMaterialDTO> paidMaterialDTOS) {
        PaidSheetVO paidSheetVO = paidSheetService.queryPaidSheetById(paidMaterialDTOS.get(NumberConstant.ZERO).getPaidSheetId());
        //截止时间
        Long endTime = paidSheetVO.getFirstEndTime();
        //位移时间
        Integer displacementTime =paidSheetVO.getDisplacementTime();
        for (PaidMaterialDTO paidMaterialDTO : paidMaterialDTOS) {
            paidMaterialDTO.setEndTime(endTime);

            endTime+=(displacementTime*60*1000);
        }

        return saveOrUpdateBatchByDTOList(paidMaterialDTOS);
    }

}
