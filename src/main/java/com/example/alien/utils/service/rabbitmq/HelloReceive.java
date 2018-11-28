package com.example.alien.utils.service.rabbitmq;

import com.example.alien.utils.entity.SysUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 09:17
 * @Description:
 */
@RabbitListener(queues = "topic.msg")
@Component
public class HelloReceive {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receive:"+hello);
    }

    @RabbitHandler
    public void process(SysUser user){
        System.out.println("receive user:"+user);
    }
}
