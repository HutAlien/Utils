package com.alien.kernel.service.pattern.factory.StaFactoryMethod;

import com.alien.kernel.service.pattern.factory.StaFactoryMethod.impl.SendMialImpl;
import com.alien.kernel.service.pattern.factory.StaFactoryMethod.impl.SendSmsImpl;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 9:53
 * @Description: 静态工厂方法模式(存在的问题是 ： 要拓展程序 ， 就必须对工厂类进行修改 ， 违背了开闭原则)
 */
public class SendFactory {

    public static ISend produceSms() {
        return new SendSmsImpl();
    }

    public static ISend produceMail() {
        return new SendMialImpl();
    }

    public static void main(String[] args) {
        ISend send = SendFactory.produceSms();
        send.send();
    }
}
