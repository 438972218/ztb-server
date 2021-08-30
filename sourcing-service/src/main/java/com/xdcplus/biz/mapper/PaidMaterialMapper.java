package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.biz.common.pojo.query.PaidMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价物品(PaidMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-30 15:32:10
 */
public interface PaidMaterialMapper extends IBaseMapper<PaidMaterial> {

    /**
     * 查询竞价物品(PaidMaterial)
     *
     * @param paidMaterialQuery 竞价物品(PaidMaterial)查询
     * @return {@link List<PaidMaterial>}
     */
    List<PaidMaterial> queryPaidMaterial(PaidMaterialQuery paidMaterialQuery);

}
