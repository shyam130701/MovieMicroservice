package com.movie.moviemicroservice.exception;

public class MovieTheaterNotFoundException extends  Exception{
    public MovieTheaterNotFoundException(String message) {
        super(message);
    }
}
