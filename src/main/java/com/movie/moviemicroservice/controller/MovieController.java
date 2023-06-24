package com.movie.moviemicroservice.controller;

import com.movie.moviemicroservice.dao.BookingRequest;
import com.movie.moviemicroservice.dao.BookingResponse;
import com.movie.moviemicroservice.model.BookingDetails;
import com.movie.moviemicroservice.model.Movie;
import com.movie.moviemicroservice.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin("*")
public class MovieController {

    private MovieService movieService;


//    @RequestHeader(value = "Authorization")String token

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> getList()
    {
        List<Movie> movies=movieService.getAllList()
                .stream().toList();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @GetMapping("/listByName")
    public ResponseEntity<List<Movie>> getListByNames(@RequestParam String name)
    {
        List<Movie> movies=movieService.getByMovieName(name)
                .stream().toList();

        return new ResponseEntity<>(movies,HttpStatus.OK);
    }


    @PostMapping("/booking")
    @SneakyThrows
    public ResponseEntity<BookingResponse> bookingApi(@RequestBody BookingRequest bookingRequest)
    {
        BookingResponse bookingResponse=movieService.bookingTickets(bookingRequest);
        return new ResponseEntity<>(bookingResponse,HttpStatus.CREATED);

    }



//    @GetMapping("/getCount")
//    public Integer getCountByName(@RequestParam("movieName") String movieName,@RequestParam("theaterName") String theaterName)
//    {
//        return movieService.getCount(movieName,theaterName);
//    }


}
