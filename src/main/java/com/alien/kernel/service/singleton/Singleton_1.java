package com.alien.kernel.service.singleton;

import java.io.*;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/21 09:44
 * @Description: 饿加载 线程安全
 */
public class Singleton_1 implements Serializable {
    private Singleton_1() {
    }

    private final static Singleton_1 singleton = new Singleton_1();//饿加载 线程安全

    private static Singleton_1 getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton_1 singleton = getInstance();
        try {
            FileOutputStream fos=new FileOutputStream(new File("D:/myObject.txt"));
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(singleton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
