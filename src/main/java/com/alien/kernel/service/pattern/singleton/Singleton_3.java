package com.alien.kernel.service.pattern.singleton;

import java.io.*;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/21 10:17
 * @Description: 静态内部类实现单例模式(线程安全)
 */
public class Singleton_3 implements Serializable {
    private Singleton_3() {
    }

    private static class InnerClassSingle {
        private static final Singleton_3 singleton = new Singleton_3();
    }

    private static Singleton_3 getInstance() {
        return InnerClassSingle.singleton;
    }

    protected Object readResolve() {
        return InnerClassSingle.singleton;
    }


    //静态内部类虽然得到的实例是线程安全的，但如果遇到序列化对象时，得到得结果还是多例的
    //解决办法： 就是在反序列化的过程中使用readResolve()方法
    public static void main(String[] args) {
        Singleton_3 singleton_3 = Singleton_3.getInstance();
        //序列化
        try {
            FileOutputStream fos = new FileOutputStream(new File("myObject.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton_3);
            fos.close();
            oos.close();
            System.out.println(singleton_3.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //反序列化
        try {
            FileInputStream fis = new FileInputStream(new File("myObject.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Singleton_3 singleton_31 = (Singleton_3) ois.readObject();
            System.out.println(singleton_31.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
