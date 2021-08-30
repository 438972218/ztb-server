package com.xdcplus.vendor.websocket;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.vendor.service.MonitorBiddingService;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket服务端
 *
 * @author Rong.Jia
 * @date 2021/08/17 11:52:16
 */
@Slf4j
@Component
@DependsOn("springUtil")
@ServerEndpoint(value = "/imServer/{userId}")
public class WebSocketServer {

    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String userId = "";

    private final MonitorBiddingService monitorBiddingService = SpringUtil.getBean(MonitorBiddingService.class);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);
            addOnlineCount();
        }

        log.info("用户连接 : {}, 当前在线人数为 : {}", userId, getOnlineCount());

        sendMessage("{\"code\":" + 0 + ",\"message\":\"连接成功\"}");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

        deleteUser();

        log.info("用户退出 : {} ,当前在线人数为 : {}", userId, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

        log.info("用户消息 : {}, 报文 : {}", userId, message);

        monitorBiddingService.messageHandler(message, this.userId);
        monitorBiddingService.sendBiddingTime(this.userId, DateUtil.current());
    }

    /**
     * @param session 会话
     * @param error 错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误 : {}, 原因 : {}", this.userId, error);

        deleteUser();
    }

    /**
     * 实现服务器主动推送
     * @param message 消息信息
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("sendMessage {}", e.getMessage());
        }

    }

    /**
     * 删除用户
     */
    private void deleteUser() {

        if (webSocketMap.containsKey(this.userId)) {
            webSocketMap.remove(this.userId);
            this.subOnlineCount();
        }
    }

    /**
     * 发送信息
     *
     * @param message 消息
     * @param userId  用户ID
     */
    public static synchronized void sendMessage(String message, @PathParam("userId") String userId) {

        log.info("发送消息到 userId : {}，报文 : {} ", userId, message);

        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else if (StrUtil.isBlank(userId)) {
            if (webSocketMap.size() > NumberConstant.ZERO) {
                for (Map.Entry<String, WebSocketServer> entry : webSocketMap.entrySet()) {
                    entry.getValue().sendMessage(message);
                }
            }
        }
    }

    /**
     * 获取客户端用户
     *
     * @return {@link List}<{@link String}>
     */
    public static List<String> getClientUsers() {

        List<String> users = CollectionUtil.newArrayList();

        if (webSocketMap.size() > NumberConstant.ZERO) {
            Enumeration<String> keys = webSocketMap.keys();
            while (keys.hasMoreElements()) {
                users.add(keys.nextElement());
            }
        }

        return users;
    }

    public synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public synchronized void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    public synchronized void subOnlineCount() {
        onlineCount.decrementAndGet();
    }


}
