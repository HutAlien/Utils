package com.example.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @Description: 如果是非事务消息时注释掉@RocketMQTransactionListener否则重复创建生产者组与yaml中的冲突，会报错
 * @Author: FengYunJun
 * @Date: 2023/7/10 15:29
 * @version:
 */
//@RocketMQTransactionListener(txProducerGroup = "dev-producer-1")
@Slf4j
public class TransactionMsgListener implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务，在消息发送成功会回调执行，一单事务提交成功，下游应用的消费者就能收到该消息
     *
     * @param message
     * @param o
     * @return RocketMQLocalTransactionState
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        //数据库访问操作
        log.info("执行本地事务----");
        String transactionId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /**
     * 检查本地事务执行状态，如果executeLocalTransaction方法中返回的状态是位置UNKNOWN或者为返回的状态，
     * 就会默认在预处理发送的一分钟后由Broker通知Producer检查本地事务，在Producer中回调本地事务监听器中的checkLocalTransaction方法，
     * 检查本地事务时，可以根据事务ID查询本地事务的状态，再返回具体事务状态给Broker
     *
     * @param message
     * @return RocketMQLocalTransactionState
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("执行回查状态----");
        String transactionId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
