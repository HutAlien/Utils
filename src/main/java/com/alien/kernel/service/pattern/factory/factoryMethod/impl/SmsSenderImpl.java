package com.alien.kernel.service.pattern.factory.factoryMethod.impl;

import com.alien.kernel.service.pattern.factory.factoryMethod.ISender;

/**
 * @Auther: FYJ
 * @Date: 2019/8/27 14:31
 * @Description:
 */
public class SmsSenderImpl implements ISender {
    @Override
    public void Send() {
        System.out.println("Send Sms");
    }
}
