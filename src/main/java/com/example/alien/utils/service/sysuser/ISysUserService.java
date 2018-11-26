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
    List<SysUser> findAllUser();

    SysUser findByName(SysUser sysUser);

    SysUser updateUser(SysUser sysUser);
}
