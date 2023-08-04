package com.example.mysql.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/31 17:13
 * @version:
 */
@Configuration
public class CaptchaConfig {

    /**
     * 配置验证码生成bean
     *
     * @param
     * @return DefaultKaptcha
     */
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        //验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //配置
        Properties properties = new Properties();
        //是否有边框
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        //设置边框颜色
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        //边框粗细度，默认为1
        // properties.setProperty("kaptcha.border.thickness","1");
        //验证码
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "code");
        //验证码文本字符颜色 默认为黑色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        //设置字体样式
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        //字体大小，默认40
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
        //验证码文本字符内容范围 默认为abced2345678gfynmnpwx
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "zxcvbnmasdfghjklqwertyuiop");
        //字符长度，默认为5
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字符间距 默认为2
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "4");
        //验证码图片宽度 默认为200
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "100");
        //验证码图片高度 默认为40
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
