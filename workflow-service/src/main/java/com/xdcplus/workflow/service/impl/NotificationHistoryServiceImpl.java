package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.workflow.common.pojo.dto.NotificationHistoryDTO;
import com.xdcplus.workflow.common.pojo.dto.NotificationHistoryFilterDTO;
import com.xdcplus.workflow.common.pojo.entity.NotificationHistory;
import com.xdcplus.workflow.common.pojo.query.NotificationHistoryQuery;
import com.xdcplus.workflow.common.pojo.vo.NotificationHistoryVO;
import com.xdcplus.workflow.common.utils.MailUtils;
import com.xdcplus.workflow.mapper.NotificationHistoryMapper;
import com.xdcplus.workflow.service.NotificationHistoryService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知历史 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-11
 */
@Slf4j
@Service
public class NotificationHistoryServiceImpl extends BaseServiceImpl<NotificationHistory, NotificationHistoryVO, NotificationHistory, NotificationHistoryMapper> implements NotificationHistoryService {

    @Autowired
    private NotificationHistoryMapper notificationHistoryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveNotification(NotificationHistoryDTO notificationHistoryDTO) {

        NotificationHistory notificationHistory = new NotificationHistory();
        notificationHistory.setType(notificationHistoryDTO.getType());
        notificationHistory.setMessageId(notificationHistoryDTO.getMessageId());
        notificationHistory.setSubject(notificationHistoryDTO.getSubject());
        notificationHistory.setContent(notificationHistoryDTO.getContent());
        notificationHistory.setMailFrom(MailUtils.recipient(notificationHistoryDTO.getFrom()));
        notificationHistory.setMailTo(MailUtils.recipient(notificationHistoryDTO.getTo()));
        notificationHistory.setMailCc(MailUtils.recipient(notificationHistoryDTO.getCc()));
        notificationHistory.setMailBcc(MailUtils.recipient(notificationHistoryDTO.getBcc()));
        notificationHistory.setMailReply(MailUtils.recipient(notificationHistoryDTO.getReply()));
        notificationHistory.setCreatedTime(DateUtil.current());

        this.save(notificationHistory);

        return notificationHistory.getId();
    }

    @Override
    public PageVO<NotificationHistoryVO> findNotificationHistory(NotificationHistoryFilterDTO filterDTO) {

        PageVO<NotificationHistoryVO> pageVO = new PageVO<>();

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        NotificationHistoryQuery query = new NotificationHistoryQuery();
        BeanUtil.copyProperties(filterDTO, query);

        List<NotificationHistory> notificationHistoryList = notificationHistoryMapper.findNotificationHistory(query);
        PageInfo<NotificationHistory> pageInfo = new PageInfo<>(notificationHistoryList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public NotificationHistoryVO objectConversion(NotificationHistory notificationHistory) {
        NotificationHistoryVO notificationHistoryVO = super.objectConversion(notificationHistory);
        if (ObjectUtil.isNotNull(notificationHistoryVO)) {
            notificationHistoryVO.setFrom(MailUtils.recipient(notificationHistory.getMailFrom(), new TypeReference<MailBean>() {
            }));
            notificationHistoryVO.setTo(MailUtils.recipient(notificationHistory.getMailTo(), new TypeReference<List<MailBean>>() {
            }));
            notificationHistoryVO.setCc(MailUtils.recipient(notificationHistory.getMailCc(), new TypeReference<List<MailBean>>() {
            }));
            notificationHistoryVO.setBcc(MailUtils.recipient(notificationHistory.getMailBcc(), new TypeReference<List<MailBean>>() {
            }));
            notificationHistoryVO.setReply(MailUtils.recipient(notificationHistory.getMailReply(), new TypeReference<List<MailBean>>() {
            }));
        }

        return notificationHistoryVO;
    }
}
