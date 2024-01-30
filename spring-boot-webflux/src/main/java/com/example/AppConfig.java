package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

import com.example.filters.ApiKeyInterceptor;
import com.example.filters.InboundLoggingFilter;

@Configuration
public class AppConfig {
    @Bean
    public InboundLoggingFilter inboundLoggingFilter() {
        return new InboundLoggingFilter();
    }

    @Bean
    public WebFilter apiKeyInterceptorWrapper() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            if (!path.startsWith("/manage/")) {
                return new ApiKeyInterceptor().filter(exchange, chain);
            }
            return chain.filter(exchange);
        };
    }
}