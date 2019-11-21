package com.alien.kernel.service.pattern.factory.StaFactoryMethod.impl;

import com.alien.kernel.service.pattern.factory.StaFactoryMethod.ISend;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 9:56
 * @Description:
 */
public class SendMialImpl implements ISend {

    @Override
    public void send() {
        System.out.println("send mail-------------");
    }
}
