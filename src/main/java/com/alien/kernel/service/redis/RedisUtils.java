package com.alien.kernel.service.redis;

import com.alien.kernel.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/29 10:28
 * @Description:
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;   //可处理任何类型的键值对

    //@Autowired
    // private StringRedisTemplate stringRedisTemplate;   //只能对String类型的键值对处理

    private static ValueOperations<String, String> ops;  //会报空指针，因为@Autowired最后才执行

    //在构造方法上使用@Autowired,表示在构建这个类的时候就注入这个参数!!!
    @Autowired
    public RedisUtils(StringRedisTemplate stringRedisTemplate) {     //表示在构造这个对象的时候注入
        ops = stringRedisTemplate.opsForValue();
    }

    public void savaData(String key, SysUser user) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();  //操作字符串
        ops.set(key, user);
    }

    public Object getData(String key) {
        ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

    public void setkey(String key, String value) {
        //ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }

    public String getkey(String key) {
        //ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

}
