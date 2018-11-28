package com.example.alien.utils.controller.manager;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;
import com.example.alien.utils.service.sysuser.ISysUserService;
import com.example.alien.utils.utils.AjaxResultUtils;
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
    ISysUserService sysUserService;

    @GetMapping("/user/list")
    public AjaxResult findAll() {
        return AjaxResultUtils.get(sysUserService.findAllUser());
    }

    @GetMapping("/user")
    public AjaxResult findByUserName(SysUser sysUser){
        return new AjaxResult(AjaxCode.SUCCESS,AjaxCode.SUCCESS_MESSAFE,sysUserService.findByName(sysUser));
    }

    @PostMapping("/user/update")
    public AjaxResult updateUser(SysUser user) {
        SysUser sysUser = sysUserService.updateUser(user);
        return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.SUCCESS_MESSAFE, sysUser);
    }
}
