package com.example.demo.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("User with this username already exists in the system!");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
