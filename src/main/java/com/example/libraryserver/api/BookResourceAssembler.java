package com.example.libraryserver.api;

import com.example.libraryserver.dataaccess.Book;
import com.example.libraryserver.dataaccess.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

@Component
public class BookResourceAssembler {

    private final ModelMapper modelMapper;
    private final IdGenerator idGenerator;

    public BookResourceAssembler(ModelMapper modelMapper, IdGenerator idGenerator) {
        this.modelMapper = modelMapper;
        this.idGenerator = idGenerator;
    }

    public BookResource toResource(Book book) {
        User borrowedBy = book.getBorrowedBy();

        BookResource bookResource = modelMapper.map(book, BookResource.class);

        if(borrowedBy != null) {
            bookResource.setBorrowedBy(modelMapper.map(borrowedBy, UserResource.class));
        }

        return bookResource;
    }

    public Book toModel(BookResource bookResource) {
        UserResource borrowedBy = bookResource.getBorrowedBy();
        Book book = modelMapper.map(bookResource, Book.class);
        if(borrowedBy != null) {
            book.doBorrow(modelMapper.map(borrowedBy, User.class));
        }

        if(book.getId() == null) {
            book.setId(idGenerator.generateId());
        }

        return book;
    }
}
