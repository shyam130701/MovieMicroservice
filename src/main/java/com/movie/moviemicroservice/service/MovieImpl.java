package com.movie.moviemicroservice.service;

import com.movie.moviemicroservice.dao.BookingRequest;
import com.movie.moviemicroservice.dao.BookingResponse;
import com.movie.moviemicroservice.dao.MovieRequest;
import com.movie.moviemicroservice.dao.MovieResponse;
import com.movie.moviemicroservice.exception.*;
import com.movie.moviemicroservice.model.BookingDetails;
import com.movie.moviemicroservice.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieImpl {

    MovieResponse addMovie(MovieRequest movieRequest) throws MovieAndTheaterAlreadyTakenException;


    List<Movie> getAllList();

    List<Movie> getByMovieName(String name);

    BookingResponse bookingTickets(BookingRequest bookingRequest) throws BookingTicketException, UserNameNotFoundException;

    String deleteMovie(String movieName,String theaterName) throws MovieTheaterNotFoundException, MovieInProgressException;

    String addTicketToMovie(String movieName,String theaterName) throws MovieTheaterNotFoundException;

    List<BookingDetails> getListOfBooking();
}
