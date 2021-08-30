package com.xdcplus.workflow.mapper;


import com.xdcplus.workflow.common.pojo.entity.NotificationHistory;
import com.xdcplus.workflow.common.pojo.query.NotificationHistoryQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;

import java.util.List;

/**
 * 通知历史 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-11
 */
public interface NotificationHistoryMapper extends IBaseMapper<NotificationHistory> {

    /**
     * 查询通知历史
     *
     * @param query 查询
     * @return {@link List<NotificationHistory>}
     */
    List<NotificationHistory> findNotificationHistory(NotificationHistoryQuery query);


}
