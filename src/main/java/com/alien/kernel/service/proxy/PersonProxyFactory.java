package com.alien.kernel.service.proxy;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/28 15:17
 * @Description: 获取代理对象
 */
public class PersonProxyFactory {
    public static IPerson newJdkProxy() {
        // 代理PersonImpl
        DynamicProxy dynamicProxy = new DynamicProxy(new PersonImpl());
        IPerson proxy = dynamicProxy.getProxy();
        return proxy;
    }

    public static void main(String[] args) {
        IPerson jdkproxy=PersonProxyFactory.newJdkProxy();
        jdkproxy.SayHellow();
        jdkproxy.SayBye();
    }
}
