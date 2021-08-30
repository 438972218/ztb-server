package com.xdcplus.workflow.factory.flow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.workflow.common.pojo.vo.RequestFlowVO;
import com.xdcplus.workflow.common.pojo.vo.RequestVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 同意 流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class AgreeProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.ONE, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Processing consent operation");

        AgreeRequest agreeRequest = new AgreeRequest();
        BeanUtil.copyProperties(processTransforParam, agreeRequest);

        AgreeHandlerFactory.handler(agreeRequest);
    }

    /**
     * 处理器的优先级注解
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Inherited
    @Documented
    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    private @interface AgreeHandlerAnnotation {

        /**
         * 顺序
         *
         * @return int
         */
        int offset() default 0;

    }

    /**
     * 同意处理工厂类
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    private static class AgreeHandlerFactory {

        /**
         * 责任链中的处理器优先级排序集合
         */
        private static List<Integer> offsets = new ArrayList<>();

        /**
         * 责任链的处理类集合
         */
        private static final Map<Integer, BaseAgreeProcessHandler> handlerList = new LinkedHashMap<>();


        /**
         * 处理器执行类（总是从链首开始执行）
         * @param request 同意参数
         */
        private static void handler(AgreeRequest request) {

            if (CollectionUtil.isEmpty(offsets)) {
                Map<String, BaseAgreeProcessHandler> agreeProcessHandlerMap = SpringUtil.getBeansOfType(BaseAgreeProcessHandler.class);
                if (MapUtil.isNotEmpty(agreeProcessHandlerMap)) {
                    ArrayList<BaseAgreeProcessHandler> agreeProcessHandlerList = CollectionUtil.newArrayList(agreeProcessHandlerMap.values());

                    // 获取处理器优先级集合
                    offsets = agreeProcessHandlerList.stream().map(t -> t.getClass()
                            .getAnnotation(AgreeHandlerAnnotation.class).offset()).sorted()
                            .collect(Collectors.toList());

                    // 通过反射机制来生成处理器实例
                    agreeProcessHandlerList.forEach(handler -> {
                        AgreeHandlerAnnotation annotation = handler.getClass().getAnnotation(AgreeHandlerAnnotation.class);
                        handlerList.put(annotation.offset(), handler);
                    });

                    // 指定处理器的下一个处理器实例
                    handlerList.forEach( (k, v) -> {
                        int size = offsets.size() - 1;
                        int index = offsets.indexOf(k);
                        if (size > index) {
                            v.setNextHandler(handlerList.get(offsets.get(index + 1)));
                        }
                    });
                }
            }

            handlerList.get(offsets.get(NumberConstant.ZERO)).handler(request);
        }
    }

    /**
     * 同意过程处理程序
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    private static abstract class BaseAgreeProcessHandler {

        /**
         * 设置下一个处理程序
         *
         * @param handler 处理程序
         */
        public abstract void setNextHandler(BaseAgreeProcessHandler handler);

        /**
         * 处理程序
         *
         * @param request 请求
         */
        public abstract void handler(AgreeRequest request);

    }

    /**
     * 同意操作-请求
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Data
    private static class AgreeRequest implements Serializable {

        private static final long serialVersionUID = 8510762105826701104L;

        /**
         * 表单
         */
        private RequestVO request;

        /**
         *  流程操作 ，详见流程操作信息
         */
        private Integer flowOption;

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 描述
         */
        private String description;

        /**
         *  同意操作
         */
        private ProcessTransforParam.Agree agree;

        /**
         * 当前自己流程是否是加签
         *  当前流程配置=null：为true, 当前流程配置 !=null：为false,
         */
        private Boolean extraNode;

        /**
         * 当前操作流转节点
         */
        private RequestFlowVO currentFlow;

        /**
         * 当前流转去向流程配置
         */
        private List<ProcessConfigVO> currentConfigList;

        /**
         *  下一个流转节点信息
         */
        private List<ProcessConfigVO> nextConfigList;

        public Boolean getExtraNode() {
            return CollectionUtil.isEmpty(currentConfigList);
        }
    }

    /**
     * 获取去向流程处理器
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Component
    @AgreeHandlerAnnotation(offset = 1)
    private class GetDestinationProcessHandler extends BaseAgreeProcessHandler {

        /**
         * 处理程序
         */
        private BaseAgreeProcessHandler handler;

        @Override
        public void setNextHandler(BaseAgreeProcessHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handler(AgreeRequest request) {

            RequestVO requestVO = request.getRequest();
            Long userId = request.getUserId();
            List<Long> roleIds = request.getAgree().getRoleIds();

            //  当前操作流转节点
            RequestFlowVO currentFlow = getCurrentRequestFlow(requestVO.getId(),
                    requestVO.getStatus().getId(), userId, roleIds);

            // 当前流转去向流程配置
            List<ProcessConfigVO> currentConfigList = findConfigByProcessIdAndFromStatusId(requestVO.getProcess().getId(),
                    currentFlow.getToStatus().getId(), requestVO.getConfigVersion(), userId, requestVO.getCreatedUser());

            request.setCurrentConfigList(currentConfigList);
            request.setCurrentFlow(currentFlow);

            doHandler(handler, request);
        }
    }

    /**
     * 完结自己处理程序
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Component
    @AgreeHandlerAnnotation(offset = 2)
    private class HisEndHandler extends BaseAgreeProcessHandler {

        /**
         * 处理程序
         */
        private BaseAgreeProcessHandler handler;

        @Override
        public void setNextHandler(BaseAgreeProcessHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handler(AgreeRequest request) {

            // 修改当前节点信息
            SyncFlow syncFlow = SyncFlow.builder()
                    .requestId(request.getRequest().getId())
                    .flowId(request.getCurrentFlow().getId())
                    .flowOptionValue(request.getFlowOption())
                    .description(request.getDescription())
                    .endTime(DateUtil.current())
                    .toUserId(request.getUserId())
                    .build();

            syncFlow(syncFlow);

            doHandler(handler, request);

        }
    }

    /**
     * 过滤去向流程处理器
     *
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Component
    @AgreeHandlerAnnotation(offset = 3)
    private class FilterDirectionFlowHandler extends BaseAgreeProcessHandler {

        /**
         * 处理程序
         */
        private BaseAgreeProcessHandler handler;

        @Override
        public void setNextHandler(BaseAgreeProcessHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handler(AgreeRequest request) {

            Boolean extraNode = request.getExtraNode();
            List<ProcessConfigVO> currentConfigList = request.getCurrentConfigList();

            // 下一个配置信息集合
            List<ProcessConfigVO> nextConfigList = CollectionUtil.newArrayList();

            // 不是加签
            if (!extraNode) {

                Optional.ofNullable(getsConfigNonAddedDestination(currentConfigList, request.getAgree().getFlowConditions()))
                        .ifPresent(nextConfigList::addAll);

            }else {

                //是加签

                // 历史流转记录
                List<RequestFlowVO> historicalCirculationList = requestFlowService.findRequestFlowRequestId(request.getRequest().getId());

                RequestFlowVO currentFlow = request.getCurrentFlow();

                // 是前加签
                if (equal(NumberConstant.SIX, currentFlow.getToStatus().getMark())) {

                    RequestFlowVO recentUnsignedFlow = getRecentUnsignedFlow(historicalCirculationList,
                            currentFlow.getId(), NumberConstant.SIX);

                    ProcessConfigVO processConfigVO = new ProcessConfigVO();
                    processConfigVO.setFromStatus(currentFlow.getToStatus());
                    processConfigVO.setToRoleId(recentUnsignedFlow.getToRoleId());
                    processConfigVO.setToUserId(recentUnsignedFlow.getToUserId());
                    processConfigVO.setVersion(request.getRequest().getConfigVersion());
                    processConfigVO.setToStatus(recentUnsignedFlow.getToStatus());
                    processConfigVO.setProcess(recentUnsignedFlow.getProcess());

                    nextConfigList.add(processConfigVO);

                }else {

                    // 后加签

                    RequestFlowVO recentUnsignedFlow = getRecentUnsignedFlow(historicalCirculationList,
                            currentFlow.getId(), NumberConstant.FIVE);

                    nextConfigList = findConfigByProcessIdAndFromStatusId(currentFlow.getProcess().getId(),
                            recentUnsignedFlow.getToStatus().getId(), recentUnsignedFlow.getConfigVersion(),
                            request.getUserId(), request.getCurrentFlow().getCreatedUser());

                    if (CollectionUtil.isNotEmpty(nextConfigList)) {
                        nextConfigList.forEach(a -> a.setFromStatus(currentFlow.getToStatus()));
                    }

                }
            }

            request.setNextConfigList(nextConfigList);

            doHandler(handler, request);
        }
    }

    /**
     *
     *新增去向流程处理器
     * @author Rong.Jia
     * @date 2021/07/15
     */
    @Component
    @AgreeHandlerAnnotation(offset = 4)
    private class NewDestinationProcessHandler extends BaseAgreeProcessHandler {

        /**
         * 处理程序
         */
        private BaseAgreeProcessHandler handler;

        @Override
        public void setNextHandler(BaseAgreeProcessHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handler(AgreeRequest request) {

            if (!requestFlowService.existRequestFlowByFlowOptionAndRequestId(NumberConstant.A_NEGATIVE,
                    request.getRequest().getId())) {

                List<ProcessConfigVO> nextConfigList = request.getNextConfigList();
                if (CollectionUtil.isNotEmpty(nextConfigList)) {
                    for (ProcessConfigVO nextConfig : nextConfigList) {

                        Integer flowOptionValue = NumberConstant.A_NEGATIVE;
                        Long endTime  = null;

                        if (equal(NumberConstant.TWO, nextConfig.getToStatus().getMark())
                                || equal(NumberConstant.FOUR, nextConfig.getToStatus().getMark()) ) {
                            flowOptionValue = NumberConstant.ONE;
                            endTime = DateUtil.current();
                        }

                        SyncFlow syncFlow = SyncFlow.builder()
                                .requestId(request.getRequest().getId())
                                .flowOptionValue(flowOptionValue)
                                .fromStatusId(nextConfig.getFromStatus().getId())
                                .toStatusId(nextConfig.getToStatus().getId())
                                .toRoleId(nextConfig.getToRoleId())
                                .configVersion(nextConfig.getVersion())
                                .toUserId(nextConfig.getToUserId())
                                .fromUserId(request.getUserId())
                                .configVersion(nextConfig.getVersion())
                                .endTime(endTime)
                                .build();

                        syncFlow(syncFlow);

                        updateRequestStatusIdById(request.getRequest().getId(), nextConfig.getToStatus().getId());

                    }
                }
            }

            doHandler(handler, request);
        }
    }

    /**
     * 执行处理程序
     *
     * @param handler 处理程序
     * @param request 处理参数
     */
    private void doHandler(BaseAgreeProcessHandler handler, AgreeRequest request) {
        if (ObjectUtil.isNotNull(handler)) {
            handler.handler(request);
        }
    }






}
