package com.alien.kernel.utils;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 15:57
 * @Description:
 */
public class CommonUtils {

    public static void main(String args[]) {
        Runnable runnable = CommonUtils::run;
        new Thread(runnable).start();
    }
    public static void run() {
        System.out.println("方法引用的代码块");
    }
}
