package com.example.alien.utils.service.sysuser;

import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;

import java.util.List;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:22
 * @Description:
 */
public interface ISysUserService {
    AjaxResult findAllUser();

    AjaxResult findByName(String username);

    AjaxResult updateUser(SysUser sysUser);
}
