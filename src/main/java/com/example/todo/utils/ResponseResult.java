package com.example.todo.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize
public class ResponseResult {

    private Object data;

    private boolean success;

    ResponseResult(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }
}