package com.example.libraryserver.business;

import com.example.libraryserver.dataaccess.Book;
import com.example.libraryserver.dataaccess.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@PreAuthorize("hasAnyRole('USER', 'CURATOR', 'ADMIN')")
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> findById(UUID uuid) {
        return bookRepository.findById(uuid);
    }

    @PreAuthorize("hasRole('CURATOR')")
    public Mono<Void> deleteById(UUID uuid) {
        return bookRepository.deleteById(uuid);
    }

    @PreAuthorize("hasRole('CURATOR')")
    public Mono<Void> createBook(Mono<Book> book) {
        return bookRepository.insert(book).then();
    }

    @PreAuthorize("hasRole('CURATOR')")
    public Mono<Book> update(Book book) {
        return bookRepository.save(book);
    }
}
