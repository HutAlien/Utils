package com.alien.kernel.service.adapter;

import org.junit.jupiter.api.Test;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/15 16:24
 * @Description:
 */
public class Adapter extends SourceClass implements ITargetTable {
    @Override
    public void method1() {
        System.out.println("this is target method1");
    }

    @Test
    public void AdapterTest() {
        ITargetTable targetTable = new Adapter();
        targetTable.method();
        targetTable.method1();
    }
}
