package com.example.mysql.controller;

import com.example.common.base.BaseResult;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/31 17:21
 * @version:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
@Slf4j
public class CaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;


    @GetMapping
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        //-------------------生成验证码 begin --------------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        log.info("验证码内容：{}", text);
        //将验证码文本内容放入session
        request.getSession().setAttribute("captcha", text);
        //根据文本验证码内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式为jpg
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/verification")
    public BaseResult<Boolean> verificationCode(String code, HttpSession httpSession) {
        String captcha = (String) httpSession.getAttribute("captcha");
        boolean verificationFlag = StringUtils.isNotBlank(code) ? code.equalsIgnoreCase(captcha) : false;
        return BaseResult.success(verificationFlag);
    }

}
