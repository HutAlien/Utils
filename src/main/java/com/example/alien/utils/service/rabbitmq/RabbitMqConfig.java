package com.example.alien.utils.service.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 09:09
 * @Description:
 */
@Configuration
public class RabbitMqConfig {
    public static String msg="topic.msg";
    public static String msgs="topic.msgs";
    //注入一个消息队列
    @Bean
    public Queue Queue(){
        return new Queue("hello");
    }

    @Bean
    public Queue queueMessage(){
        return new Queue(msg);
    }
    @Bean
    public Queue queueMessages(){
        return new Queue(msgs);
    }
    //注入一个交换机bean
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }
    @Bean
    Binding bindingExchangeMessage(TopicExchange topicExchange,Queue queueMessage){     //queueMessage是指定由哪个方法产生的bean
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.msg");
    }
    @Bean
    Binding bindingExchangeMessages(TopicExchange topicExchange,Queue queueMessages){
        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");   //# 匹配多个字符，* 匹配一个字符
    }
}
