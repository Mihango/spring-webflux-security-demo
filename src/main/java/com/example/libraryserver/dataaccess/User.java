package com.example.libraryserver.dataaccess;

import com.example.libraryserver.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id private UUID id;

    @Indexed private String email;

    private String password;

    private String firstName;

    private String lastName;

    private List<Role> roles;

    public User(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getRoles());
    }
}
