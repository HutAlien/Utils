package com.alien.kernel.configure.shiro;

import com.alien.kernel.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 10:09
 * @Description: shiro Realm
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    Dao dao;

    /**
     * 授权
     *
     * @param
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 验证
     *
     * @param
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String) authenticationToken.getPrincipal();
        SysUser sysUser=dao.fetch(SysUser.class, Cnd.where("username","=",username));
        if (sysUser==null){
            throw new UnknownAccountException("用户不存在");
        }
        if (sysUser.getStatus()==0){
            throw new LockedAccountException("账户已被锁定");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}
