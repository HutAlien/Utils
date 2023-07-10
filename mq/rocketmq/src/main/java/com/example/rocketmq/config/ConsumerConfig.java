package com.example.rocketmq.config;

import com.example.rocketmq.consumer.MessageInfoSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/6 9:28
 * @version:
 */
@Component
@Slf4j
public class ConsumerConfig implements ApplicationRunner {

    @Autowired
    private Sink sink;

    @Autowired
    private MessageInfoSink messageInfoSink;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sink.input().subscribe((Message<?> message) -> {
            MessageHeaders messageHeaders = message.getHeaders();
            byte[] bytes = (byte[]) message.getPayload();
            String bodyStr = new String(bytes, Charset.forName("UTF-8"));
            log.info("sink message={}", bodyStr);
        });
        messageInfoSink.itemInfoChangeInput().subscribe((Message<?> message) -> {
            MessageHeaders messageHeaders = message.getHeaders();
            byte[] bytes = (byte[]) message.getPayload();
            String bodyStr = new String(bytes, Charset.forName("UTF-8"));
            log.info("messageInfoSink message={}", bodyStr);
            //设置抛出异常则消费失败
            //throw new RuntimeException("测试消费失败！");
        });
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy执行---------");
    }
}
