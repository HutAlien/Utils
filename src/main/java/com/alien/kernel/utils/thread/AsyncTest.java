package com.alien.kernel.utils.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: FYJ
 * @Date: 2019/8/28 9:03
 * @Description:
 */
@Component
public class AsyncTest {

    @Async
    public void printAsyncResult() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("this is test method");
    }
}
