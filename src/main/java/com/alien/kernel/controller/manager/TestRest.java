package com.alien.kernel.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

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
        map.addAttribute("host", "This is a test");
        return "index";
    }

    @PostMapping("/json")
    public String getRequestJson(HttpServletRequest request){
        String json=getJsonString(request);
        String json1=getJsonString(request);
        return null;
    }

    /**
     * 从request中获取json数据
     *
     * @Param:
     * @return:
     */
    private String getJsonString(HttpServletRequest request){
        BufferedReader br;
        StringBuilder sb = null;
        String reqBody = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
            reqBody = reqBody.substring(reqBody.indexOf("{"));
            //request.setAttribute("inputParam", reqBody);
            return reqBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
