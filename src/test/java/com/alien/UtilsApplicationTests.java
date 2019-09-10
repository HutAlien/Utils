package com.alien;

import com.alien.kernel.utils.thread.AsyncTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsApplicationTests {

    @Autowired
    AsyncTest asyncTest;

    @Test
    public void contextLoads() {
    }

    @Test
    public void asycTest() throws InterruptedException {
        asyncTest.printAsyncResult();
    }
}
