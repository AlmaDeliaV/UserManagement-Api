package com.ar.usermanagementapi;

import com.ar.usermanagementapi.api.models.User;
import com.ar.usermanagementapi.exception.userException;
import com.ar.usermanagementapi.service.UserService;
import com.ar.usermanagementapi.service.UserServiceInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceInMemory();
    }

    @Test
    void testAddUserSuccessfully() {
        User newUser = new User(100, "user1", "user1@example.com", "password123");
        userService.addUser(newUser);

        User retrieved = userService.getUser(100);
        assertNotNull(retrieved);
        assertEquals("user1", retrieved.getName());
    }

    @Test
    void testAddUserWithNullPasswordThrowsException() {
        User invalidUser = new User(200, "user2", "user2@example.com", null);

        Exception exception = assertThrows(userException.class, () -> {
            userService.addUser(invalidUser);
        });

        assertTrue(exception.getMessage().contains("Password empty"));
    }

    @Test
    void testAddUserDuplicateIdThrowsException() {
        User user1 = new User(1, "User1", "user1@example.com", "1234");

        Exception exception = assertThrows(userException.class, () -> {
            userService.addUser(user1);
        });

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testGetUser() {
        User user = userService.getUser(1L);
        assertNotNull(user);
    }
}
