package com.xdcplus.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.workflow.common.pojo.entity.NotificationRelation;
import com.xdcplus.workflow.mapper.NotificationRelationMapper;
import com.xdcplus.workflow.service.NotificationRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通知关系服务实现
 *
 * @author Rong.Jia
 * @date 2021/08/12 10:46:00
 */
@Slf4j
@Service
public class NotificationRelationServiceImpl extends ServiceImpl<NotificationRelationMapper, NotificationRelation> implements NotificationRelationService {

    @Autowired
    private NotificationRelationMapper relationMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveNotificationRelation(Long requestId, Long flowId, Long historyId) {

        NotificationRelation notificationRelation = new NotificationRelation();
        notificationRelation.setRequestId(requestId);
        notificationRelation.setFlowId(flowId);
        notificationRelation.setHistoryId(historyId);

        this.save(notificationRelation);

        return notificationRelation.getId();
    }
}
