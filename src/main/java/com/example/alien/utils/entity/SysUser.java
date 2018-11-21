package com.example.alien.utils.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 11:09
 * @Description:
 */
@Table("u_sys_user")
@Data
public class SysUser implements Serializable {
    @Name
    @Column
    @Comment("用户名")
    private String username;
    @Column
    @Comment("密码")
    private String password;
}
