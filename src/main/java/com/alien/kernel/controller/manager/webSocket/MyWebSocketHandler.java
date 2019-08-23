package com.alien.kernel.controller.manager.webSocket;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 14:41
 * @Description: 实现webSocketHandler接口
 */
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {
    private static CopyOnWriteArraySet<WebSocketSession> onLineUserList = new CopyOnWriteArraySet<>();

    /**
     * 建立连接之后
     *
     * @param
     * @return
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("建立连接成功!");
        onLineUserList.add(webSocketSession);
        webSocketSession.sendMessage(new TextMessage("当前登录用户数" + onLineUserList.size()));
    }

    /**
     * 处理消息
     *
     * @param webSocketSession 客户端的会话session 可用于向客户端发送消息
     * @param webSocketMessage 消息接口
     * @return
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("接收到客户端信息={}", Json.toJson(webSocketMessage.getPayload()));
        //服务端发送给客户端
        //webSocketSession.sendMessage(new TextMessage("Hi,I am server!"));
        WebSocketUser user = (WebSocketUser) webSocketSession.getAttributes().get("userInfo");
        sendMessageToAll(new TextMessage("用户" + user.getUsername() + "已上线,当前线上用户数"+onLineUserList.size()));
    }


    public void sendMessage(WebsocketMessage message, WebSocketMessage wsMessage) {
        List<WebSocketUser> userList = message.getRecieveUser();
        if (userList == null || userList.size() == 0) {
            sendMessageToAll(wsMessage);
        } else {
            log.info("发送给其他");
        }
    }

    public void sendMessageToAll(WebSocketMessage socketMessage) {
        for (WebSocketSession user : onLineUserList) {
            if (user.isOpen()) {
                try {
                    user.sendMessage(socketMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 处理错误
     *
     * @param
     * @return
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * 连接关闭之后
     *
     * @param
     * @return
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        onLineUserList.remove(webSocketSession);
        WebSocketUser user = (WebSocketUser) webSocketSession.getAttributes().get("userInfo");
        sendMessageToAll(new TextMessage("用户" + user.getUsername() + "已下线,当前用户数"+onLineUserList.size()));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public static List<WebSocketUser> getOnlineUser() {
        List<WebSocketUser> userList = Lists.newArrayList();
        for (WebSocketSession session : onLineUserList) {
            userList.add(getUserBySession(session));
        }
        return userList;
    }

    private static WebSocketUser getUserBySession(WebSocketSession session) {
        return (WebSocketUser) session.getAttributes().get("userInfo");
    }

    public static synchronized int getOnlineCount() {
        return onLineUserList.size();
    }
}
