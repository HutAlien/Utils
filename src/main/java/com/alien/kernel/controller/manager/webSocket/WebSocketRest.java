package com.alien.kernel.controller.manager.webSocket;

import com.alien.kernel.dto.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: FYJ
 * @Date: 2019/8/22 14:15
 * @Description:
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketRest {

    /**
     * 获取在线人用户信息
     *
     * @param
     * @return
     */
    @GetMapping
    public ModelAndView getOnlineCount(ModelAndView mv) {
        //return new AjaxResult(200, "获取成功", MyWebSocketHandler.getOnlineUser());
        mv.addObject("userList", MyWebSocketHandler.getOnlineUser());
        mv.setViewName("index.html");
        return mv;
    }

    /**
     * 获取在线人数统计
     *
     * @param
     * @return
     */
    public AjaxResult getUserCount() {
        return new AjaxResult(200, "获取成功", null, MyWebSocketHandler.getOnlineUser().size());
    }
}
