package com.henryschein.userservicetest;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.henryschein.userservice.controller.UserController;
import com.henryschein.userservice.model.User;
import com.henryschein.userservice.repository.UserRepository;
import com.henryschein.userservice.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private User sampleUser;

    @Before
    public void setUp() {
        // Sample user for testing
        sampleUser = new User();
        sampleUser.setId(1);
        sampleUser.setUserName("testuser");
        sampleUser.setEmail("test@example.com");
        sampleUser.setPassword("password");
        sampleUser.setActive(true);
        sampleUser.setRoles("ROLE_USER");
    }

    @Test
    public void testCreateUser() {
        // Setup behavior of userService mock
        when(userService.createUser(any(User.class))).thenReturn(sampleUser);

        // Call the method being tested
        User createdUser = userController.createUser(sampleUser);

        // Assertions
        assertEquals(sampleUser, createdUser);
    }

    @Test
    public void testGetUserById() {
        // Setup behavior of userService mock
        when(userService.getUserById(1)).thenReturn(Optional.of(sampleUser));

        // Call the method being tested
        User retrievedUser = userController.getUserById(1);

        // Assertions
        assertEquals(sampleUser, retrievedUser);
    }

    @Test
    public void testUpdateUser() {
        // Setup behavior of userService mock
        when(userService.updateUser(any(User.class))).thenReturn(sampleUser);

        // Call the method being tested
        User updatedUser = userController.updateUser(sampleUser);

        // Assertions
        assertEquals(sampleUser, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        // Setup behavior of userService mock
        when(userService.deleteUser(1)).thenReturn("User removed successfully!1");

        // Call the method being tested
        String result = userController.deleteUser(1);

        // Assertions
        assertEquals("User removed successfully!1", result);
    }

    // Similarly, you can add test methods for other controller methods like createUsers, getUsers, deleteUser, etc.
}
