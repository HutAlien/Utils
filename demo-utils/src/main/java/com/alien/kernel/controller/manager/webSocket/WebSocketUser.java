package com.alien.kernel.controller.manager.webSocket;

import lombok.Data;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 14:18
 * @Description:
 */
@Data
public class WebSocketUser {
    private String username;
    private String token;
    private Long userId;
}
