package com.acme.mindflicks.platform.users.interfaces.rest;


import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByUserNameAndPassword;
import com.acme.mindflicks.platform.users.domain.services.UserCommandService;
import com.acme.mindflicks.platform.users.domain.services.UserQueryService;
import com.acme.mindflicks.platform.users.interfaces.rest.resources.UserResource;
import com.acme.mindflicks.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.acme.mindflicks.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.mindflicks.platform.users.interfaces.rest.resources.CreateUserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }


    @Operation(
            summary = "Create a user",
            description = "Create a user with the provided user data"
    )
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping
    public ResponseEntity<UserResource> createUser(CreateUserResource resource){
        Optional<User> user =  userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return user.map(u -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(u), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }



    @Operation(
            summary = "Get a user by ID",
            description = "Get a user by the provided user ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResource>getUserById(@PathVariable Long id){
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(u->ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Sign in",
            description = "Get a user by the provided username and password"
    )
    @GetMapping
    public ResponseEntity<UserResource>getUserByUserNameAndPassword( String userName,
                                                                     String password){
        Optional<User> user = userQueryService.handle(new GetUserByUserNameAndPassword(userName, password));
        return user.map(u->ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
