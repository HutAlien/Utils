package com.example.alien.utils.controller.manager;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.service.sysuser.ISysUserService;
import com.example.alien.utils.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/user/list")
    public AjaxResult findAll() {
        return ResultUtils.get(sysUserService.findAllUser());
    }

    @RequestMapping("/user/{username}")
    public AjaxResult findByUserName(@PathVariable("username") String username){
        return new AjaxResult(AjaxCode.SUCCESS,AjaxCode.SUCCESS_MESSAFE,sysUserService.findByName(username));
    }
}
