package com.alien.kernel.service.pattern.proxy;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/26 16:26
 * @Description:
 */
public class PersonImpl implements IPerson {

    @Override
    public void SayHellow() {
        try {
            Thread.currentThread().sleep(20);
            System.out.println("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SayBye() {
        try {
            Thread.currentThread().sleep(20);
            System.out.println("bye");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
