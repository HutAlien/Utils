package com.example.rocketmq;

import com.example.rocketmq.consumer.MessageInfoSink;
import com.example.rocketmq.dto.ItemMessageInfo;
import com.example.rocketmq.producer.MessageInfoSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/6/28 11:35
 * @version:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RocketMqTestCase {

    @Autowired
    private MessageInfoSource messageInfoSource;

    @Autowired
    private Source source;

    @Autowired
    private Sink sink;

    @Autowired
    private MessageInfoSink messageInfoSink;


    /**
     *
     * 自定义消息通道发送
     * @param
     * @return
     */
    @Test
    public void sendMessageTest() {
        ItemMessageInfo itemMessageInfo=new ItemMessageInfo();
        itemMessageInfo.setId(UUID.randomUUID().toString());
        itemMessageInfo.setItemName("测试3");
        itemMessageInfo.setCreated(LocalDateTime.now());
        final Message<ItemMessageInfo> springMessage = MessageBuilder
                .withPayload(itemMessageInfo)
                .setHeader(RocketMQHeaders.KEYS, itemMessageInfo.getId())
                // 发送延迟消息,延时级别 1~18 (1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 【 1=1s 2=5s 3=10s】)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 1)
                .setHeader(MessageConst.PROPERTY_TAGS,"item-tags")
                .build();
        boolean sendFlag=messageInfoSource.itemInfoChangeOutput().send(springMessage);
        log.info("sendFlag={},key={}",sendFlag,itemMessageInfo.getId());
    }

    @Test
    public void sendTransactionMessageTest() {
        ItemMessageInfo itemMessageInfo=new ItemMessageInfo();
        itemMessageInfo.setId(UUID.randomUUID().toString());
        itemMessageInfo.setItemName("测试事务消息");
        itemMessageInfo.setCreated(LocalDateTime.now());
        final Message<ItemMessageInfo> springMessage = MessageBuilder
                .withPayload(itemMessageInfo)
                .setHeader(RocketMQHeaders.KEYS, itemMessageInfo.getId())
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(MessageConst.PROPERTY_TAGS,"item-tags")
                .build();
        boolean sendFlag=messageInfoSource.itemInfoChangeOutput().send(springMessage);
        log.info("sendFlag={},key={}",sendFlag,itemMessageInfo.getId());
    }

    /**
     * 系统自带的Source发送
     *
     * @param
     * @return
     */
    @Test
    public void sourceSend(){
        boolean flag = source.output().send(MessageBuilder.withPayload("hellow rocketmq").build());
    }

    /**
     * 使用sink进行接收消费，也可以使用@StreamListener(Sink.INPUT)加在bean的方法上进行消费
     *
     * @param
     * @return
     */
    @Test
    public void sinkReceive() {
        while (true) {
            sink.input().subscribe((Message<?> message) -> {
                MessageHeaders messageHeaders = message.getHeaders();
                log.info("MessageHeaders={}", messageHeaders);
                byte[] bytes = (byte[]) message.getPayload();
                String bodyStr = new String(bytes, Charset.forName("UTF-8"));
                log.info("receive message={}", bodyStr);
            });
        }
    }

    /**
     * 使用自定义消息通道来消费消息
     *
     * @param
     * @return
     */
    @Test
    public void itemInfoChangeInputTest() {
        while (true) {
            messageInfoSink.itemInfoChangeInput().subscribe((Message<?> message) -> {
                MessageHeaders messageHeaders = message.getHeaders();
                log.info("MessageHeaders={}", messageHeaders);
                byte[] bytes = (byte[]) message.getPayload();
                String bodyStr = new String(bytes, Charset.forName("UTF-8"));
                log.info("receive message={}", bodyStr);
            });
        }
    }
}
