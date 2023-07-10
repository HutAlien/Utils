package com.example.rocketmq.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/5 15:34
 * @version:
 */
public interface MessageInfoSink {

    /**
     * 接收商品变化消息
     *
     * @param
     * @return SubscribableChannel
     */
    @Input("itemInfoChange-input")
    SubscribableChannel itemInfoChangeInput();
}
