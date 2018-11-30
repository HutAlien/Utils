package com.example.alien.utils.controller.manager;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;
import com.example.alien.utils.service.sysuser.ISysUserService;
import com.example.alien.utils.utils.AjaxResultUtils;
import com.example.alien.utils.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:25
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class UsrRest {
    @Autowired
    ISysUserService userService;

    @GetMapping("/user/list")
    @ApiOperation(value = "用户列表",notes = "获取全部用户")
    public AjaxResult findAll() {
        return userService.findAllUser();
    }

    @GetMapping("/user")
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
