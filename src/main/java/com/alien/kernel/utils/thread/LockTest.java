package com.alien.kernel.utils.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: FYJ
 * @Date: 2019/9/20 16:21
 * @Description:
 */
public class LockTest {

    private Lock lock=new ReentrantLock();

    public void method(){
        lock.lock();            //获取锁，获取不到就一直等待
        //lock.tryLock();       //如果获取锁的时候锁被占用就返回false，否则返回true
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        //lock.unlock();
        System.out.println(Thread.currentThread().getName() + "释放了锁");
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        new Thread(() -> {
            lockTest.method();
        }).start();
        new Thread(() -> {
            lockTest.method();
        }).start();
    }

}
