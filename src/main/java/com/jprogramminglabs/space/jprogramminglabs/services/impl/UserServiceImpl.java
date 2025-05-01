package com.jprogramminglabs.space.jprogramminglabs.services.impl;

import com.jprogramminglabs.space.jprogramminglabs.factory.UserFactory;
import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;
import com.jprogramminglabs.space.jprogramminglabs.repositories.UserRepository;
import com.jprogramminglabs.space.jprogramminglabs.schema.User;
import com.jprogramminglabs.space.jprogramminglabs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(String username, String email, String passwordHash) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPasswordHash(passwordHash);
        Optional<User> saved = Optional.of(userRepository.save(u));

        return UserFactory.toResponse(saved);
    }

    @Override
    public UserResponse retrieveUserByID(Long id) {
        return userRepository.findById(id)
                .map(UserFactory::toResponse)
                .orElse(null);
    }

    @Override
    public List<UserResponse> retrieveAllUsers() {
        return UserFactory.toResponseList(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, String username, String email) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setUsername(username);
                    u.setEmail(email);
                    return UserFactory.toResponse(Optional.of(userRepository.save(u)));
                })
                .orElse(null);
    }

    @Override
    @Transactional
    public void softDeleteUser(Long id) {
        // use our custom soft-delete query
        userRepository.softDeleteById(id);
    }
}
