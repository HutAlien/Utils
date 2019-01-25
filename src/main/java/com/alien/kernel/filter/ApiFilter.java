package com.alien.kernel.filter;

import com.alien.kernel.dto.AjaxResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.nutz.json.Json;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/3 15:39
 * @Description:
 */
public class ApiFilter extends FormAuthenticationFilter{

    @Override
    //Shiro未认证处理
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse=(HttpServletResponse) response;
        httpServletResponse.setContentType("application/json;charset=utf-8");
        response.getWriter().write(Json.toJson(new AjaxResult(601,"请先登录认证")));
        return false;
    }
}
