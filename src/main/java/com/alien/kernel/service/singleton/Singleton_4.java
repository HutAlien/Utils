package com.alien.kernel.service.singleton;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/21 10:43
 * @Description: 使用静态代码块实现单例模式 （线程安全）
 */
public class Singleton_4 {
    private static Singleton_4 singleton;

    private Singleton_4() {

    }

    static {
        singleton = new Singleton_4();
    }

    private static Singleton_4 getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + Singleton_4.getInstance().hashCode());
        }).start();
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + Singleton_4.getInstance().hashCode());
        });
        thread.start();
    }
}
