package com.alien.kernel.service.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/26 16:27
 * @Description: JDK动态代理
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { //add()
        System.out.println("before-----------------");
        Object obj = method.invoke(target, args);
        System.out.println("after--------------------");
        return obj;
    }

    /**
     * this表示实现InvocationHandler接口的类，里面的invoke方法是关联的处理逻辑
     *
     * @param
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
