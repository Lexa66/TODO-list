package com.example.todo.configuration;

import com.example.todo.enums.AuthorityName;
import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {
    private final UserRepository userRepository;

    @Autowired
    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        User user = new User();
        user.setName("User");
        user.setUsername("User");
        user.setPassword("User");
        user.setRole(AuthorityName.ROLE_USER);

        userRepository.save(user);

        User admin = new User();
        admin.setName("Admin");
        admin.setUsername("Admin");
        admin.setPassword("Admin");
        admin.setRole(AuthorityName.ROLE_ADMIN);

        userRepository.save(admin);
    }
}
