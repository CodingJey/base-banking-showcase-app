package com.jprogramminglabs.space.jprogramminglabs.controllers.impl;

import com.jprogramminglabs.space.jprogramminglabs.models.reqs.CreateUserRequest;
import com.jprogramminglabs.space.jprogramminglabs.models.reqs.UpdateUserRequest;
import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;
import com.jprogramminglabs.space.jprogramminglabs.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor       // Lombok generates a constructor for final fields
public class UserControllerImpl {

    @Autowired
    private UserService userService;

    /** Create new user */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest req) {
        return userService.createUser(
                req.getUsername(),
                req.getEmail(),
                req.getPasswordHash()
        );
    }

    /** Get all non-deleted users */
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    /** Get single user by ID */
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.retrieveUserByID(id);
    }

    /** Update an existing user */
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest req
    ) {
        return userService.updateUser(
                id,
                req.getUsername(),
                req.getEmail()
        );
    }

    /** Soft-delete a user */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
    }


}
