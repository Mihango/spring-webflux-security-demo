package com.example.libraryserver.api;

import com.example.libraryserver.common.Role;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateUserResource extends UserResource {

    private String password;

    public CreateUserResource() {
    }

    public CreateUserResource(UUID uuid, String email, String password, String firstname, String lastname, List<Role> roles) {
        super(uuid, email, firstname, lastname, roles);
        this.password = password;
    }
}
