package com.alien.kernel.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/3 15:23
 * @Description: 日志切面
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义切点
     *
     * @param
     * @return
     */
    @Pointcut("@annotation(com.alien.kernel.dto.annotation.Log)")
    public void LogPointCut() {
    }

    @Pointcut("execution(public * com.alien.kernel.utils.controller.*.*(..))")
    public void otherPointCut() {

    }

    @Before("LogPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object o = joinPoint.getTarget();
        Object o1 = joinPoint.getThis();
        log.info("target={}", o);
        log.info("this={}", o1);
    }
}
