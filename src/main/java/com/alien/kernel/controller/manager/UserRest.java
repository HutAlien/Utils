package com.alien.kernel.controller.manager;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.dto.annotation.Log;
import com.alien.kernel.entity.SysUser;
import com.alien.kernel.service.sysuser.ISysUserService;
import com.alien.kernel.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:25
 * @Description: 用户API
 */
@RestController
@RequestMapping("/api")
public class UserRest {
    @Autowired
    ISysUserService userService;

    @GetMapping("/user/list")
    @ApiOperation(value = "用户列表",notes = "获取全部用户")
    public AjaxResult findAll() {
        return userService.findAllUser();
    }

    @GetMapping("/user")
    @Log(operationName = "查询用户")
    public AjaxResult findByUserName(String username){
        if (StringUtils.isAllTranslate(username)){
            return userService.findByName(username);
        }
        return new AjaxResult(AjaxCode.FAILURE,"请输入用户名");
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户",notes = "更新用户信息")
    public AjaxResult updateUser(SysUser user) {
       return userService.updateUser(user);
    }

    @PostMapping("/user")
    public AjaxResult addUser(SysUser user){
        return userService.addUser(user);
    }
}
