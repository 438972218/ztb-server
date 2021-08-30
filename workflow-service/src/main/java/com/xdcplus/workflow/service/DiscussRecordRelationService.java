package com.xdcplus.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecordRelation;

import java.util.List;

/**
 * 讨论记录关联信息服务类
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussRecordRelationService extends IService<DiscussRecordRelation> {

    /**
     * 保存联系
     *
     * @param recordId 记录id
     * @param toUsers   接收用户
     */
    void saveRelation(Long recordId, List<String> toUsers);

    /**
     * 查询关系
     *
     * @param recordId 记录id
     * @return {@link List<DiscussRecordRelation>}
     */
    List<DiscussRecordRelation> findRelation(Long recordId);












}
