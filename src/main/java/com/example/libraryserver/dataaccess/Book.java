package com.example.libraryserver.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Book {
    @Id private UUID id;
    @Indexed(unique = true)
    private String isbn;

    @Indexed private String title;

    @TextIndexed private String description;

    private List<String> authors;

    private boolean borrowed;

    private User borrowedBy;

    public void doBorrow(User user) {
        if(!this.borrowed) {
            this.borrowed = true;
            this.borrowedBy = user;
        }
    }

    public void doReturn(User user) {
        if(this.borrowedBy.getId() == user.getId()) {
            if(this.borrowed) {
                this.borrowed = false;
                this.borrowedBy = null;
            }
        } else {
            // throw error
        }
    }
}
