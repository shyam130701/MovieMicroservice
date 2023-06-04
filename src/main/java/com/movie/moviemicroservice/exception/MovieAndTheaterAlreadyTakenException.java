package com.movie.moviemicroservice.exception;

public class MovieAndTheaterAlreadyTakenException extends Exception{
    public MovieAndTheaterAlreadyTakenException(String message) {
        super(message);
    }
}
