package com.example.todo.service;

import com.example.todo.enums.AuthorityName;
import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {

        user.setRole(AuthorityName.ROLE_USER);

        userRepository.save(user);
    }

    public Boolean isLoginExist(String login) {
        return (userRepository.findByUsername(login) != null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public void removeUser(Long id) {
        User user = getUserById(id);
        if (user.getUsername().equalsIgnoreCase(getAuthenticatedUser().getUsername())) {
            throw new RuntimeException("You cannot delete your account");
        } else {
            userRepository.delete(user);
        }
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    public void updateUser(User update) {
        if (!Objects.equals(userRepository.findByUsername(update.getUsername()).getId(), update.getId())) {
            throw new RuntimeException("Username already exist");
        } else {
            userRepository.save(update);
        }
    }
}


