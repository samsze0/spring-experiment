package com.example.filters;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
public class ApiKeyInterceptor implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String apiKey = exchange.getRequest().getHeaders().getFirst("api-key");
        boolean validApiKey = apiKey != null && apiKey.equals(System.getenv("API_KEY"));
        if (!validApiKey) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 401
            return response.writeWith(Mono.just(response.bufferFactory().wrap("Invalid API key".getBytes())));
        }
        return chain.filter(exchange).doFinally(signalType -> {
            log.info("Returning response after validated API key");
            log.info("Response status: " + exchange.getResponse().getStatusCode());
        });
    }
}