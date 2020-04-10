package com.alien.kernel.service.pattern.proxy.usually;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 11:00
 * @Description:
 */
public class SourceProxy implements ISource {

    private ISource source;

    public SourceProxy() {
        super();
        this.source = new SourceImpl();
    }

    private void before() {
        System.out.println("before-------------");
    }

    @Override
    public void sourceAble() {
        before();
        source.sourceAble();
        after();
    }

    private void after() {
        System.out.println("after---------------");
    }

    public static void main(String[] args) {
        ISource source = new SourceProxy();
        source.sourceAble();
    }
}
