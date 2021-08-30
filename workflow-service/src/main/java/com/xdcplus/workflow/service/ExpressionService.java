package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.ExpressionDTO;
import com.xdcplus.workflow.common.pojo.entity.Expression;
import com.xdcplus.workflow.common.pojo.vo.ExpressionVO;
import com.xdcplus.ztb.common.mp.service.BaseService;

import java.util.List;

/**
 * 表达式标识表 服务类
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ExpressionService extends BaseService<Expression, ExpressionVO, Expression> {

    /**
     * 添加表达式
     *
     * @param expressionDTO 表达dto
     * @return {@link Boolean}
     */
    Boolean saveExpression(ExpressionDTO expressionDTO);

    /**
     *  修改表达式
     *
     * @param expressionDTO 表达dto
     * @return {@link Boolean}
     */
    Boolean updateExpression(ExpressionDTO expressionDTO);

    /**
     * 删除表达式
     *
     * @param id 主键
     * @return {@link Boolean}
     */
    Boolean deleteExpression(Long id);

    /**
     * 查询表达式
     *
     * @return {@link List<ExpressionVO>} 表达式信息
     */
    List<ExpressionVO> findExpression();

    /**
     * 查询表达式
     *
     * @param id 主键
     * @return {@link ExpressionVO} 表达式
     */
    ExpressionVO findOne(Long id);















}
