package com.alien.kernel.service.pattern.factory.StaFactoryMethod.impl;

import com.alien.kernel.service.pattern.factory.StaFactoryMethod.ISend;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 9:55
 * @Description:
 */
public class SendSmsImpl implements ISend {

    @Override
    public void send() {
        System.out.println("send sms-----------");
    }
}
