package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.entity.Request;
import com.xdcplus.workflow.common.pojo.entity.RequestRelation;
import com.xdcplus.workflow.common.pojo.query.HandleMattersQuery;
import com.xdcplus.workflow.common.pojo.query.RequestQuery;
import com.xdcplus.workflow.common.pojo.vo.FunctionalStrategyVO;
import com.xdcplus.workflow.common.pojo.vo.OddRuleVO;
import com.xdcplus.workflow.common.pojo.vo.RequestTypeVO;
import com.xdcplus.workflow.common.pojo.vo.RequestVO;
import com.xdcplus.workflow.common.utils.FunctionalStrategyValidationUtils;
import com.xdcplus.workflow.common.utils.VersionUtils;
import com.xdcplus.workflow.factory.matters.RequestHandleMattersParam;
import com.xdcplus.workflow.factory.matters.RequestHandleMattersProcessorFactory;
import com.xdcplus.workflow.factory.oddrule.OddAlgorithmFactory;
import com.xdcplus.workflow.mapper.RequestMapper;
import com.xdcplus.workflow.service.*;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 流程表单 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@Slf4j
@Service
public class RequestServiceImpl extends BaseServiceImpl<Request, RequestVO, Request, RequestMapper> implements RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private OddRuleService oddRuleService;

    @Autowired
    private OddAlgorithmFactory oddAlgorithmFactory;

    @Autowired
    private RequestRelationService requestRelationService;

    @Autowired
    private RequestFlowService requestFlowService;

    @Autowired
    private ProcessConfigService processConfigService;

    @Autowired
    private RequestTypeService requestTypeService;

    @Autowired
    private RequestTypeRelationService requestTypeRelationService;

    @Autowired
    private FunctionalStrategyService functionalStrategyService;

    @Autowired
    private RequestHandleMattersProcessorFactory requestHandleMattersProcessorFactory;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RequestVO saveRequest(RequestDTO requestDTO) {

        Long processId = getProcessId(requestDTO.getProcessId(), requestDTO.getStrategy());
        Assert.notNull(processId, ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        requestDTO.setProcessId(processId);

        OddRuleVO oddRuleVO = oddRuleService.findOne(requestDTO.getRuleId());
        Assert.notNull(oddRuleVO, ResponseEnum.THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        List<String> configVersions = processConfigService.findConfigVersionByProcessId(requestDTO.getProcessId());
        Assert.notEmpty(configVersions, ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND.getMessage());

        Request request = new Request();
        BeanUtil.copyProperties(requestDTO, request);
        request.setCreatedTime(DateUtil.current());
        request.setStatusId(NumberConstant.ONE.longValue());
        request.setConfigVersion(VersionUtils.maxVersion(configVersions));

        String oddNumber = oddAlgorithmFactory.algorithmProcessor(oddRuleVO.getId(),
                oddRuleVO.getAutoNumber(),
                oddRuleVO.getPrefix(), oddRuleVO.getAlgorithm());
        request.setOddNumber(oddNumber);

        this.save(request);

        Set<Long> parentIds = requestDTO.getParentIds();
        if (CollectionUtil.isNotEmpty(parentIds)) {
            for (Long parentId : parentIds) {
                requestRelationService.saveRequestRelation(request.getId(), parentId);
            }
        }

        // 保存表单-类型的关系
        saveRequestTypeRelation(request.getId(), requestDTO.getStrategy());

        // 开始流转
        requestFlowService.startTransfor(request.getId(), requestDTO.getCirculation());

        return this.objectConversion(request);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteRequest(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(requestId);

        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        return this.removeById(requestId);
    }

    @Override
    public Boolean existRequestByRuleId(Long ruleId) {

        if (ObjectUtil.isNotNull(ruleId)) {
            List<Request> requestList = requestMapper.findRequestByRuleId(ruleId);
            return CollectionUtil.isNotEmpty(requestList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean existRequestByProcessId(Long processId) {

        if (ObjectUtil.isNotNull(processId)) {
            List<Request> requestList = requestMapper.findRequestByProcessId(processId);
            return CollectionUtil.isNotEmpty(requestList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public RequestVO findOne(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(requestId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateStatusIdById(Long id, Long statusId) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(id);
        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestMapper.updateStatusIdById(id, statusId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateProcessIdAndVersionById(RequestConfigDTO requestConfigDTO) {

        Long processId = requestConfigDTO.getProcessId();
        Long requestId = requestConfigDTO.getRequestId();
        String version = requestConfigDTO.getVersion();

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(requestId);
        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Assert.isTrue(processConfigService.existConfigByProcessIdAndVersion(processId, version),
                ResponseEnum.THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestMapper.updateProcessIdAndVersionById(requestId, processId, version);
    }

    @Override
    public Boolean validationExists(String title) {

        Assert.notBlank(title, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());

        return ObjectUtil.isNotNull(requestMapper.findRequestByTitle(title))
                ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public PageVO<RequestVO> handleMatters(HandleMattersFilterDTO handleMattersFilterDTO) {

        PageVO<RequestVO> pageVO = new PageVO<>();

        RequestHandleMattersParam requestHandleMattersParam = new RequestHandleMattersParam();
        BeanUtil.copyProperties(handleMattersFilterDTO, requestHandleMattersParam);
        Set<Long> requestIds = requestHandleMattersProcessorFactory.handleMattersProcessor(requestHandleMattersParam);

        HandleMattersQuery handleMattersQuery = new HandleMattersQuery();
        BeanUtil.copyProperties(handleMattersFilterDTO, handleMattersQuery);
        handleMattersQuery.setRequestIds(requestIds);

        if (handleMattersFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(handleMattersFilterDTO);
        }

        List<Request> requestList = requestMapper.handleMatters(handleMattersQuery);
        PageInfo<Request> pageInfo = new PageInfo<>(requestList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(requestList));

        return pageVO;
    }

    @Override
    public Integer countRequestByStatusId(Long statusId) {

        Assert.notNull(statusId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return requestMapper.countRequestByStatusId(statusId);
    }

    @Override
    public List<RequestVO> findRequestByParentId(Long parentId) {

        Assert.notNull(parentId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<Request> requestList = requestMapper.findRequestsByParentId(parentId);

        return this.objectConversion(requestList);
    }

    @Override
    public PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO) {

        PageVO<RequestVO> pageVO = new PageVO<>();

        if (requestFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(requestFilterDTO);
        }

        RequestQuery requestQuery = BeanUtil.copyProperties(requestFilterDTO, RequestQuery.class);

        Set<Long> ids = CollectionUtil.newHashSet();
        if (Validator.isNotNull(requestFilterDTO.getParentId())) {
            List<RequestRelation> requestRelationList = requestRelationService.findRequestRelationByParentId(requestFilterDTO.getParentId());
            if (CollectionUtil.isNotEmpty(requestRelationList)) {
                ids = requestRelationList.stream()
                        .filter(a -> ObjectUtil.notEqual(NumberConstant.ZERO.longValue(), a.getRequestId()))
                        .map(RequestRelation::getRequestId)
                        .collect(Collectors.toSet());
            }

            if (CollectionUtil.isEmpty(ids) || ids.size() <= NumberConstant.ZERO) {
                ids.add(NumberConstant.ZERO.longValue());
            }
            requestQuery.setIds(ids);
        }

        List<Request> requestList = requestMapper.findRequest(requestQuery);

        PageInfo<Request> pageInfo = new PageInfo<>(requestList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(requestList));

        return pageVO;

    }

    @Override
    public RequestVO objectConversion(Request request) {
        return this.objectConversion(request, Boolean.TRUE);
    }

    @Override
    public RequestVO objectConversion(Request request, Boolean isReturnRelation) {

        RequestVO requestVO = super.objectConversion(request);
        if (ObjectUtil.isNotNull(requestVO) && isReturnRelation) {

            requestVO.setProcess(processService.findOne(request.getProcessId()));
            requestVO.setStatus(processStatusService.findOne(request.getStatusId()));

            List<RequestRelation> requestRelationList = requestRelationService.findRequestRelationByRequestId(request.getId());
            if (CollectionUtil.isNotEmpty(requestRelationList)) {
                Set<Long> ids = requestRelationList.stream()
                        .filter(a -> ObjectUtil.notEqual(NumberConstant.ZERO.longValue(), a.getRequestId()))
                        .map(RequestRelation::getParentId).collect(Collectors.toSet());

                List<Request> requestList = requestMapper.findRequestByIds(ids);
                requestVO.setParent(this.objectConversion(requestList, Boolean.FALSE));
            }

            Optional.ofNullable(requestTypeRelationService.findRequestTypeRelation(requestVO.getId()))
                    .ifPresent(a -> Optional.ofNullable(requestTypeService.findOne(a.getTypeId()))
                            .ifPresent(requestVO::setRequestType));
        }

        return requestVO;
    }

    @Override
    public List<RequestVO> objectConversion(List<Request> requests, Boolean isReturnRelation) {

        if (CollectionUtil.isNotEmpty(requests)) {
            return requests.stream()
                    .map(a -> this.objectConversion(a, isReturnRelation))
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 保存表单-类型的关系
     *
     * @param requestId 表单ID
     * @param strategy  策略条件
     */
    private void saveRequestTypeRelation(Long requestId, FormFlowStrategyDTO strategy) {

        if (ObjectUtil.isNull(strategy) || Validator.isNull(strategy.getTypeId())) {
            return;
        }

        RequestTypeVO requestTypeVO = requestTypeService.findOne(strategy.getTypeId());
        Assert.notNull(requestTypeVO, ResponseEnum.THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestTypeRelationService.saveRequestTypeRelation(requestId, requestTypeVO.getId());
    }

    /**
     * 获取流程Id
     *
     * @param processId 流程ID
     * @param strategy  策略
     * @return {@link Long}
     */
    private Long getProcessId(Long processId, FormFlowStrategyDTO strategy) {

        if (Validator.isNotNull(processId)) {
            return processId;
        }

        if (ObjectUtil.isNotNull(strategy)) {
            FunctionalStrategyValidationUtils.validationOfValidity(strategy);
            FunctionalStrategyVO functionalStrategyVO = functionalStrategyService.calculateFormPolicy(strategy.getTypeId(), strategy.getStrategyConditions());
            if (ObjectUtil.isNotNull(functionalStrategyVO)
                    && ObjectUtil.isNotNull(functionalStrategyVO.getProcess())) {
                processId = functionalStrategyVO.getProcess().getId();
            }
        }

        return processId;
    }


}