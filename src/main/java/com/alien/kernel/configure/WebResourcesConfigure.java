package com.alien.kernel.configure;

import com.alien.kernel.interceptors.ApiInterceptor;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 10:08
 * @Description:
 */
@Configuration
public class WebResourcesConfigure implements WebMvcConfigurer{
    @Autowired
    Dao dao;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor(dao)).addPathPatterns("/**").excludePathPatterns("/api/login");
    }
}
