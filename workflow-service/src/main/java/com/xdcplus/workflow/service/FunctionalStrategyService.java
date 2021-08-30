package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.RequestStrategyDTO;
import com.xdcplus.workflow.common.pojo.entity.FunctionalStrategy;
import com.xdcplus.workflow.common.pojo.vo.FunctionalStrategyVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 * 功能策略信息 服务类
 *
 * @author Rong.Jia
 * @since 2021-08-06
 */
public interface FunctionalStrategyService extends BaseService<FunctionalStrategy, FunctionalStrategyVO, FunctionalStrategy> {

    /**
     * 同步表单策略
     *
     * @param requestStrategyDTO 表单策略DTO
     * @return {@link Long} 主键ID
     */
    Long syncRequestStrategy(RequestStrategyDTO requestStrategyDTO);

    /**
     * 删除策略
     *
     * @param strategyId 策略id
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteStrategy(Long strategyId);

    /**
     * 查询策略
     *
     * @param pageDTO 分页DTO
     * @return {@link PageVO<FunctionalStrategyVO>} 策略信息
     */
    PageVO<FunctionalStrategyVO> findStrategy(PageDTO pageDTO);

    /**
     * 计算表单策略
     *
     * @param requestTypeId 表单类型ID
     * @param conditions    条件
     * @return {@link FunctionalStrategyVO} 策略
     */
    FunctionalStrategyVO calculateFormPolicy(Long requestTypeId, Object conditions);


















}
