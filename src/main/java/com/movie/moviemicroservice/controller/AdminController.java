package com.movie.moviemicroservice.controller;

import com.movie.moviemicroservice.dao.MovieRequest;
import com.movie.moviemicroservice.dao.MovieResponse;
import com.movie.moviemicroservice.exception.MovieAndTheaterAlreadyTakenException;
import com.movie.moviemicroservice.exception.MovieInProgressException;
import com.movie.moviemicroservice.exception.MovieTheaterNotFoundException;
import com.movie.moviemicroservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private MovieService movieService;

    @PostMapping("/addMovie")

    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) throws MovieAndTheaterAlreadyTakenException {
        MovieResponse movieResponse=movieService.addMovie(movieRequest);
        return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete")
    public String deletingMovie(@RequestParam("movieName") String movieName,@RequestParam("theaterName") String theaterName ) throws MovieInProgressException, MovieTheaterNotFoundException {
        return movieService.deleteMovie(movieName,theaterName);
    }


    @PutMapping("/addTicket")
    public String addingTicket(@RequestParam("movieName") String movieName,@RequestParam("theaterName") String theaterName) throws MovieTheaterNotFoundException {
        return movieService.addTicketToMovie(movieName,theaterName);
    }
}
