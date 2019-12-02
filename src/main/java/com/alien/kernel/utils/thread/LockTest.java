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

    /**
     * Synchronized和Lock的主要区别：
     * 它们都是可重入锁 (什么是可重入锁？可重入锁，指的是以线程为单位，当一个线程获取对象锁之后，这个线程可以再次获取本对象上的锁，
     * 而其他的线程是不可以的。可重入锁的意义之一在于防止死锁。)
     * Sync是JVM关键字，可锁方法和代码块，默认是非公平锁，不可以等待中断。
     *
     *
     *ReentrantLock是Java的一个Lock接口的实现类，使用更为灵活：
     *获取锁和释放锁需手动进行
     *获取锁失败的时候可以手动中断，默认是非公平锁，也可以指定为公平锁。
     *获取不到锁时可以中断等待
     *
     * ps：公平锁是指多个线程在等待同一个锁时，必须按照申请的时间顺序来依次获得锁；而非公平锁则不能保证这一点
     *
     */

}
