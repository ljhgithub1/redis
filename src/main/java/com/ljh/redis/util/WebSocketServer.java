package com.ljh.redis.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/31 8:38
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    /**
     * 日志输出
     */
    static Log log = LogFactory.get(WebSocketServer.class);
    /**
     * 静态变量记录在线连接数
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    /**
     * 与客户端连接会话，session是给客户端传输数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //一旦连接成功，将当前这个对象存入CopyOnWriteArraySet
        webSocketServers.add(this);
        //在线人数加一
        addOnlineCount();
        sendMessage("连接成功");
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        //从set中删除这个对象
        webSocketServers.remove(this);
        //人数减一
        subOnlineCount();
        log.info("有一个连接关闭！，当前人数为: " + getOnlineCount());
    }

    /**
     * 收到客户端发送的消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        webSocketServers.forEach(i -> {
            i.sendMessage(message);
        });
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) {
        webSocketServers.forEach(i -> {
            i.sendMessage(message);
        });
    }
    public void sendMessage(String message)  {
        webSocketServers.forEach(i->{
            log.info("【websocket消息推送】广播消息{}"+message);
            try {
                i.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
