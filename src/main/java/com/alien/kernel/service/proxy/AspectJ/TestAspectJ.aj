package com.alien.kernel.service.proxy.AspectJ;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/29 09:55
 * @Description:
 */
public aspect TestAspectJ {
    void around():call(void Hello.sayHello()){
        System.out.println("开始说Hello");
        proceed();
        System.out.println("结束说Hello");
    }
}
