package com.alien.kernel.utils.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * @Auther: FYJ
 * @Date: 2019/8/28 9:03
 * @Description:
 */
@Component
@Slf4j
public class AsyncTest {

    /**
     * 在使用默认的线程池调用异步方法的时候，会出现异常，但仍可以调用成功
     *
     * @param
     * @return
     */
    @Async
    public void printAsyncResult() throws InterruptedException {
        log.info("begin...................");
        Thread.sleep(3000);
        log.info("end.....................");
    }
}
