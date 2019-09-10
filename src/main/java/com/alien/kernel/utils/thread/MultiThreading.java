package com.alien.kernel.utils.thread;

import java.util.concurrent.*;

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
    ExecutorService cache = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

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

    // Java线程池的完整构造函数
    //public ThreadPoolExecutor(
    //  int corePoolSize, // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
    //  int maximumPoolSize, // 线程数的上限
    //  long keepAliveTime, TimeUnit unit, // 超过corePoolSize的线程的idle时长，
    //                                     // 超过这个时间，多余的线程会被回收。
    //  BlockingQueue<Runnable> workQueue, // 任务的排队队列
    //  ThreadFactory threadFactory, // 新线程的产生方式
    //  RejectedExecutionHandler handler) // 拒绝策略  有4种


    //java.lang.Runtime.availableProcessors() 方法: 返回可用处理器的Java虚拟机的数量。
    //构建线程池
    private static ExecutorService myexecutorService;

    static {
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;       //不合理设置会影响性能，甚至耗尽线程
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);//指定容量 避免使用无界队列导致内存溢出
        RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();//拒绝策略 直接忽略
        myexecutorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);
    }

    /**
     *
     *
     * ThreadLocal主要解决的是线程级别的共享变量的问题，即每个线程拥有自己的变量，每个线程绑定自己的变量值，线程变量之间有隔离性。
     *
     *
     */


    /**
     *<p>
     * @Async注解使用条件：
     *
     * 一般用在类的方法上，如果用在类上，那么这个类的所有方法都是异步执行的
     * 所使用的@Async注解方法的类对象应该是Spring容器管理的bean对象；
     * 调用异步方法类上需要配置上注解@EnableAsync
     *
     *</p>
     */

}
