package com.alien.kernel.service.pattern.factory.factoryMethod.impl;

import com.alien.kernel.service.pattern.factory.factoryMethod.ISender;
import com.alien.kernel.service.pattern.factory.factoryMethod.Provider;

/**
 * @Auther: FYJ
 * @Date: 2019/8/27 14:36
 * @Description:
 */
public class SmsFactory implements Provider {

    @Override
    public ISender produce() {
        return new SmsSenderImpl();
    }

    public static void main(String[] args) {
        //工厂方法模式
        SmsFactory sms = new SmsFactory();
        ISender sender = sms.produce();
        sender.Send();
    }
}
