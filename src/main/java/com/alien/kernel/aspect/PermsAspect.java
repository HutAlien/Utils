package com.alien.kernel.aspect;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.dto.annotation.Permits;
import com.alien.kernel.utils.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Auther: FengYunJun
 * @Date: 2019/2/11 16:37
 * @Description: AOP 权限拦截切面
 */
@Aspect
@Component
public class PermsAspect {

    @Pointcut("@annotation(com.alien.kernel.dto.annotation.Permits)")//拦截controller下的所有类
    public void permsPointCut() {
    }

    /**
     * 权限环绕通知
     *
     * @param joinPoint
     * @return object
     */

    @Around("permsPointCut()")
    public Object doPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();//获取方法签名
        Method method = methodSignature.getMethod();
        Permits permits = method.getAnnotation(Permits.class);
        if (permits == null) {
            return joinPoint.proceed();
        } else {
            String[] roles = permits.roles().split(",");
            String currentRole = ShiroUtils.getCurrentRole();
            for (String role : roles) {
                if (currentRole.equals(role)) {
                    return joinPoint.proceed();
                }
            }
            return new AjaxResult(AjaxCode.FAILURE, "没有访问权限");
        }
    }
}
