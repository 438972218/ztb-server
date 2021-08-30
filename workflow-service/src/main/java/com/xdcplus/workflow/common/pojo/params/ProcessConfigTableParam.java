package com.xdcplus.workflow.common.pojo.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 过程参数配置表
 *
 * @author Rong.Jia
 * @date 2021/08/04 17:38:16
 */
@Data
public class ProcessConfigTableParam implements Serializable {

    private static final long serialVersionUID = -1846495588465752127L;

    /**
     * 流程ID
     */
    private Long processId;

    /**
     *  上一个状态Id
     */
    private Long fromStatusId;

    /**
     *  下一个状态Id
     */
    private Long toStatusId;

    /**
     *  限定词ID
     */
    private Long qualifierId;

    /**
     *  超时自动流转
     */
    private Long timeoutAction;

    /**
     *  去向角色ID
     */
    private Long toRoleId;

    /**
     *  去向用户ID
     */
    private Long toUserId;

    /**
     *  去向用户类型
     */
    private Long userTo;

    /**
     * 节点类型-》开始: 0;结束: -1;一般: 1;
     * 会签节点:2;条件判断节点：3;
     * 查阅节点：4;子流程节点：5
     */
    private Integer type;


















}
