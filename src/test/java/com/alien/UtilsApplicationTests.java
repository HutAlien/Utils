package com.alien;

import com.alien.kernel.utils.thread.AsyncTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsApplicationTests {

    private Logger logger= LoggerFactory.getLogger(UtilsApplicationTests.class);

    @Autowired
    AsyncTest asyncTest;

    @Test
    public void contextLoads() {
    }

    @Test
    public void asycTest() throws InterruptedException {
        logger.info("调用开始...");
        asyncTest.printAsyncResult();
        logger.info("调用结束...");
        Thread.sleep(5000);
    }
}
