package com.xxdannilinxx.todosimple.controllers;

public class ConflictException extends RuntimeException {

    private final int status;
    private final String message;

    public ConflictException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
