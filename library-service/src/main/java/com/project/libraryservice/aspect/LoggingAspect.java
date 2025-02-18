package com.project.libraryservice.aspect;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final Cache<String, Set<Integer>> requestIdCache;

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
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

    private void ensureUniqueRequestId(int requestId) {
        String today = LocalDate.now().toString();
        requestIdCache.asMap().compute(today, (key, requestIds) -> {
            if (requestIds == null) {
                requestIds = new HashSet<>();
            }
            if (!requestIds.add(requestId)) {
                log.error("Duplicate requestId: {} for today", requestId);
                throw new IllegalArgumentException("Duplicate requestId: " + requestId + " for today");
            }
            return requestIds;
        });
    }
}

