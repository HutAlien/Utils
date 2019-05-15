package com.alien.kernel.service.bridge.impl;

import com.alien.kernel.service.bridge.ISourceable;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/15 16:13
 * @Description:
 */
public class SourceSub2 implements ISourceable {

    @Override
    public void method() {
        System.out.println("this is Sub2 method");
    }
}
