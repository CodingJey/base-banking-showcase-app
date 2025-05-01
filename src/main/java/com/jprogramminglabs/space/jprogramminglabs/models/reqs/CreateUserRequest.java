package com.jprogramminglabs.space.jprogramminglabs.models.reqs;

import lombok.Data;

@Data
public final class CreateUserRequest {
    @jakarta.validation.constraints.NotBlank
    private String username;

    @jakarta.validation.constraints.Email
    @jakarta.validation.constraints.NotBlank
    private String email;

    @jakarta.validation.constraints.NotBlank
    private String passwordHash;
}