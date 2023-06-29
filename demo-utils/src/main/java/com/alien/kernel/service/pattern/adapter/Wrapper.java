package com.alien.kernel.service.pattern.adapter;

/**
 * @Auther: FYJ
 * @Date: 2019/8/27 15:50
 * @Description: 对象 适配器模式
 */
public class Wrapper implements ITargetTable {

    private SourceClass sourceClass;

    public Wrapper(SourceClass sourceClass) {
        super();
        this.sourceClass = sourceClass;
    }

    @Override
    public void method1() {
        sourceClass.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is target method2");
    }

    public static void main(String[] args) {
        SourceClass sourceClass = new SourceClass();
        Wrapper wrapper = new Wrapper(sourceClass);
        //wrapper.method1();
        wrapper.method2();
    }

    /**
     *<p>
     *     接口适配器模式：
     *     当我们需要实现一个接口时，必须实现其所有的抽象方法，这显然不是我们所想要的，
     *     我们只需要其中的某一些，为了解决这样的问题，我们可以借助一个抽象类，让该抽象类实现该接口的
     *     所有方法，写一个类去继承抽象类，重写我们需要的方法就行了。
     *</p>
     *
     */
}
