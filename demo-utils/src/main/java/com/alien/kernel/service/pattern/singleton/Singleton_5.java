package com.alien.kernel.service.pattern.singleton;

/**
 * @Auther: FengYunJun
 * @Date:
 * @Description: 单元素的枚举已经成为实现Singleton的最佳方法
 */
public enum  Singleton_5 {
    INSTANCE;

    public void doSomething(){

    }

    /**
     * 序列化问题：
     *  任何一个readObject方法，不管是显式的还是默认的，它都会返回一个新建的实例，
     *  这个新建的实例不同于该类初始化时创建的实例。”当然，这个问题也是可以解决的，
     * 想详细了解的同学可以翻看《effective java》第77条：对于实例控制，枚举类型优于readResolve
     *
     * 私有化构造器并不保险：
     * 《effective java》中只简单的提了几句话：“享有特权的客户端可以借助AccessibleObject.setAccessible方法，
     *  通过反射机制调用私有构造器。如果需要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。
     *
     */

    public static void main(String[] args) {
        Singleton_5.INSTANCE.doSomething();
    }
}
