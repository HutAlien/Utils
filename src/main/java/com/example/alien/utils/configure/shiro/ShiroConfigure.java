package com.example.alien.utils.configure.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 10:08
 * @Description: shiro配置
 */
@Configuration
public class ShiroConfigure {

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

    //拦截链配置
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/api/login");
        factoryBean.setSuccessUrl("/");
        factoryBean.setUnauthorizedUrl("/403");   //未授权页面

        Map<String,String> map=new HashMap<>();
        //map.put("/login","anon");
        map.put("/logout","logout");
        map.put("/api/login","anon");
        map.put("/**","authc");  //表示需要认证才可以访问

        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

}
