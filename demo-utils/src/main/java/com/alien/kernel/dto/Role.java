package com.alien.kernel.dto;

/**
 * @Auther: FengYunJun
 * @Date: 2019/2/19 10:49
 * @Description: 角色枚举
 */
public enum Role {
    ADMIN("1", "管理员"),
    USER("2", "用户");

    private String role;
    private String roleName;

    Role(String role, String roleName) {
        this.role = role;
        this.roleName = roleName;
    }

    public String getRole() {
        return this.role;
    }

    public String getRoleName() {
        return this.roleName;
    }

}
