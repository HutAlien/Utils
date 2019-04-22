package com.alien.kernel.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/17 16:26
 * @Description:
 */
@RequestMapping("/test")
@Controller
public class TestRest {

    @GetMapping
    public String getHost(ModelMap map) {
        map.addAttribute("host","This is a test");
        return "index";
    }
}
