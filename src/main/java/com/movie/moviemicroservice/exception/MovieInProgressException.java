package com.movie.moviemicroservice.exception;

public class MovieInProgressException extends Exception{
    public MovieInProgressException(String message) {
        super(message);
    }
}
