package com.example.libraryserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LogWebFilter implements WebFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(LogWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        LOGGER.info("Request {} called", exchange.getRequest().getPath().value());
        return chain.filter(exchange);
    }
}
