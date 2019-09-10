package com.alien.kernel.service.pattern.factory.factoryMethod.impl;

import com.alien.kernel.service.pattern.factory.factoryMethod.ISender;
import com.alien.kernel.service.pattern.factory.factoryMethod.Provider;

/**
 * @Auther: FYJ
 * @Date: 2019/8/27 14:38
 * @Description:
 */
public class MailFactory implements Provider {
    @Override
    public ISender produce() {
        return new MailSenderImpl();
    }
}
