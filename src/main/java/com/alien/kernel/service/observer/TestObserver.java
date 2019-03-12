package com.alien.kernel.service.observer;

import com.alien.kernel.service.observer.observerlist.ObserverBlack;
import com.alien.kernel.service.observer.observerlist.ObserverGreen;
import com.alien.kernel.service.observer.observerlist.ObserverRed;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/23 15:07
 * @Description:
 */
public class TestObserver implements Subject {

    private List<Observer> observers = Lists.newArrayList();

    @Override
    public void ObserverRegister(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObserver(String str) {
        observers.forEach((o) -> o.notice(str));
    }

    public static void main(String[] args) {
        TestObserver testObserver = new TestObserver();
        testObserver.ObserverRegister(new ObserverBlack());
        testObserver.ObserverRegister(new ObserverGreen());
        testObserver.ObserverRegister(new ObserverRed());
        //
        testObserver.notifyObserver("black");
    }
}
