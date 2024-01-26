package com.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ApiKeyInterceptor implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(ApiKeyInterceptor.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        boolean validApiKey = exchange.getRequest().getHeaders().containsKey("api-key") && 
                              exchange.getRequest().getHeaders().getFirst("api-key").equals(System.getenv("API_KEY"));
        if (!validApiKey) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 401
            return response.writeWith(Mono.just(response.bufferFactory().wrap("Invalid API key".getBytes())));
        }
        return chain.filter(exchange).doFinally(signalType -> {
            logger.info("Returning response after validated API key");
            logger.info("Response status: " + exchange.getResponse().getStatusCode());
        });
    }
}