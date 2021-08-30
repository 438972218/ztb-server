package com.xdcplus.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecordRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 讨论记录关联信息Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussRecordRelationMapper extends BaseMapper<DiscussRecordRelation> {

    /**
     * 查询关系
     *
     * @param recordId 记录id
     * @return {@link List <DiscussRecordRelation>}
     */
    List<DiscussRecordRelation> findRelation(@Param("recordId") Long recordId);

}
