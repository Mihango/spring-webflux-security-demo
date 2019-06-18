package com.example.libraryserver.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResource {
    private UUID id;
    @NotNull private String isbn;
    @NotNull private String description;
    @NotNull private List<String> authors;
    private boolean borrowed;
    private UserResource borrowedBy;
}
