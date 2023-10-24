package com.ssafy.member.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.ssafy.*..*.*(..))")
    private void cut(){}

    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        log.debug("------> Method = {} Start", joinPoint.getStaticPart());

        Object[] values = joinPoint.getArgs();
        String[] names = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        for (int i=0; i<names.length; i++){
            log.info("- parameter {} = {}",names[i],values[i]);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObject")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObject) {

        log.info("- return {}",returnObject);
        log.debug("------> Method {} End", joinPoint.getStaticPart());
    }

}
