package com.university.aoplab.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class PerformanceAspect {

    @Around("execution(* com.university.aoplab.service.*.*(..))")
    public Object measureExecutionTime(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.nanoTime();

        try {

            // Execute the actual target method
            Object result = joinPoint.proceed();

            return result;

        } finally {

            long end = System.nanoTime();

            double duration = (end - start) / 1_000_000.0;

            System.out.println(
                    "[PERFORMANCE] " +
                            joinPoint.getSignature().getName() +
                            " executed in " + duration + " ms"
            );
        }
    }
}