package com.alien.kernel.controller.manager.webSocket;

import lombok.Data;

import java.util.List;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 14:18
 * @Description:
 */
@Data
public class WebsocketMessage {
    private WebSocketUser sendUser;
    private List<WebSocketUser> recieveUser;
    private String message;

}
