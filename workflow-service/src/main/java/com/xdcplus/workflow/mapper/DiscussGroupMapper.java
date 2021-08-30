package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.DiscussGroup;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  讨论组Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussGroupMapper extends IBaseMapper<DiscussGroup> {

    /**
     * 查询讨论组通过表单ID
     *
     * @param requestId 请求id
     * @param currentUser 当前用户
     * @return {@link List<DiscussGroup>} 讨论组
     */
    List<DiscussGroup> findDiscussGroupByRequestId(@Param("requestId") Long requestId,
                                                   @Param("currentUser") String currentUser);












}
