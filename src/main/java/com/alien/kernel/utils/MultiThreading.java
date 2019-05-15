package com.alien.kernel.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/10 14:11
 * @Description:
 */
public class MultiThreading implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                synchronized (this) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + " " + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, "thread1");
        Thread thread1 = new Thread(runnable, "thread2");
        thread.start();
        thread1.start();

    }

    /**
     * Executor 提供了四种线程池的，newSingleThreadPool  newCacheThreadPool  newFixedThreadPool  newScheduleThreadPool
     * <p>
     * <p>
     * newSingleThreadPool:   创建一个单线程化的线程池，他会用唯一的工作线程来执行任务，保证所有的任务按照指定顺序(FIFO LIFO 优先级)执行
     * newCacheThreadPool:    创建一个可缓存的线程池，如果线程池线程数超过需要，可灵活回收空闲线程，若无可回收，则创建新的线程
     * newFixedThreadPool:    创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * newScheduleThreadPool: 创建一个定长线程池，支持定时及周期性执行任务
     */
    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    ExecutorService cache=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //
    LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue();
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private volatile Boolean flag = true;

    public void customer() {
        cachedThreadPool.execute(() -> {
            while (flag) {
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * ThreadLocal主要解决的是线程级别的共享变量的问题，即每个线程拥有自己的变量，每个线程绑定自己的变量值，线程变量之间有隔离性。
     *
     * @param
     * @return
     */


}
