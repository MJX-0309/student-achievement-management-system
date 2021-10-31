package edu.zhku.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author MJX
 * @date 2021/10/31
 */
@Slf4j
@Aspect
@Component
public class ServiceLogAspect {
    private final ThreadLocal<Long> threadLocal= new ThreadLocal<>();

    @Around("execution(* edu.zhku.boot.controller..*.*(..))")
    public Object serviceLog(ProceedingJoinPoint pjp) throws Throwable{
        threadLocal.set(System.currentTimeMillis());
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.warn("执行 {} {} 花费了 {} 毫秒",pjp.getTarget().getClass(),pjp.getSignature().getName(),end-threadLocal.get());
        return proceed;
    }
}
