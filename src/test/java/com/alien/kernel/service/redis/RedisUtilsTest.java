package com.alien.kernel.service.redis;

import com.alien.kernel.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/29 10:41
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
       /* redisUtils.savaData("user", SysUser.builder().username("alien").password("123456").build());
        Object user = redisUtils.getData("user");
        log.info("user:{}", user);
        log.info("-----------------------------");*/
        redisUtil.set("1", "Tom");
        log.info("1={}",redisUtil.get("1"));

    }

    @Test
    public void StringRedisTemplate() {
        redisUtils.setkey("alien", "123456");
        log.info(redisUtils.getkey("alien"));
    }

}