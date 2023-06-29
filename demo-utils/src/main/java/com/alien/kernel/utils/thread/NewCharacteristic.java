package com.alien.kernel.utils.thread;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/4/3 16:15
 * @version: jdk的一些新特性
 */
public class NewCharacteristic {

    /**
     * java 9
     * 移除了在 Java 8 中 被废弃的垃圾回收器配置组合，同时把G1设为默认的垃圾回收器实现。替代了之前默认使用的Parallel GC
     * String 再也不用 char[] 来存储啦，改成了 byte[] 加上编码标记，节约了不少空间
     * 接口的私有方法
     *
     * Java 11
     * 本地变量类型推断 如 var javastack = "javastack";局部变量类型推断就是左边的类型直接使用 var 定义，而不用写具体的类型，编译器能根据右边的表达式自动推断类型
     * java 12(非LTS版本)
     * switch表达式扩展
     *
     *
     *
     * java 19
     * <p>虚拟线程</p>
     * 虚拟线程是JVM管理的轻量级线程，它有助于我们编写高吞吐量的并发应用程序
     * 平台线程：和OS线程一对一映射
     *
     * 虚拟线程也是java.lang.Thread的一个实例，它在底层OS线程上执行代码，但他不会在代码的整个生命周期内阻塞OS线程
     * 我们可以在程序中创建非常多的虚拟线程(数百万)，而不依赖与平台线程的数量，这些虚拟线程由JVM管理，因此也不会有上下文切换
     * 的开销，因为它们作为普通Java对象存储在RAM中
     *
     *
     *
     *
     * @param
     * @return
     */

}
