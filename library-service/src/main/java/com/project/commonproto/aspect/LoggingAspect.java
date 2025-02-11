package com.project.commonproto.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.project.commonproto.controller..*(..))")
    public Object logRequestsAndResponses(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("Incoming request: {} with args: {}", methodName, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            log.error("Exception in method: {} - Message: {}", methodName, ex.getMessage(), ex);
            throw ex;
        }

        if (result instanceof ResponseEntity) {
            ResponseEntity<?> response = (ResponseEntity<?>) result;
            log.info("Response from {}: Status = {} Body = {}", methodName, response.getStatusCode(), response.getBody());
        } else {
            log.info("Response from {}: {}", methodName, result);
        }

        return result;
    }
}
