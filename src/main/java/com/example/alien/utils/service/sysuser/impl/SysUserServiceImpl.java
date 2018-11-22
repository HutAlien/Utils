package com.example.alien.utils.service.sysuser.impl;

import com.example.alien.utils.dto.AjaxResult;
import com.example.alien.utils.entity.SysUser;
import com.example.alien.utils.service.sysuser.ISysUserService;
import com.example.alien.utils.utils.ResultUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:23
 * @Description: redis测试
 */
@Service
//@CacheConfig(cacheNames = "users")
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Dao dao;

    @Override
    @Cacheable(cacheNames = "userList")
    public List<SysUser> findAllUser() {
        boolean haskey=redisTemplate.hasKey("users");
        if (haskey){
            List<SysUser> list=(List<SysUser>) redisTemplate.opsForValue().get("users");
            return list;
        }
        List<SysUser> list = dao.query(SysUser.class, null);
        return list;
    }

    @Override
    @Cacheable(cacheNames = "user")
    public SysUser findByName(String username) {
        boolean haskey=redisTemplate.hasKey("user");  //，每次有key，就从缓存中取值
        if (haskey){
            return (SysUser) redisTemplate.opsForValue().get("user");
        }
        SysUser sysUser=dao.fetch(SysUser.class, Cnd.where("username","=",username));
        return sysUser;
    }
}
