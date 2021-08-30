package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.ProcessConfigTableDTO;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigTreeDTO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 流程配置控制服务
 *
 * @author Rong.Jia
 * @date 2021/07/29 17:07:40
 */
public interface ProcessConfigControlService {

    /**
     * 添加过程配置
     *
     * @param processConfigTreeDTO 过程配置v2 DTO
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProcessConfig(ProcessConfigTreeDTO processConfigTreeDTO);

    /**
     * 查询流程配置
     *
     * @param processId 流程主键
     * @param version   版本号
     * @return {@link List <ProcessConfigInfoVO> } 流程配置信息
     */
    List<ProcessConfigInfoVO> findProcessConfig(Long processId, @Nullable String version);

    /**
     * 查询过程配置信息
     *
     * @param processId 流程ID
     * @param version   版本
     * @return {@link List<ProcessConfigVO>} 过程配置信息
     */
    List<ProcessConfigVO> findProcessConfigTable(Long processId, String version);

    /**
     * 保存流程配置
     *
     * @param processConfigTables 流程配置
     * @return {@link Boolean} 是否添加成功
     */
    Boolean saveProcessConfig(List<ProcessConfigTableDTO> processConfigTables);


}
