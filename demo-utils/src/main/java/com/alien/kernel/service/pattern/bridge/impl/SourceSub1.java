package com.alien.kernel.service.pattern.bridge.impl;

import com.alien.kernel.service.pattern.bridge.ISourceable;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/15 16:11
 * @Description:
 */
public class SourceSub1 implements ISourceable {
    @Override
    public void method() {
        System.out.println("this is sub1 method");
    }
}
