package com.example.alien.utils.service.observer.observerlist;

import com.example.alien.utils.service.observer.Observer;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/23 15:02
 * @Description:
 */
public class ObserverRed implements Observer {
    @Override
    public void notice(String str) {
        if (null != str && str.contains("red")) {
            System.out.println(str);
        }
    }
}
