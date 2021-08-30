package com.xdcplus.vendor.service;

import com.xdcplus.vendor.websocket.WebSocketServer;

import javax.annotation.Nullable;

/**
 * 监测招标服务
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
public interface MonitorBiddingService {

    /**
     * 消息处理程序
     *
     * @param message         消息
     * @param userId 用户ID
     */
    void messageHandler(String message, String userId);

    /**
     * 推送客户端状态
     */
    void sendClientState();

    /**
     * 推送竞价时间
     * @param userId 用户ID
     * @param finalBiddingTime 最后竞价时间
     */
    void sendBiddingTime(@Nullable String userId, Long finalBiddingTime);

    /**
     * 刷新竞价时间信息
     */
    void refreshBiddingTimeInfo();













}
