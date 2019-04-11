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
}
