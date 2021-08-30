package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeFilterDTO;
import com.xdcplus.workflow.common.pojo.entity.RequestType;
import com.xdcplus.workflow.common.pojo.query.RequestTypeQuery;
import com.xdcplus.workflow.common.pojo.vo.RequestTypeVO;
import com.xdcplus.workflow.mapper.RequestTypeMapper;
import com.xdcplus.workflow.service.RequestTypeRelationService;
import com.xdcplus.workflow.service.RequestTypeService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 表单类型服务实现类
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
@Slf4j
@Service
public class RequestTypeServiceImpl extends BaseServiceImpl<RequestType, RequestTypeVO, RequestType, RequestTypeMapper>
        implements RequestTypeService {

    @Autowired
    private RequestTypeMapper requestTypeMapper;

    @Autowired
    private RequestTypeRelationService requestTypeRelationService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveRequestType(RequestTypeDTO requestTypeDTO) {

        RequestType requestType = requestTypeMapper.findRequestTypeByType(requestTypeDTO.getRequestType());
        Assert.isNull(requestType, ResponseEnum.THE_FORM_TYPE_ALREADY_EXISTS.getMessage());
        requestType = new RequestType();
        BeanUtil.copyProperties(requestTypeDTO, requestType);
        requestType.setCreatedTime(DateUtil.current());

        this.save(requestType);

        return requestType.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateRequestType(RequestTypeDTO requestTypeDTO) {

        RequestType requestType = this.getById(requestTypeDTO.getId());
        Assert.notNull(requestType, ResponseEnum.THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        requestType.setUpdatedTime(DateUtil.current());
        requestType.setDescription(requestTypeDTO.getDescription());
        requestType.setRequestType(requestTypeDTO.getRequestType());

        this.updateById(requestType);

        return requestType.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteRequestType(Long typeId) {

        Assert.notNull(typeId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        RequestType requestType = this.getById(typeId);
        Assert.notNull(requestType, ResponseEnum.THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Assert.isFalse(requestTypeRelationService.validationExists(requestType.getId()),
                ResponseEnum.DATA_QUOTE.getMessage());

        return this.removeById(requestType.getId());
    }

    @Override
    public PageVO<RequestTypeVO> findRequestType(RequestTypeFilterDTO requestTypeFilterDTO) {

        PageVO<RequestTypeVO> pageVO = new PageVO<>();

        if (requestTypeFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(requestTypeFilterDTO);
        }

        RequestTypeQuery query = new RequestTypeQuery();
        BeanUtil.copyProperties(requestTypeFilterDTO, query);

        List<RequestType> requestTypeList = requestTypeMapper.findRequestTypes(query);
        PageInfo<RequestType> pageInfo = new PageInfo<>(requestTypeList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public Boolean validationExists(String typeName) {

        RequestType requestType = requestTypeMapper.findRequestTypeByType(typeName);

        return ObjectUtil.isNotNull(requestType) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public RequestTypeVO findOne(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(getById(id));
    }

}
