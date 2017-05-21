package com.example.todo.exception;

import org.springframework.dao.DataAccessException;

public class UserRepositoryException extends DataAccessException {

    public UserRepositoryException(String msg) {
        super(msg);
    }
}
