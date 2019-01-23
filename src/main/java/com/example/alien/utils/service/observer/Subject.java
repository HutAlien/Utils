package com.example.alien.utils.service.observer;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/23 15:04
 * @Description:
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param
     * @return
     */
    void ObserverRegister(Observer observer);

    /**
     * @param str
     * @return
     */
    void notifyObserver(String str);

}
