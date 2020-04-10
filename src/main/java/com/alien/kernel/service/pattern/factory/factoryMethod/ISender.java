package com.alien.kernel.service.pattern.factory.factoryMethod;

public interface ISender {

    /**
     * 工厂方法模式，如果你需要新增功能，增加实现类实现ISend接口，并且创造一个工厂类实现Provider接口
     * 无需改动现成的代码，这种模式扩展性较好
     *
     * @param
     * @return
     */
    void Send();
}
