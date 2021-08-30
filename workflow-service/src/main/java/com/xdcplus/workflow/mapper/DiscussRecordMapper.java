package com.xdcplus.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 讨论记录 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussRecordMapper extends BaseMapper<DiscussRecord> {

    /**
     * 查询讨论记录通过组id
     *
     * @param groupId 组id
     * @return {@link List <DiscussRecord>}
     */
    List<DiscussRecord> findDiscussRecordByGroupId(@Param("groupId") Long groupId);

    /**
     * 查询讨论记录通过组id
     *
     * @param groupIds 组id
     * @return {@link List <DiscussRecord>}
     */
    List<DiscussRecord> findDiscussRecordByGroupIds(@Param("groupIds") Set<Long> groupIds);













}
