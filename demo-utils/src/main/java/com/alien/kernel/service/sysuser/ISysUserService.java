package com.alien.kernel.service.sysuser;

import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.entity.SysUser;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:22
 * @Description:
 */
public interface ISysUserService {
    AjaxResult findAllUser();

    AjaxResult findByName(String username);

    AjaxResult updateUser(SysUser sysUser);

    AjaxResult addUser(SysUser user);
}
