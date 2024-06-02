package com.xxdannilinxx.todosimple.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(com.xxdannilinxx.todosimple.log.Log)")
    public Object automatic(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Entering " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        System.out.println("Exiting " + pjp.getSignature().getName());
        return result;
    }
}
