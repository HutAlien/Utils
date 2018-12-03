package com.example.alien.utils.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/3 15:39
 * @Description:
 */
public class ApiFilter extends FormAuthenticationFilter{

    protected boolean onAccessDenied(){
        return false;
    }

}
