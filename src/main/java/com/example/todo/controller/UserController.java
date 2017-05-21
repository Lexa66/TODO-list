package com.example.todo.controller;

import com.example.todo.utils.ResponseResult;
import com.example.todo.exception.UserRepositoryException;
import com.example.todo.model.User;
import com.example.todo.service.UserService;
import com.example.todo.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    public ResponseResult getUser(@PathVariable("userId") Long id) {
        try {
            return ResponseBuilder.success(userService.getUserById(id));
        } catch (UserRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseResult deleteUser(@PathVariable("userId") Long id) {
        try {
            userService.removeUser(id);
            return ResponseBuilder.success("User successfully removed");

        } catch (UserRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseResult createUser(@RequestBody User user) {
        if (userService.isLoginExist(user.getUsername())) {
            log.info("login already exist");
            return ResponseBuilder.fail("Login already exist");
        }
        userService.createUser(user);

        return ResponseBuilder.success("User is successfully created");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseResult updateUser(@RequestBody User update) {
        try {
            userService.updateUser(update);
            log.info("Update user " + update.getName());

            return ResponseBuilder.success("User %s successfully updated", update.getName());
        } catch (UserRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}