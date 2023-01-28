package com.example.dataclean.user;

public class UserAlreadyExitException extends Throwable {

    public UserAlreadyExitException(String s) {
        super(s);
    }
}
