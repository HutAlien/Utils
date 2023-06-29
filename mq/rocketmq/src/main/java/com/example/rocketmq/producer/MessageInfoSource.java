package com.example.rocketmq.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/6/28 11:28
 * @version:
 */
public interface MessageInfoSource {

    /**
     * 发送商品变化消息
     *
     * @param
     * @return MessageChannel
     */
    @Output("itemInfoChange-output")
    MessageChannel itemInfoChangeOutput();
}
