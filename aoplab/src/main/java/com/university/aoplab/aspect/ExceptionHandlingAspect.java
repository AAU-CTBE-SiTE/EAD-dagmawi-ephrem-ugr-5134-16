package com.university.aoplab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class ExceptionHandlingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.university.aoplab.service.*.*(..))",
            throwing = "exception"
    )
    public void monitorExceptions(
            JoinPoint joinPoint,
            Throwable exception) {

        System.err.println("\n[ERROR MONITOR]");

        System.err.println(
                "Method: " + joinPoint.getSignature().getName()
        );

        System.err.println(
                "Error: " + exception.getMessage()
        );
    }
}