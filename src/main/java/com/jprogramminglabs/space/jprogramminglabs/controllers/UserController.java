package com.jprogramminglabs.space.jprogramminglabs.controllers;

import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;

public interface UserController {
    UserResponse retrieveUserById(Long id);
}
