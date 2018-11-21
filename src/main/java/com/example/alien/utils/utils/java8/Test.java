package com.example.alien.utils.utils.java8;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author QWE
 * @Date 2018/11/21 20:55
 * @Description
 **/
public class Test {

    public static void TestCollection() {
        Map<String, Object> map = new HashMap<>();
        map.put("A", 100);
        map.put("B", 200);
        map.put("C", 500);
        map.forEach((k, v) -> {
            System.out.println(k + "  " + v);
        });
        map.forEach((k, v) -> {
            System.out.println(k + "" + v);
            if (k.equals("B")) {
                System.out.println("Hello B");
            }
        });
    }

    public static void TestThread() {
        Runnable runnable = () -> {
            System.out.println("用拉姆达表达式创建线程");
        };
        new Thread(runnable).start();
        new Thread(() -> {
            System.out.println("这是用拉姆达表达式创建的线程1");
        }).start();
    }

    public static void main(String[] args) {
        // Test.TestCollection();
        Test.TestThread();
    }
}
