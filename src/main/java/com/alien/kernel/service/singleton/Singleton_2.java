package com.alien.kernel.service.singleton;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/21 10:04
 * @Description: 懒加载相关
 */
public class Singleton_2 {
    private volatile static Singleton_2 singleton;

    private Singleton_2() {
    }

    private static Singleton_2 getInstance() {         //线程不安全
        if (singleton == null) {
            singleton = new Singleton_2();
        }
        return singleton;
    }

    synchronized private static Singleton_2 getInstance1() {    //方法加锁 线程安全  执行效率低
        if (singleton == null) {
            singleton = new Singleton_2();
        }
        return singleton;
    }

    //尝试同步代码块,
    private static Singleton_2 getInstance2() {                      //使用双检测DCL锁机制  线程安全 执行效率提高
        if (singleton != null) {
            //实例用volatile修饰(保证多线程可见性)
        } else {
            synchronized (Singleton_2.class) {
                if (singleton == null) {
                    singleton = new Singleton_2();
                }
            }
        }
        return singleton;
    }

}
