package com.alien.kernel.service.pattern.observer.observerlist;

import com.alien.kernel.service.pattern.observer.Observer;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/23 15:02
 * @Description:
 */
public class ObserverBlack implements Observer {
    @Override
    public void notice(String str) {
        if (null != str && str.contains("black")) {
            System.out.println(str);
        }
    }
}
