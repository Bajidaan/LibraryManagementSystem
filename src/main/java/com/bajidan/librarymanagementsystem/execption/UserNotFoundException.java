package com.bajidan.librarymanagementsystem.execption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
