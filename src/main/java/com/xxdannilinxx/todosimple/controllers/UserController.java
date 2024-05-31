package com.xxdannilinxx.todosimple.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.models.User.CreateUser;
import com.xxdannilinxx.todosimple.models.User.UpdateUser;
import com.xxdannilinxx.todosimple.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Validated
@Tag(name = "User", description = "Endpoints for users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Fetch user", description = "Returns a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> findById(@PathVariable("id") Long id) {
        User user = this.userService.findById(id);

        return ResponseEntity.ok().body(user.toString());
    }

    @PostMapping("/")
    @Validated(CreateUser.class)
    @Operation(summary = "Create user", description = "Creates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> create(@Valid @RequestBody User u) {
        User user = this.userService.create(u);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{id}")
    @Validated(UpdateUser.class)
    @Operation(summary = "Update user", description = "Updates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        user.setId(id);
        this.userService.update(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
