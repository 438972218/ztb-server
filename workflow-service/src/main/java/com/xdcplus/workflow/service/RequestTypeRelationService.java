package com.xdcplus.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.entity.RequestTypeRelation;

/**
 * 表单-表单类型关系 服务类
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
public interface RequestTypeRelationService extends IService<RequestTypeRelation> {

    /**
     * 保存表单类型的关系
     *
     * @param requestId       表单ID
     * @param requestTypeId 表单类型ID
     * @return {@link Boolean}
     */
    Boolean saveRequestTypeRelation(Long requestId, Long requestTypeId);

    /**
     * 查询表单类型关系
     *
     * @param requestId 表单id
     * @return {@link RequestTypeRelation} 表单类型关系
     */
    RequestTypeRelation findRequestTypeRelation(Long requestId);

    /**
     * 验证存在关联
     *
     * @param requestTypeId 表单类型id
     * @return {@link Boolean} 是否存在
     */
    Boolean validationExists(Long requestTypeId);












}
