package com.alien.kernel.utils.concurrent;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

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
     *
     *
     *
     */
    @Test
    public void concurrentHashMapTest() {
        Map<String, Object> map = new ConcurrentHashMap<>(1);
    }
}
