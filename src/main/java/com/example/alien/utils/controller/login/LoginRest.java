package com.example.alien.utils.controller.login;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 10:04
 * @Description: 登录接口
 */
@RestController
@RequestMapping("/api")
public class LoginRest {
    @PostMapping("/login")
    public AjaxResult login(final String username, final String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        return doLogin(token);
    }

    private AjaxResult doLogin(UsernamePasswordToken token) {
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {  //账户不存在
            return new AjaxResult();
        } catch (IncorrectCredentialsException e) { //密码错误
            return new AjaxResult();
        } catch (LockedAccountException e) {  //账户被锁定
            return new AjaxResult();
        } catch (AuthenticationException e) {  //认证失败
            return new AjaxResult();
        }
        return new AjaxResult(AjaxCode.SUCCESS,AjaxCode.SUCCESS_MESSAFE);
    }

}
