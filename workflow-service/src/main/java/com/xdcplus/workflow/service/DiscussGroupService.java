package com.xdcplus.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.entity.DiscussGroup;

import java.util.List;

/**
 * 讨论组服务类
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
public interface DiscussGroupService extends IService<DiscussGroup> {

    /**
     * 查询讨论组
     *
     * @param requestId 表单ID
     * @param currentUser 当前用户
     * @return {@link List<DiscussGroup>} 讨论组
     */
    List<DiscussGroup> findDiscussionGroup(Long requestId, String currentUser);

    /**
     * 保存讨论组
     *
     * @param requestId 表单id
     * @param subject   主题
     * @param currentUser 当前用户
     * @return {@link Long}
     */
    Long saveDiscussGroup(Long requestId, String subject, String currentUser);











}
