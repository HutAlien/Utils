package com.alien.kernel.service.pattern.factory.factoryMethod;

public interface Provider {
    /**
     * 新增工厂的时候实现接口
     *
     * @param
     * @return
     */
    ISender produce();
}
