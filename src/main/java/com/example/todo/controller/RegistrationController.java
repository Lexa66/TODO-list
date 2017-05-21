package com.example.todo.controller;

import com.example.todo.utils.ResponseResult;
import com.example.todo.model.User;
import com.example.todo.service.UserService;
import com.example.todo.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseResult registration(@RequestBody User userInfo) {

        if (userService.isLoginExist(userInfo.getUsername())) {
            log.info("login already exist");
            return ResponseBuilder.fail("Login already exist");
        }

        userService.createUser(userInfo);

        return ResponseBuilder.success("User is successfully created");
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
