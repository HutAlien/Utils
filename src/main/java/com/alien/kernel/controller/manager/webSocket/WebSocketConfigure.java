package com.alien.kernel.controller.manager.webSocket;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 15:38
 * @Description:
 */
@Configuration
@EnableWebSocket
public class WebSocketConfigure implements WebSocketConfigurer {

    @Bean
    public MyWebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public WebSocketHandShake myWebSocketHandShake() {
        return new WebSocketHandShake();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myWebSocketHandler(),"/websocket/echo")
                .addInterceptors(myWebSocketHandShake())
                .setAllowedOrigins("*");
    }
}
