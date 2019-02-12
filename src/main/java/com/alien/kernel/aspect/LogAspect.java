package com.alien.kernel.aspect;

import com.alien.kernel.dto.annotation.Log;
import com.alien.kernel.entity.SysLog;
import com.alien.kernel.utils.DateUtils;
import com.alien.kernel.utils.ShiroUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: FengYunJun
 * @Date: 2018/12/3 15:23
 * @Description: 日志切面（分为两部分 1 记录操作信息 2 记录异常信息）
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private Dao dao;

    /**
     * 定义切点
     *
     * @param
     * @return
     */
    @Pointcut("@annotation(com.alien.kernel.dto.annotation.Log)")
    public void LogPointCut() {
    }

    @Pointcut("execution(public * com.alien.kernel.controller.*.*(..))")
    public void otherPointCut() {

    }

    /**
     * 前置增强 用于记录操作
     *
     * @param
     * @return
     */

    @Before("LogPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //获取方法签名
        Method method = methodSignature.getMethod();
        Log Syslog = method.getAnnotation(Log.class);
        String operateType = Syslog.operationType();
        String operateName = Syslog.operationName();
        String paramas = Arrays.toString(joinPoint.getArgs());//对象数组 不能直接toString
        log.info("params={}", paramas);
        String className = joinPoint.getTarget().getClass().getName();//获取目标代理对象类名称
        String methodName = methodSignature.getName();  //获取方法名称
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestIp = request.getRemoteAddr();
        String requestuser = request.getRemoteUser();

        SysLog sysLog = new SysLog();
        sysLog.setLogType("1");
        sysLog.setRequestParams(paramas);
        sysLog.setDescription(operateName);
        sysLog.setOperateType(operateType);
        sysLog.setOperateTime(DateUtils.getCurrentTime());
        sysLog.setMethod(className + "." + methodName);
        sysLog.setOperateUser(ShiroUtils.getCurrentUser().getUsername());
        sysLog.setExceptionCode("正常");
        sysLog.setExceptionDetail("正常");
        dao.insert(sysLog);
    }

    /**
     * 异常信息增强  用于记录API异常信息
     *
     * @param
     * @return
     */

    @AfterThrowing(pointcut = "LogPointCut()", throwing = "e")
    public void doThrowing(JoinPoint joinPoint, Throwable e) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //获取方法签名
        Method method = methodSignature.getMethod();
        Log Syslog = method.getAnnotation(Log.class);
        String operateName = Syslog.operationName();
        String operateType = Syslog.operationType();
        String paramas = Arrays.toString(joinPoint.getArgs());//对象数组 不能直接toString
        String methodName = methodSignature.getName();  //获取方法名称
        String className = joinPoint.getTarget().getClass().getName();//获取目标代理对象类名称

        SysLog sysLog = new SysLog();
        sysLog.setLogType("2");
        sysLog.setDescription(operateName);
        sysLog.setRequestParams(paramas);
        sysLog.setOperateType(operateType);
        sysLog.setOperateTime(DateUtils.getCurrentTime());
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setOperateUser(ShiroUtils.getCurrentUser().getUsername());
        sysLog.setExceptionCode(e.getClass().getName());
        sysLog.setExceptionDetail(e.getMessage());
        dao.insert(sysLog);
    }
}
