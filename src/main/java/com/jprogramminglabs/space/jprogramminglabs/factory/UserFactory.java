package com.jprogramminglabs.space.jprogramminglabs.factory;

import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;
import com.jprogramminglabs.space.jprogramminglabs.schema.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserFactory {

    public static UserResponse toResponse(Optional<User> user) {
        return user.map(value -> UserResponse.builder()
                .id(value.getId())
                .username(value.getUsername())
                .email(value.getEmail())
                .passwordHash(value.getPasswordHash())
                .createdAt(value.getCreatedAt())
                .updatedAt(value.getUpdatedAt())
                .build()).orElse(null);

    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

    }

    public static List<UserResponse> toResponseList(List<User> userList){
        return userList.stream().map(UserFactory::toResponse).collect(Collectors.toList());
    }
}
