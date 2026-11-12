package com.university.aoplab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // Reusable pointcut
    @Pointcut("execution(* com.university.aoplab.service.*.*(..))")
    public void serviceMethods() {}

    //  BEFORE
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("\n========== METHOD STARTED ==========");
        System.out.println("Class: " +
                joinPoint.getTarget().getClass().getSimpleName());
        System.out.println("Method: " +
                joinPoint.getSignature().getName());
        System.out.println("Arguments: " +
                Arrays.toString(joinPoint.getArgs()));
    }

    //  AFTER RETURNING
    @AfterReturning(
            pointcut = "serviceMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("Method completed successfully: " +
                joinPoint.getSignature().getName());

        System.out.println("Return value: " + result);
    }

    //  AFTER THROWING
    @AfterThrowing(
            pointcut = "serviceMethods()",
            throwing = "exception"
    )
    public void logAfterThrowing(
            JoinPoint joinPoint,
            Throwable exception) {

        System.out.println("Method failed: " +
                joinPoint.getSignature().getName());

        System.out.println("Exception: " + exception.getMessage());
    }

    //  AFTER
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("Method execution finished: " +
                joinPoint.getSignature().getName());

        System.out.println("====================================\n");
    }
}