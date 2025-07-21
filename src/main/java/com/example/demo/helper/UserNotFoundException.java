package com.example.demo.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User with this username not found in database! Try with another username.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
