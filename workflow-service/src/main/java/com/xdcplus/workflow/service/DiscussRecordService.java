package com.xdcplus.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.bo.DiscussRecordBO;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecord;
import com.xdcplus.workflow.common.pojo.vo.DiscussRecordVO;
import com.xdcplus.ztb.common.mp.service.BaseService;

import java.util.List;
import java.util.Set;

/**
 * 讨论记录 服务类
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussRecordService extends IService<DiscussRecord> {

    /**
     * 保存讨论记录
     *
     * @param groupId 讨论组ID
     * @param content 内容
     * @param toUsers 接收人
     * @param currentUser 当前用户
     * @return {@link Long}
     */
    Long saveDiscussRecord(Long groupId, String content, String currentUser, List<String> toUsers);

    /**
     * 查询讨论记录通过组id
     *
     * @param groupId 组id
     * @param currentUser 当前用户
     * @return {@link List<DiscussRecordBO>}
     */
    List<DiscussRecordBO> findDiscussRecordByGroupId(Long groupId, String currentUser);

    /**
     * 查询讨论记录通过组id
     *
     * @param groupIds 组id
     * @param currentUser 当前用户
     * @return {@link List<DiscussRecordBO>}
     */
    List<DiscussRecordBO> findDiscussRecordByGroupIds(Set<Long> groupIds, String currentUser);















}
