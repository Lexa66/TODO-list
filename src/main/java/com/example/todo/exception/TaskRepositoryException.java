package com.example.todo.exception;

import org.springframework.dao.DataAccessException;

public class TaskRepositoryException extends DataAccessException {

    public TaskRepositoryException(String msg) {
        super(msg);
    }
}
