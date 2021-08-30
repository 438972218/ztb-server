package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.ProcessConfigFilterDTO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.workflow.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfig;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 流程配置表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessConfigService extends BaseService<ProcessConfigBO, ProcessConfigVO, ProcessConfig> {

    /**
     * 存在配置通过流程ID
     *
     * @param processId 流程ID
     * @return {@link Boolean}
     */
    Boolean existConfigByProcessId(Long processId);

    /**
     * 查询配置通过表单主键ID
     *
     * @param requestId 请求主键
     * @return {@link List<ProcessConfigVO>}
     */
    List<ProcessConfigVO> findConfigByRequestId(Long requestId);

    /**
     * 查询流程配置
     *
     * @param processId    流程主键
     * @param fromStatusId 上一个状态主键
     * @param version 版本号
     * @return {@link List<ProcessConfigVO>}
     */
    List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId, Long fromStatusId, String version);

    /**
     * 根据流程ID和版本号判断是否有流程配置
     *
     * @param processId 流程ID
     * @param version  版本号
     * @return {@link Boolean} true: 存在，false : 不存在
     */
    Boolean existConfigByProcessIdAndVersion(Long processId, String version);

    /**
     * 根据流程ID获取历史配置版本号集合
     *
     * @param processId 流程ID
     * @return {@link List<String>}  历史配置版本号集合
     */
    List<String> findConfigVersionByProcessId(Long processId);

    /**
     * 查询流程配置
     *
     * @param processConfigFilterDTO 查询条件
     * @return {@link PageVO<ProcessConfigVO>} 流程配置
     */
    PageVO<ProcessConfigVO> findProcessConfig(ProcessConfigFilterDTO processConfigFilterDTO);

    /**
     * 查询配置通过流程ID
     *
     * @param processId 流程ID
     * @param version  版本
     * @return {@link List<ProcessConfigBO>} 流程配置信息
     */
    List<ProcessConfigBO> findConfigAssociatedByProcessId(Long processId, String version);

    /**
     * 保存流程配置
     *
     * @param processConfigList 流程配置列表
     */
    void saveProcessConfig(List<ProcessConfig> processConfigList);




















































}
