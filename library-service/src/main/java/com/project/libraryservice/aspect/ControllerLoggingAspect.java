package com.project.libraryservice.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerLoggingAspect {

    private final Cache<String, Set<Integer>> requestIdCache;
    private final ObjectMapper objectMapper;

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logRequestsAndResponses(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if (!(request instanceof ContentCachingRequestWrapper)) {
            log.warn("Request is not wrapped with ContentCachingRequestWrapper! Ensure RequestCachingFilter is applied.");
            return joinPoint.proceed(); // Continue execution
        }

        ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;

        String requestURI = cachingRequest.getRequestURI();
        String method = cachingRequest.getMethod();
        String userAgent = cachingRequest.getHeader("User-Agent");
        String remoteAddr = cachingRequest.getRemoteAddr();

        Map<String, String> headers = Collections.list(cachingRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(header -> header, cachingRequest::getHeader));

        String requestNoHeader = headers.get("request-no");
        if (requestNoHeader != null) {
            try {
                int requestNo = Integer.parseInt(requestNoHeader);
                ensureUniqueRequestId(requestNo); // Ensure the requestNo is unique for the day
            } catch (NumberFormatException e) {
                log.error("Invalid requestNo format: {}", requestNoHeader);
                throw new IllegalArgumentException("Invalid requestNo format: " + requestNoHeader);
            }
        } else {
            log.error("Missing Mandatory requestNo header in the request!");
            throw new IllegalArgumentException("Missing Mandatory requestNo header in the request!");
        }

        Map<String, List<String>> queryParams = cachingRequest.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        element -> Arrays.asList(element.getValue())));

        String requestBody = extractRequestBody(cachingRequest);

        log.info("Request Received With Details: Method: {} URI: {} User-Agent: {} Headers: {} Remote Address: {} Query Parameters: {} Body: {}",
                method, requestURI, userAgent, headers, remoteAddr, queryParams, requestBody);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            log.error("Exception in method {}: {}", joinPoint.getSignature().toShortString(), ex.getMessage(), ex);
            throw ex;
        }

        log.info("Response Completed with request-no {}: and body {}", requestNoHeader, objectMapper.writeValueAsString(result));

        return result;
    }


    private String extractRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        return content.length > 0 ? new String(content, StandardCharsets.UTF_8) : "No Body!";
    }

    private void ensureUniqueRequestId(int requestId) {
        // Generate the cache key as today's date
        String today = LocalDate.now().toString();
        // Compute and update the cache for today's date
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

