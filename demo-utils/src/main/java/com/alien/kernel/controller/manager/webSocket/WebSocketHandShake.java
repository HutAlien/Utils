package com.alien.kernel.controller.manager.webSocket;

import com.alien.kernel.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 14:23
 * @Description:
 */
@Slf4j
public class WebSocketHandShake implements HandshakeInterceptor {

    //握手之前干啥，常用来注册用户信息，绑定 WebSocketSession
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("开始连接之前的准备");
        //SysUser user= ShiroUtils.getCurrentUser();
        WebSocketUser socketUser = new WebSocketUser();
        socketUser.setUsername("alien");
        socketUser.setUserId(1L);
        socketUser.setToken(StringUtils.getUUID());
        map.put("userInfo", socketUser);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        //todo
    }
}
