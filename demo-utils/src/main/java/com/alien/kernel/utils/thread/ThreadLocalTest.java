package com.alien.kernel.utils.thread;

import com.google.common.collect.Maps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @Auther: FYJ
 * @Date: 2019/9/12 9:48
 * @Description:
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据
     * ThreadLocal提供线程间的线程隔离，在使用的时候可以达到线程安全的效果
     * <p>
     * 其实，在其内部有一个ThreadLocalMap的静态内部类，每个线程持有一个ThreadLocalMap对象。每个新的线程Thread都会实例化一个
     * ThreadLocalMap赋值给threadLocals，若已存在则直接使用（在set方法的时候可见）
     * <p>
     * 在构造ThreadLocalMap的时候，会创建一个Entry数组，相当于每个线程都持有一个Entry数组 table，而一切的读取过程都是
     * 操作这个数组完成的，在使用set方法的时候，通过线程的hashCode与length位运算确定出一个索引值i存入数组。
     * <p>
     * 要点：
     * 每个Thread线程内部都有一个Map(ThreadLocalMap)。
     * Map里面存储线程本地对象（key）和线程的变量副本（value）
     * 但是，Thread内部的Map是由ThreadLocal维护的，由ThreadLocal负责向map获取和设置线程的变量值。
     * 所以对于不同的线程，每次获取副本值时，别的线程并不能获取到当前线程的副本值，形成了副本的隔离，互不干扰。
     * <p>
     * get()方法用于获取当前线程的副本变量值。
     * set()方法用于保存当前线程的副本变量值。
     * initialValue()为当前线程初始副本变量值。
     * remove()方法移除当前前程的副本变量值。
     * <p>
     * 每个ThreadLocal只能保存一个变量副本，如果想要上线一个线程能够保存多个副本以上，就需要创建多个ThreadLocal。
     * ThreadLocalMap中，也是用Entry(静态内部类)来保存K-V结构数据的。Entry继承自WeakReference（弱引用），
     * 但只有Key是弱引用类型的，Value并非弱引用。
     *
     * ThreadLocal内部的ThreadLocalMap键为弱引用(https://www.cnblogs.com/zjj1996/p/9140385.html)，弱引用的生命周期只能存活到下次GC前，会有内存泄漏的风险。
     * 所以，每次使用完ThreadLocal，都调用它的remove()方法，清除数据防止泄露。
     * <p>
     * 内存泄漏（Memory Leak）是指程序中己动态分配的堆内存由于某种原因程序未释放或无法释放，造成系统内存的浪费，
     * 导致程序运行速度减慢甚至系统崩溃等严重后果。
     * <p>
     * ThreadLocalMap(没有实现Map接口)中解决Hash冲突的方式并非链表的方式，而是采用线性探测的方式
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println(outPutStr("A"));
            }).start();
        }

    }

    static Map<String, String> map = Maps.newHashMap();

    private static String outPutStr(String s) {
       /* map.put(s,Thread.currentThread().getName());
        return map.get(s);*/

        THREAD_LOCAL.set(s + Thread.currentThread().getName());
        return THREAD_LOCAL.get();
    }

    /**
     *
     *一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
     *
     * 那么加载一个类时，静态内部类是不是被看做“静态代码块”同时被加载了？
     *
     * 答案是不会，只有在静态内部类被调用的时候才加载。（因此可以于实现单例模式）
     *
     */


    /**
     * SimpleDateFormat()线程安全问题解决：
     *  一.可以在使用的时候创建对象，避免多线程共享
     *  二.同步SimpleDateFormat对象（给对象加锁）
     *  三.使用ThreadLocal
     *
     *
     *  强引用，弱引用，软引用，虚引用（https://www.cnblogs.com/fengbs/p/7019687.html）
     *
     */

}
