package com.example.alien.utils.entity;

import jdk.nashorn.internal.parser.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

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
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Column
    @Comment("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Column
    @Comment("token过期时间")
    private Date expireDate;
    @Column
    @Comment("token")
    private String token;
    @Column
    @Comment("账户状态")
    private Integer status;

    public SysUser() {
    }
}
