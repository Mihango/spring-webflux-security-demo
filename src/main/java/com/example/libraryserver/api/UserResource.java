package com.example.libraryserver.api;

import com.example.libraryserver.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private UUID id;
    private String email;
    private String firstname;
    private String lastname;
    private List<Role> roles;
}
