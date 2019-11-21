package com.alien.kernel.service.pattern.decorator;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 10:33
 * @Description:
 */
public class SourceImpl implements ISource{
    @Override
    public void sourceMethod() {
        System.out.println("--------this is origin method");
    }
}
