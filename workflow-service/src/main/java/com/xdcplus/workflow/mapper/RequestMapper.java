package com.xdcplus.workflow.mapper;


import com.xdcplus.workflow.common.pojo.query.HandleMattersQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.workflow.common.pojo.entity.Request;
import com.xdcplus.workflow.common.pojo.query.RequestQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 流程表单 Mapper 接口
 * @author Rong.Jia
 * @since 2021-05-31
 */
public interface RequestMapper extends IBaseMapper<Request> {

    /**
     * 查询请求通过标题
     *
     * @param title 标题
     * @return {@link Request}
     */
    Request findRequestByTitle(@Param("title") String title);

    /**
     * 查询表单
     *
     * @param requestQuery 表单查询对象
     * @return {@link List<Request>}
     */
    List<Request> findRequest(RequestQuery requestQuery);

    /**
     * 查询表单通过主键集合
     *
     * @param ids 主键集合
     * @return {@link List<Request>}
     */
    List<Request> findRequestByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询表单通过规则主键
     *
     * @param ruleId 规则主键
     * @return {@link Request}
     */
    List<Request> findRequestByRuleId(@Param("ruleId") Long ruleId);

    /**
     * 查询表单通过流程主键
     *
     * @param processId 流程主键
     * @return {@link List<Request>}
     */
    List<Request> findRequestByProcessId(@Param("processId") Long processId);

    /**
     * 修改状态主键通过主键
     *
     * @param id       主键
     * @param statusId 状态主键
     * @return int 0: 失败，1：成功
     */
    int updateStatusIdById(@Param("id") Long id, @Param("statusId") Long statusId);

    /**
     * 修改流程主键和流程配置版本 通过表单主键
     *
     * @param requestId 表单主键
     * @param processId 流程主键
     * @param version   流程配置版本
     */
    void updateProcessIdAndVersionById(@Param("requestId") Long requestId,
                                       @Param("processId") Long processId, @Param("version") String version);

    /**
     * 根据创建人查询表单信息
     *
     * @param createdUser 创建用户
     * @return {@link List<Request>} 表单信息
     */
    List<Request> findRequestByCreatedUser(@Param("createdUser") String createdUser);

    /**
     * 表单处理事项查询
     *
     * @param handleMattersQuery 事项查询对象
     * @return {@link List<Request>} 表单信息
     */
    List<Request> handleMatters(HandleMattersQuery handleMattersQuery);

    /**
     * 根据状态ID标识 计算表单个数
     *
     * @param statusId 状态标识
     * @return {@link Integer} 表单个数
     */
    Integer countRequestByStatusId(@Param("statusId") Long statusId);

    /**
     * 查询表单通过父id
     *
     * @param parentId 父id
     * @return {@link List<Request>} 表单信息
     */
    List<Request> findRequestsByParentId(@Param("parentId") Long parentId);







}
