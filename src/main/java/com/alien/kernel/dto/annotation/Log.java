package com.alien.kernel.dto.annotation;

import java.lang.annotation.*;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/3 15:20
 * @Description: 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    //操作类型
    String operationType() default "";

    //操作名称
    String operationName() default "";
}
