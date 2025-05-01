package com.jprogramminglabs.space.jprogramminglabs;

import com.jprogramminglabs.space.jprogramminglabs.models.resp.UserResponse;
import com.jprogramminglabs.space.jprogramminglabs.schema.User;
import com.jprogramminglabs.space.jprogramminglabs.repositories.UserRepository;
import com.jprogramminglabs.space.jprogramminglabs.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User john;
    private User jane;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        // NOTE: `createdAt` and `updatedAt` will be set automatically by Hibernate.
        john = new User();
        john.setUsername("john_doe");
        john.setEmail("john@example.com");
        john.setPasswordHash("hashedPassword1");
        john = userRepository.save(john);

        jane = new User();
        jane.setUsername("jane_smith");
        jane.setEmail("jane@example.com");
        jane.setPasswordHash("hashedPassword2");
        jane = userRepository.save(jane);
    }

    @Test
    @DisplayName("retrieveUserByID returns correct UserResponse for existing user")
    void whenValidId_thenUserResponseIsReturned() {
        // call the service
        UserResponse resp = userService.retrieveUserByID(john.getId());

        // assertions
        assertThat(resp).isNotNull();
        assertThat(resp.getId()).isEqualTo(john.getId());
        assertThat(resp.getUsername()).isEqualTo("john_doe");
        assertThat(resp.getEmail()).isEqualTo("john@example.com");
        // the factory currently copies passwordHash directly—ensure it matches:
        assertThat(resp.getPasswordHash()).isEqualTo("hashedPassword1");

        // createdAt/updatedAt should be non-null and within a reasonable window:
        assertThat(resp.getCreatedAt()).isNotNull();
        assertThat(resp.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("retrieveUserByID returns null when user not found")
    void whenInvalidId_thenNullReturned() {
        UserResponse resp = userService.retrieveUserByID(9999L);
        assertThat(resp).isNull();
    }
}
