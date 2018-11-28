package com.example.alien.utils.service.rabbitmq;

import com.example.alien.utils.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 09:20
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloSenderTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void send() {
        /*for (int i=0;i<50;i++){
            helloSender.send();
        }*/
        /*SysUser user=new SysUser("alien","123456");
        helloSender.send(user);*/
        //helloSender.send1();
        helloSender.send2();
    }
}