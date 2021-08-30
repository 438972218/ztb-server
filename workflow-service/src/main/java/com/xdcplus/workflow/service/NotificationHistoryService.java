package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.NotificationHistoryDTO;
import com.xdcplus.workflow.common.pojo.dto.NotificationHistoryFilterDTO;
import com.xdcplus.workflow.common.pojo.entity.NotificationHistory;
import com.xdcplus.workflow.common.pojo.vo.NotificationHistoryVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 * 通知历史 服务类
 *
 * @author Rong.Jia
 * @since 2021-08-11
 */
public interface NotificationHistoryService extends BaseService<NotificationHistory, NotificationHistoryVO, NotificationHistory> {

    /**
     * 保存通知
     *
     * @param notificationHistoryDTO 通知历史DTO
     * @return {@link Long}
     */
    Long saveNotification(NotificationHistoryDTO notificationHistoryDTO);

    /**
     * 查询通知历史
     *
     * @param notificationHistoryFilterDTO 通知历史过滤器DTO
     * @return {@link PageVO<NotificationHistoryVO>}
     */
    PageVO<NotificationHistoryVO> findNotificationHistory(NotificationHistoryFilterDTO notificationHistoryFilterDTO);


}
