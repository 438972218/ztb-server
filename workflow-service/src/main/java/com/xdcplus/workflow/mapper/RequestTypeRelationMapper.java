package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.RequestTypeRelation;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表单-表单类型关系 Mapper 接口
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
public interface RequestTypeRelationMapper extends IBaseMapper<RequestTypeRelation> {

    /**
     * 查询表单类型关系
     *
     * @param requestId 表单id
     * @return {@link RequestTypeRelation} 表单类型关系
     */
    RequestTypeRelation findRequestTypeRelationByRequestId(@Param("requestId") Long requestId);

    /**
     * 查询通过表单类型关系通过类型id
     *
     * @param typeIdId id类型id
     * @return {@link List<RequestTypeRelation>} 表单类型关系
     */
    List<RequestTypeRelation> findByRequestTypeRelationByTypeId(@Param("typeId") Long typeIdId);




}
