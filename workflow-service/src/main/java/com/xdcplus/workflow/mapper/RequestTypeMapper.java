package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.RequestType;
import com.xdcplus.workflow.common.pojo.query.RequestTypeQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  表单类型持久层接口
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
public interface RequestTypeMapper extends IBaseMapper<RequestType> {

    /**
     * 查询表单类型通过类型
     *
     * @param typeName 类型名称
     * @return {@link RequestType} 表单类型
     */
    RequestType findRequestTypeByType(@Param("typeName") String typeName);

    /**
     * 查询表单类型
     *
     * @param query 查询条件
     * @return {@link List<RequestType>} 表单类型信息
     */
    List<RequestType> findRequestTypes(RequestTypeQuery query);







}
