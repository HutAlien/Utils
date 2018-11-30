package com.example.alien.utils.controller.login;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;
import com.example.alien.utils.utils.ShiroUtils;
import com.example.alien.utils.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 10:04
 * @Description: 登录接口
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginRest {
    @Autowired
    private Dao dao;

    @PostMapping("/login")
    public AjaxResult login(final String username, final String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        return doLogin(token);
    }

    private AjaxResult doLogin(UsernamePasswordToken token) {
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {  //账户不存在
            return new AjaxResult(AjaxCode.FAILURE, "账户不存在");
        } catch (IncorrectCredentialsException e) { //密码错误
            return new AjaxResult(AjaxCode.FAILURE, "密码错误");
        } catch (LockedAccountException e) {  //账户被锁定
            return new AjaxResult(AjaxCode.FAILURE, "账户被锁定");
        } catch (AuthenticationException e) {  //认证失败
            return new AjaxResult(AjaxCode.FAILURE, "认证失败");
        }
        ShiroUtils.getSubject().getSession().setTimeout(1231313); //设置Session过期时间，以毫秒为单位
        String accessToken = StringUtils.getUUID();
        Date expireTime = new Date(new Date().getTime() + 1231313);  //设置token过期时间
        SysUser user = ShiroUtils.getCurrentUser();
        user.setExpireDate(expireTime);
        user.setToken(accessToken);
        dao.updateIgnoreNull(user);
        return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.SUCCESS_MESSAFE, user);
    }

}
