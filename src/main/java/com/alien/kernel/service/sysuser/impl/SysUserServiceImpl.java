package com.alien.kernel.service.sysuser.impl;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.exception.CustomException;
import com.alien.kernel.entity.SysUser;
import com.alien.kernel.service.sysuser.ISysUserService;
import com.alien.kernel.utils.AjaxResultUtils;
import com.alien.kernel.utils.java8.NewDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     *
     * @Cacheable ，用来声明方法是可缓存，将结果存储到缓存中以便后续使用相同参数调用时不需执行实际的方法，直接从缓存中取值。
     *
     * @CachePut，使用 @CachePut 标注的方法在执行前，不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，
     * 并将执行结果以键值对的形式存入指定的缓存中。
     *
     * @CacheEvict，是用来标注在需要清除缓存元素的方法或类上的，当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     *
     */

    @Override
    //  @Cacheable(cacheNames = "userList")
    public AjaxResult findAllUser() {
       /* boolean haskey=redisTemplate.hasKey("users");
        if (haskey){
            List<SysUser> list=(List<SysUser>) redisTemplate.opsForValue().get("users");
            return list;
        }*/
        List<SysUser> list = dao.query(SysUser.class, null);
        return AjaxResultUtils.get(list);
    }

    @Override
    //@Cacheable(cacheNames = "user")   //能根据请求参数的不同  对不同的结果进行缓存！默认用请求参数作为key
    public AjaxResult findByName(String username) {
       /* boolean haskey=redisTemplate.hasKey("user");  //，每次有key，就从缓存中取值
        if (haskey){
            return (SysUser) redisTemplate.opsForValue().get("user");
        }*/
        SysUser user = dao.fetch(SysUser.class, Cnd.where("username", "=", username));
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        return AjaxResultUtils.get(user);
    }

    @Override
    @CachePut(key = "#result.username", value = "user")     //更新后 更新缓存
    public AjaxResult updateUser(SysUser sysUser) {
        int state = dao.updateIgnoreNull(sysUser);
        if (state > 0) {
            SysUser user = dao.fetch(SysUser.class, sysUser.getUsername());
            return AjaxResultUtils.get(user);
        }
        throw new CustomException("更新异常");
    }

    @Override
    public AjaxResult addUser(SysUser user) {
        //user.setCreateTime(NewDateUtils.getCurrentDateTime());
        if (dao.insert(user) == null) {
            throw new CustomException("添加异常");
        }
        return new AjaxResult(AjaxCode.SUCCESS, "添加成功");
    }
}
