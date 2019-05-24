package com.alien.kernel.utils.thread;

import com.alien.kernel.utils.DateUtils;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/22 14:20
 * @Description:
 */
public class ThreadTest {



    private static String methodA() throws InterruptedException {
        Thread.sleep(2000);
        return "A,ok";
    }

    private static String methodB() throws InterruptedException {
        Thread.sleep(1000);
        return "B,ok";
    }
    private static final ExecutorService executorService= Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Date star=DateUtils.getCurrentTime();
        Future<String> resultA=executorService.submit(()->{
            return methodA();
        });
        Future<String> resultB=executorService.submit(()-> {
            return methodB();
        });
        Thread.sleep(3000);
        System.out.println(resultA.get()+resultB.get());
        Date finish=DateUtils.getCurrentTime();
        System.out.println(finish.getTime()-star.getTime());
        System.out.println(Thread.currentThread().isAlive());
        executorService.shutdown();
    }


}
