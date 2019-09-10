package com.alien.kernel.service.pattern.adapter;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/15 16:24
 * @Description: 类-适配器模式
 */
public class Adapter extends SourceClass implements ITargetTable {
    @Override
    public void method2() {
        System.out.println("this is target method2");
    }


    /**
     * 类-适配器模式 （将SourceClass的功能扩展到接口ITargetable中）
     * <p>
     * 在接口中定义一个和source中相同的方法，在适配时不对其实现，它会调用父类的实现
     *
     * @param
     * @return
     */
    public static void main(String[] args) {
        ITargetTable target = new Adapter();
        target.method1();
        target.method2();
    }
}
