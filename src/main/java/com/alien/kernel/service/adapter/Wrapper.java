package com.alien.kernel.service.adapter;

import org.junit.jupiter.api.Test;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/15 16:29
 * @Description:
 */
public class Wrapper implements ITargetTable {
    private SourceClass sourceClass;

    public Wrapper(SourceClass sourceClass) {
        super();
        this.sourceClass = sourceClass;
    }

    @Override
    public void method() {
        sourceClass.method();
    }

    @Override
    public void method1() {
        System.out.println("this is target method1");
    }

    @Test
    public void WrapperTest() {
        SourceClass sourceClass = new SourceClass();
        //ITargetTable targetTable = new Wrapper(sourceClass);
        // targetTable.method();
        //targetTable.method1();
    }

}
