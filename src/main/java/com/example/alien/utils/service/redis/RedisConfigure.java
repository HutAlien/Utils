package com.example.alien.utils.service.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/29 12:28
 * @Description:
 */
@Configuration
public class RedisConfigure {
    @Bean
    public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
}
