package com.xdcplus.workflow.service;


import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigNodeDTO;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfigNode;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigNodeVO;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 流程配置节点信息 服务类
 * @author Rong.Jia
 * @date  2021-06-17
 */
public interface ProcessConfigNodeService extends BaseService<ProcessConfigNode, ProcessConfigNodeVO, ProcessConfigNode> {

    /**
     * 添加配置节点
     *
     * @param processId         流程主键
     * @param processConfigNodeDTO 过程配置节点
     * @param version           版本
     * @return {@link Boolean}
     */
    Boolean saveConfigNode(Long processId, ProcessConfigNodeDTO processConfigNodeDTO, String version);

    /**
     * 修改配置节点
     *
     * @param processId         流程主键
     * @param processConfigNodeDTO 过程配置节点
     * @param version           版本
     * @return {@link Boolean}
     */
    Boolean updateConfigNode(Long processId, ProcessConfigNodeDTO processConfigNodeDTO, String version);

    /**
     * 删除配置节点
     *
     * @param processId 流程主键
     * @param version   版本
     * @return {@link Boolean}
     */
    Boolean deleteConfigNode(Long processId, String version);

    /**
     * 查询配置节点
     *
     * @param processId 流程主键
     * @param version   版本
     * @return {@link List<ProcessConfigNode>}
     */
    List<ProcessConfigNode> findConfigNode(Long processId, @Nullable String version);

    /**
     * 获取配置节点
     *
     * @param processId 流程ID
     * @param mark      标识
     * @param version   版本
     * @return {@link ProcessConfigNode} 配置节点信息
     */
    ProcessConfigNode findConfigNode(Long processId, String mark, String version);





}
