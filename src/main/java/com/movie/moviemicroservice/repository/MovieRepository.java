package com.movie.moviemicroservice.repository;

import com.movie.moviemicroservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Long> {


    List<Movie> findMovieByMovieName(String name);


    Optional<Movie> findMovieByMovieNameAndTheaterName(String movieName,String theaterName);


    Movie deleteMovieByMovieNameAndTheaterName(String movieName,String theaterName);

    @Query(value = "{$and:[{'movieName': ?0},{'theaterName': ?1}]}")
    Movie availabilityCount(String movieName,String theaterName);
}
