package com.xdcplus.vendor.common.timer;

import cn.hutool.core.date.DateUtil;
import com.xdcplus.vendor.service.MonitorBiddingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
@Slf4j
@Component
public class TimingTask {

    @Autowired
    private MonitorBiddingService monitorBiddingService;

    /**
     * 广播websocket 客户端状态
     *  每10秒执行一次
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void broadcastClientState() {

        log.info("broadcastClientState : {}", System.currentTimeMillis());

        monitorBiddingService.sendClientState();
    }

    /**
     * 刷新竞价时间信息
     *  每5秒执行一次
     */
    @Scheduled(fixedDelay = 5 * 1000)
    public void refreshBiddingTimeInfo() {
        log.info("refreshBiddingTimeInfo : {}", System.currentTimeMillis());
        monitorBiddingService.refreshBiddingTimeInfo();
        monitorBiddingService.sendBiddingTime(null, DateUtil.current());
    }














}
