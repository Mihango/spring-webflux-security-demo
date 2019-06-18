package com.example.libraryserver.business;

import com.example.libraryserver.dataaccess.User;
import com.example.libraryserver.dataaccess.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public Mono<User> findUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public Mono<Void> create(Mono<User> user) {
        return userRepository.insert(user).then();
    }

    public Mono<User> update(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<Void> deleteById(UUID uuid) {
        return userRepository.deleteById(uuid);
    }

}
