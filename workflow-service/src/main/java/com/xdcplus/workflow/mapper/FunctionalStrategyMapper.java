package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.FunctionalStrategy;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能策略信息 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-06
 */
public interface FunctionalStrategyMapper extends IBaseMapper<FunctionalStrategy> {

    /**
     * 查询功能策略
     *
     * @param requestTypeId 表单类型id
     * @param type 类型
     * @return {@link List<FunctionalStrategy>} 功能策略
     */
    List<FunctionalStrategy> findFunctionalStrategies(@Param("requestTypeId") Long requestTypeId,
                                                      @Param("type") Integer type);











}
