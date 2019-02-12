package com.alien.kernel.controller.manager;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.dto.annotation.Log;
import com.alien.kernel.dto.annotation.Permits;
import com.alien.kernel.entity.SysUser;
import com.alien.kernel.service.sysuser.ISysUserService;
import com.alien.kernel.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:25
 * @Description: 用户API
 */
@RestController
@RequestMapping("/api")
@Validated
public class UserRest {
    @Autowired
    ISysUserService userService;

    @GetMapping("/user/list")
    @Permits(roles = "1")
    @Log(operationType = "get", operationName = "获取用户列表")
    public AjaxResult findAll() {
        return userService.findAllUser();
    }

    @GetMapping("/user")
    @Log(operationName = "查询用户")
    public AjaxResult findByUserName(String username) {
        if (StringUtils.isAllTranslate(username)) {
            return userService.findByName(username);
        }
        return new AjaxResult(AjaxCode.FAILURE, "请输入用户名");
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户", notes = "更新用户信息")
    public AjaxResult updateUser(SysUser user) {
        return userService.updateUser(user);
    }

    @PostMapping("/user")
    public AjaxResult addUser(@Validated SysUser user) {
        return userService.addUser(user);
    }


    @GetMapping("/test")
    public AjaxResult test(@NotBlank(message = "paasword不能为空") String password) {//@Pattern(regexp ="^[A-Za-z0-9]+$",message = "参数只能包含数字和字母")
        return new AjaxResult(AjaxCode.SUCCESS, "请求成功");
    }
}
