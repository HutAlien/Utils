package com.alien.kernel.service.observer.observerlist;

import com.alien.kernel.service.observer.Observer;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/23 15:03
 * @Description:
 */
public class ObserverGreen implements Observer {
    @Override
    public void notice(String str) {
        if (null != str && str.contains("green")) {
            System.out.println(str);
        }
    }
}
