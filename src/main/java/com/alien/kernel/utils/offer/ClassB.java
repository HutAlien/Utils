package com.alien.kernel.utils.offer;

/**
 * @Auther: FYJ
 * @Date: 2019/6/24 21:00
 * @Description:
 */
public class ClassB extends ClassA {

    public String show(ClassB b) {
        return "B and B";
    }

    public String show(ClassA A) {
        return "A and B";
    }

}
