package com.xdcplus.workflow.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.workflow.common.pojo.entity.OddRule;
import com.xdcplus.workflow.common.pojo.query.OddRuleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单号规则 Mapper 接口
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface OddRuleMapper extends IBaseMapper<OddRule> {

    /**
     * 修改自增长基数
     * @param id   id
     * @param autoNumber 自动数量
     * @return 0: 失败，1：成功
     */
    int updateAutoNumber(@Param("id") Long id, @Param("autoNumber") Long autoNumber);


    /**
     * 查询前缀查询规则信息
     *
     * @param prefix 前缀
     * @return {@link OddRule} 规则信息
     */
    OddRule findOddRuleByPrefix(@Param("prefix") String prefix);


    /**
     * 查询规则信息
     *
     * @param query 查询条件
     * @return {@link List<OddRule>} 规则信息
     */
    List<OddRule> findOddRule(OddRuleQuery query);

    /**
     * 查询名称查询规则信息
     *
     * @param name 名称
     * @return {@link OddRule} 规则信息
     */
    OddRule findOddRuleByName(@Param("name") String name);











}
