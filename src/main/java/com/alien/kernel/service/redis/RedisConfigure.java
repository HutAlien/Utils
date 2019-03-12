package com.alien.kernel.service.redis;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/29 12:28
 * @Description: /*
 *//*
@Configuration
public class RedisConfigure {
    @Bean
    public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
}*/
