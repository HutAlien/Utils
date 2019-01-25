package com.alien.kernel.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 14:54
 * @Description:
 */
@Slf4j
public class SysUserTest {

    @Test
    public void testSysUser(){
        SysUser user=SysUser.builder().username("Tom").password("123456").build();
        log.info("User={}", user);
    }
}