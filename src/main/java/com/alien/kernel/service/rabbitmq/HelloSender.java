package com.alien.kernel.service.rabbitmq;

import com.alien.kernel.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 09:12
 * @Description:
 */
@Slf4j
//@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        log.info("发送消息开始");
        this.rabbitTemplate.convertAndSend("hello", "this is content");
        log.info("发送消息成功");
    }

    public void send(SysUser user) {
        System.out.println("user:" + user.toString());
        this.rabbitTemplate.convertAndSend("hello", user);
        log.info("发送对象成功");
    }

    public void send1() {      //将根据路由键匹配到两个队列
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.msg", "i am msg1");
        log.info("消息发送成功");
    }

    public void send2() {      //只匹配到一个队列ic
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.msgs", "i am msg2");
    }
}
