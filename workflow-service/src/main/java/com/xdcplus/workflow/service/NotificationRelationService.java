package com.xdcplus.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.entity.NotificationRelation;

/**
 * 通知服务的关系
 *
 * @author Rong.Jia
 * @date 2021/08/12 10:45:47
 */
public interface NotificationRelationService extends IService<NotificationRelation> {

    /**
     * 保存通知关系
     *
     * @param requestId 表单id
     * @param flowId    流转IDid
     * @param historyId 通知历史id
     * @return {@link Long} 主键ID
     */
    Long saveNotificationRelation(Long requestId, Long flowId, Long historyId);


}
