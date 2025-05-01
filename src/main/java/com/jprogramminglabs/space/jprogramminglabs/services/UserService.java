package com.jprogramminglabs.space.jprogramminglabs.services;

import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse createUser(String username, String email, String passwordHash);

    UserResponse retrieveUserByID(Long id);

    List<UserResponse> retrieveAllUsers();

    UserResponse updateUser(Long id, String username, String email);

    void softDeleteUser(Long id);
}

