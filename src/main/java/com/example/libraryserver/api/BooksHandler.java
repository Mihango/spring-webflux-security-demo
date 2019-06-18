package com.example.libraryserver.api;

import com.example.libraryserver.business.BookService;
import com.example.libraryserver.dataaccess.Book;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Validated
public class BooksHandler {

    private BookService bookService;
    private BookResourceAssembler bookAssembler;

    public BooksHandler(BookService bookService, BookResourceAssembler bookAssembler) {
        this.bookService = bookService;
        this.bookAssembler = bookAssembler;
    }

    public Mono<ServerResponse> getAllBooks(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(bookService.findAll().map(bookAssembler::toResource), BookResource.class);
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        return bookService
                .findById(UUID.fromString(request.pathVariable("bookId")))
                .map(bookAssembler::toResource)
                .flatMap(bookResource ->
                        ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(BodyInserters.fromObject(bookResource)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return ok().build(
                bookService.createBook(request.bodyToMono(BookResource.class).map(bookAssembler::toModel))
        );
    }
}
