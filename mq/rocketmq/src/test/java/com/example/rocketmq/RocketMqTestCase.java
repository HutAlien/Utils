package com.example.rocketmq;

import com.example.rocketmq.dto.ItemMessageInfo;
import com.example.rocketmq.producer.MessageInfoSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Test
    public void sendMessageTest() {
        ItemMessageInfo itemMessageInfo=new ItemMessageInfo();
        itemMessageInfo.setId(UUID.randomUUID().toString());
        itemMessageInfo.setItemName("测试");
        itemMessageInfo.setCreated(LocalDateTime.now());
        final Message<ItemMessageInfo> springMessage = MessageBuilder
                .withPayload(itemMessageInfo)
                .setHeader(RocketMQHeaders.KEYS, itemMessageInfo.getId())
                // 发送延迟消息
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 1)
                .build();
        boolean sendFlag=messageInfoSource.itemInfoChangeOutput().send(springMessage);
        log.info("sendFlag={}",sendFlag);
    }
}
