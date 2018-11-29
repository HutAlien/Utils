package com.example.alien.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 11:09
 * @Description:
 */
@Table("u_sys_user")
@Data
@AllArgsConstructor
@Builder
public class SysUser implements Serializable {
    @Column
    @Prev(els = @EL("uuid()"))  //可以是uuid 也可以是uuid(32)
    private String id;

    @Name
    @Column
    @Comment("用户名")
    private String username;
    @Column
    @Comment("密码")
    private String password;

    public SysUser() {
    }
}
