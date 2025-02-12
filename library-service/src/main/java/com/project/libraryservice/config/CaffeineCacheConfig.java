package com.project.libraryservice.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Set;

@Configuration
public class CaffeineCacheConfig {

    @Bean
    public Cache<String, Set<Integer>> requestIdCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofDays(1))
                .build();
    }
}

