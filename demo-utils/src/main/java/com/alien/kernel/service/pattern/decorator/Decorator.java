package com.alien.kernel.service.pattern.decorator;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 10:35
 * @Description: 装饰器模式（动态扩展对象功能）
 */
public class Decorator implements ISource {

    private ISource source;

    public Decorator(ISource source) {
        this.source = source;
    }

    @Override
    public void sourceMethod() {
        System.out.println("before source----------------");
        source.sourceMethod();
        System.out.println("after source------------------");
    }

    public static void main(String[] args) {
        ISource source = new SourceImpl();
        ISource decorator = new Decorator(source);
        decorator.sourceMethod();
    }
}
