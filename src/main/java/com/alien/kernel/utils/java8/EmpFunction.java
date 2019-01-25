package com.alien.kernel.utils.java8;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/24 09:41
 * @Description:
 */
@FunctionalInterface
public interface EmpFunction<R, R1, R2, R3, T> {
    T get(R r, R1 r1, R2 r2, R3 r3);
}
