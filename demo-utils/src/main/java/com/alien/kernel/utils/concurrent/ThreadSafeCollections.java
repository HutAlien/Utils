package com.alien.kernel.utils.concurrent;

import com.alien.kernel.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * @author: alien
 * @date: 2019/11/29 15:56
 * @description: 线程安全相关
 */
public class ThreadSafeCollections {

    /**
     * add():队列未满返回true 满则抛出异常
     * offer()：队列未满返回true 满则返回false --可传参设定时间
     * put()：队列未满直接插入，满时会阻塞等待
     * <p>
     * remove()：为空抛出异常
     * poll()：为空返回空
     * take()：为空则阻塞等待
     * <p>
     * LinkedBlockingQueue使用lock加锁，put()和take()使用的是不同的两把锁(putLock,takeLock 这样能大大提高队列的吞吐量,)
     * 插入时会生成一个额外的Node()对象，长时间内需要高效并发地处理大批量数据的系统中，考虑GC的影响
     * <p>
     * element() ：获取但不移除此队列的头元素，没有元素则抛异常
     * peek() :获取但不移除此队列的头；若队列为空，则返回 null。
     */
    @Test
    private void linkedBlockingQueueTest() {
        //没有指定容量则使用Integer.MAX_VALUE作为上限
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
    }

    /**
     * ArrayBlockingQueue内部的阻塞队列是通过重入锁ReenterLock和Condition条件队列实现的
     * put()和take()都用一个锁,由此也意味着两者无法真正并行运行
     * <p>
     * ArrayBlockingQueue中的元素存在公平访问与非公平访问的区别，
     * 对于公平访问队列，被阻塞的线程可以按照阻塞的先后顺序访问队列，即先阻塞的线程先访问队列。
     * 而非公平队列，当队列可用时，阻塞的线程将进入争夺访问资源的竞争中，也就是说谁先抢到谁就执行，没有固定的先后顺序。
     * <p>
     * 思考：linkedBlockingQueue是不是非公平队列 ? 是的，若是公平队列的话效率就下降了
     */
    @Test
    private void arrayBlockingQueueTest() {
        //默认非公平锁
        ArrayBlockingQueue queue = new ArrayBlockingQueue(128);
        //公平阻塞队列
        ArrayBlockingQueue q = new ArrayBlockingQueue(128, true);
    }


    /**
     * CopyOnWriteArrayList的CUD等方法都是加上了锁（通过lock）保证线程安全，读操作没有。
     * 写入时先复制，写入完成后把元数据替换成当前副本
     * <p>
     * <p>
     * Vector的curd方法都加上Synchronized保证线程安全，效率较低。
     *
     * @Param:
     * @return:
     */
    @Test
    public void threadList() {
        List<String> list = new CopyOnWriteArrayList<>();
        List<String> vector = new Vector<>();
        //返回一个加锁线程安全的list
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * hashMap():
     * 扩容这个过程涉及到 rehash、复制数据等操作，所以非常消耗性能。因此最好能指定容量，以减少扩容带来的性能消耗
     * <p>
     * concurrentHashMap():
     * 不像hashTable一样效率低下(因为所有访问hashTable的线程读必须竞争同一把锁)
     * concurrentHashMap使用的是锁分段技术，将数据分成一段一段地存放，然后给每段数据加上锁，这样当一个线程访问其中一段数据的时候，
     * 其他段的数据也可以被其他线程访问到。
     */
    @Test
    public void concurrentHashMapTest() {
        Map<String, Object> map = new ConcurrentHashMap<>(1);
    }


    //只保证可见性，不能保证原子性
    //happens-before规则中的volatile变量规则
    private volatile int value = 0;

    @Test
    public void atomicTest() throws InterruptedException {
        for (int x = 0; x < 50; x++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addValue();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(value);
    }

    private void addValue() {
        value++;
    }

    /**
     * 原子更新基本操作类型
     * 原子更新数组
     * 原子更新引用类型
     * 原子更新字段类
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[1]);
    private AtomicReference<Employee> atomicReference = new AtomicReference<>();

    @Test
    public void atomicUpdate() {
        atomicInteger.addAndGet(10);
        atomicIntegerArray.addAndGet(0, 10);
        //
        class Tuser {
            public volatile int age;
            public volatile Integer source;
        }
        //更新引用类型中的字段
        Tuser tuser = new Tuser();
        tuser.source = 10;
        System.out.println(tuser.source);
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater
                .newUpdater(Tuser.class, Integer.class, "source");
        updater.set(tuser, new Integer(100));
        System.out.println(tuser.source);
        //原子更新字段类(对象里的字段不能是包装类型)
        tuser.age = 20;
        AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Tuser.class, "age");
        fieldUpdater.incrementAndGet(tuser);
        System.out.println(tuser.age);
    }

    /**
     * 实现一个线程安全的队列有两种方式：一种是使用阻塞算法，另一种则是使用非阻塞算法
     * 阻塞算法一般是用锁机制来实现
     * 非阻塞算法则是使用循环CAS的方式来实现
     */
    @Test
    public void ConcurrentLinkedQueueTest() {
        //循环cas实现线程安全
        ConcurrentLinkedQueue linkedQueue = new ConcurrentLinkedQueue();
    }
}
