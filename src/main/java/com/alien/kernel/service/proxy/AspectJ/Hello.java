package com.alien.kernel.service.proxy.AspectJ;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/29 09:52
 * @Description:
 */
public class Hello {
    public void sayHello(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Hello hello=new Hello();
        hello.sayHello();
    }
}
