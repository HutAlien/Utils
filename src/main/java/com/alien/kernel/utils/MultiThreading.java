package com.alien.kernel.utils;

import java.util.LinkedList;
import java.util.Queue;

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

        //
        Queue<Integer> queue=new LinkedList();
        queue.poll();
    }
    /**
     * Executor 提供了四种线程池的，newSingleThreadPool  newCacheThreadPool  newFixedThreadPool  newScheduleThreadPool
     *
     *
     * newSingleThreadPool:   创建一个单线程化的线程池，他会用唯一的工作线程来执行任务，保证所有的任务按照指定顺序(FIFO LIFO 优先级)执行
     * newCacheThreadPool:    创建一个可缓存的线程池，如果线程池线程数超过需要，可灵活回收空闲线程，若无可回收，则创建新的线程
     * newFixedThreadPool:    创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * newScheduleThreadPool: 创建一个定长线程池，支持定时及周期性执行任务
     *
     *
     */


}
