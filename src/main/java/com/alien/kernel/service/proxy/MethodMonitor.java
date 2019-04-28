package com.alien.kernel.service.proxy;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/26 16:33
 * @Description:
 */
public class MethodMonitor {
    private long satrt;
    private String method;

    public MethodMonitor(String method) {
        this.method = method;
        System.out.println("begin monitor...");
        this.satrt = System.currentTimeMillis();
    }

    public void log() {
        long excutionTime = System.currentTimeMillis() - satrt;
        System.out.println("end monitor...");
        System.out.println("Method " + method + "excute time" + excutionTime);
    }
}
