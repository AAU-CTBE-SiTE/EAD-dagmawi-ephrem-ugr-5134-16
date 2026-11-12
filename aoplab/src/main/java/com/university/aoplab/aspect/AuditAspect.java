package com.university.aoplab.aspect;

import com.university.aoplab.annotation.Auditable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class AuditAspect {

    @Around("@annotation(auditable)")
    public Object auditOperation(
            ProceedingJoinPoint joinPoint,
            Auditable auditable) throws Throwable {

        String operation = auditable.value();

        System.out.println(
                "[AUDIT] Starting operation: " + operation
        );

        try {

            Object result = joinPoint.proceed();

            System.out.println(
                    "[AUDIT] Operation successful: " + operation
            );

            return result;

        } catch (Throwable exception) {

            System.out.println(
                    "[AUDIT] Operation failed: " + operation
            );

            throw exception;
        }
    }
}