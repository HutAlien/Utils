package com.alien.kernel.interceptors;

import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.entity.SysUser;
import com.alien.kernel.utils.DateUtils;
import com.alien.kernel.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.json.Json;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/4 13:29
 * @Description: API拦截器
 */
@Slf4j
public class ApiInterceptor implements HandlerInterceptor {
    private Dao dao;

    // @Autowired
    public ApiInterceptor(Dao dao) {
        this.dao = dao;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        HandlerMethod handlerMethod = (HandlerMethod) handler; //SpringMVC会将请求通过处理器映射器将请求交给匹配的Handler处理, handler是描述处理请求的Handler
        Method method = handlerMethod.getMethod();    //获取请求的方法
        log.info("请求方法为={}", method);
        return volidateToken(request, response);
    }

    /**
     * 验证token
     *
     * @param request
     * @param response
     * @return
     */

    private boolean volidateToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = getToken(request);
        if (token == null) {
            response.getWriter().write(Json.toJson(new AjaxResult(402, "没有token")));
            return false;
        }
        SysUser user = dao.fetch(SysUser.class, Cnd.where("token", "=", token));
        if (user == null) {
            response.getWriter().write(Json.toJson(new AjaxResult(402, "无效的token")));
            return false;
        } else {
            if (token.equals(ShiroUtils.getCurrentUser().getToken())) {
                if (user.getExpireDate().getTime() < DateUtils.getCurrentTime().getTime()) {  //token过期
                    if (ShiroUtils.isAuthenticated()) {
                        ShiroUtils.getSubject().logout();
                        response.getWriter().write(Json.toJson(new AjaxResult(402, "token已过期")));
                        return false;
                    }
                    response.getWriter().write(Json.toJson(new AjaxResult(402, "登录已失效，请重新登录")));
                    return false;
                }
                return true;
            } else {
                response.getWriter().write(Json.toJson(new AjaxResult(402, "该账号已在其他地方登录,请重新登录")));
                return false;
            }
        }
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");  //从请求头中获取
        if (token == null) {
            token = request.getParameter("token"); //从请求参数中获取
        }
        return token;
    }
}
