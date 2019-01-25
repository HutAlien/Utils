package com.alien.kernel.utils;

import com.alien.kernel.entity.SysUser;
import com.alien.kernel.exception.CustomException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * @Auther: FengYunJun
 * @Date: 2018/11/30 10:30
 * @Description:
 */
public class ShiroUtils {

    //获取当前用主体
    public static Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new CustomException("用户未登录");
        }
        return subject;
    }

    //判断是否登录
    public static boolean isAuthenticated() {
        return getSubject().isAuthenticated();
    }

    //获取当前用户Session
    public static Session getSession() {
        return getSubject().getSession();
    }

    //获取当前用户
    public static SysUser getCurrentUser(){
        SysUser user=(SysUser) getSubject().getPrincipal();
        if (user==null){
            throw new CustomException("用户未登录");
        }
        return user;
    }
}
