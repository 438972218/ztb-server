package com.xdcplus.workflow.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.workflow.common.pojo.entity.RequestTypeRelation;
import com.xdcplus.workflow.mapper.RequestTypeRelationMapper;
import com.xdcplus.workflow.service.RequestTypeRelationService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 表单-表单类型关系 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-05
 */
@Slf4j
@Service
public class RequestTypeRelationServiceImpl extends ServiceImpl<RequestTypeRelationMapper, RequestTypeRelation> implements RequestTypeRelationService {

    @Autowired
    private RequestTypeRelationMapper requestTypeRelationMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveRequestTypeRelation(Long requestId, Long requestTypeId) {

        RequestTypeRelation requestTypeRelation = new RequestTypeRelation(requestTypeId, requestId);
        return this.save(requestTypeRelation);
    }

    @Override
    public RequestTypeRelation findRequestTypeRelation(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return requestTypeRelationMapper.findRequestTypeRelationByRequestId(requestId);
    }

    @Override
    public Boolean validationExists(Long requestTypeId) {

        Assert.notNull(requestTypeId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<RequestTypeRelation> typeRelationList = requestTypeRelationMapper.findByRequestTypeRelationByTypeId(requestTypeId);

        return CollectionUtil.isNotEmpty(typeRelationList) ? Boolean.TRUE : Boolean.FALSE;
    }
}
