package com.alien.kernel.utils.thread;

import com.google.common.collect.Maps;

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
     * 从表面上看ThreadLocal相当于维护了一个map，key就是当前的线程id，value就是需要存储的对象。(set方法)
     * <p>
     * <p>
     * 其实，在其内部有一个ThreadLocalMap的静态内部类，每个线程持有一个ThreadLocalMap对象。每个新的线程Thread都会实例化一个
     * ThreadLocalMap赋值给threadLocals，若已存在则直接使用（在set方法的时候可见）
     * <p>
     * 在构造ThreadLocalMap的时候，会创建一个Entry数组，相当于每个线程都持有一个Entry数组 table，而一切的读取过程都是
     * 操作这个数组完成的，在使用set方法的时候，通过线程的hashCode与length位运算确定出一个索引值i存入数组。
     */
    private static final ThreadLocal<String> THREAD_LOCAL=new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println(outPutStr("A"));
            }).start();
        }

    }

    static Map<String,String> map= Maps.newHashMap();
    private static String outPutStr(String s) {
       /* map.put(s,Thread.currentThread().getName());
        return map.get(s);*/

        THREAD_LOCAL.set(s+Thread.currentThread().getName());
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

}
