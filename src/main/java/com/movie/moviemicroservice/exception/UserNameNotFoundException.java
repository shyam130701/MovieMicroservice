package com.movie.moviemicroservice.exception;

public class UserNameNotFoundException extends  Exception{
    public UserNameNotFoundException(String message) {
        super(message);
    }
}
