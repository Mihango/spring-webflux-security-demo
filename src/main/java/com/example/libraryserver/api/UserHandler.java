package com.example.libraryserver.api;

import com.example.libraryserver.business.UserService;
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
public class UserHandler {

    private final UserService userService;
    private final UserResourceAssembler userResourceAssembler;

    public UserHandler(UserService userService, UserResourceAssembler userResourceAssembler) {
        this.userService = userService;
        this.userResourceAssembler = userResourceAssembler;
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userService.findAll().map(userResourceAssembler::toResource), UserResource.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        return userService
                .findById(UUID.fromString(serverRequest.pathVariable("userId")))
                .map(userResourceAssembler::toResource)
                .flatMap(userResource ->
                        ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(BodyInserters.fromObject(userResource)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return ok().build(userService.deleteById(UUID.fromString(serverRequest.pathVariable("userId"))));
    }

    public Mono<ServerResponse> creteUser(ServerRequest serverRequest) {
        return ok().build(
                userService.create(
                        serverRequest.bodyToMono(CreateUserResource.class).map(userResourceAssembler::toModel)
                )
        );
    }
}
