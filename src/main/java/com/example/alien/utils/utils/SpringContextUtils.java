package com.example.alien.utils.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/29 11:33
 * @Description:
 */
@Component
public class SpringContextUtils implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static Object getBean(String className){
        return applicationContext.getBean(className);
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }
}
