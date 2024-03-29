package com.example.libraryserver.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<String>> handle(RuntimeException e) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.error(e.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handle(Exception e) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.error(e.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
