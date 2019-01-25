package com.alien.kernel.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 10:52
 * @Description:
 */
@Component
@RabbitListener(queues = "topic.msgs")
public class HelloReceive1 {
    @RabbitHandler
    public void process(String msg){
        System.out.println("receive1:"+msg);
    }
}
