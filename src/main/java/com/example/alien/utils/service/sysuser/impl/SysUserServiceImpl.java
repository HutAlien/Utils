package com.example.alien.utils.service.sysuser.impl;

import com.example.alien.utils.entity.SysUser;
import com.example.alien.utils.exception.CustomException;
import com.example.alien.utils.service.sysuser.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
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
@Slf4j
//@CacheConfig(cacheNames = "users")
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Dao dao;

    @Override
    @Cacheable(cacheNames = "userList")
    public List<SysUser> findAllUser() {
       /* boolean haskey=redisTemplate.hasKey("users");
        if (haskey){
            List<SysUser> list=(List<SysUser>) redisTemplate.opsForValue().get("users");
            return list;
        }*/
        List<SysUser> list = dao.query(SysUser.class, null);
        return list;
    }

    @Override
    @Cacheable(cacheNames = "user")   //能根据请求参数的不同  对不同的结果进行缓存！默认用请求参数作为key
    public SysUser findByName(SysUser sysUser) {
       /* boolean haskey=redisTemplate.hasKey("user");  //，每次有key，就从缓存中取值
        if (haskey){
            return (SysUser) redisTemplate.opsForValue().get("user");
        }*/
        SysUser user=dao.fetch(SysUser.class, Cnd.where("username","=",sysUser.getUsername()));
        if (user==null){
            throw new CustomException("用户不存在");
        }
        return user;
    }

    @Override
    @CachePut(key = "#result.username",value = "user")     //更新后 更新缓存
    public SysUser updateUser(SysUser sysUser) {
        int state = dao.updateIgnoreNull(sysUser);
        if (state>0){
            SysUser user=dao.fetch(SysUser.class,sysUser.getUsername());
            return user;
        }
        throw new CustomException("更新异常");
    }
}
