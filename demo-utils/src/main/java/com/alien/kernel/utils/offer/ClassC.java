package com.alien.kernel.utils.offer;

/**
 * @Auther: FYJ
 * @Date: 2019/6/24 21:00
 * @Description:
 */
public class ClassC extends ClassB {


    public static void main(String[] args) {
        ClassA a = new ClassB();
        ClassB b = new ClassB();
        ClassC c = new ClassC();

        System.out.println(a.show(b));
        System.out.println(a.show(c));

    }
}
