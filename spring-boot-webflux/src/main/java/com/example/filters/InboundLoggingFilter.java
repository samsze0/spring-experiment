package com.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class InboundLoggingFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(InboundLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("Received request. Request URI: " + request.getURI());
        return chain.filter(exchange);
    }
}