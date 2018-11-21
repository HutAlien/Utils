package com.example.alien.utils.controller;

import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 15:02
 * @Description:
 */
public class AbstractController {
    protected Subject getConcurrentSubject() {
        return SecurityUtils.getSubject();
    }
    protected SysUser getSysUser(){
        SysUser sysUser=(SysUser) getConcurrentSubject().getPrincipal();
        if (sysUser==null){

        }
        return sysUser;
    }
}
