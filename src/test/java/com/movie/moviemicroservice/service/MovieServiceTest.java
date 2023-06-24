package com.movie.moviemicroservice.service;

import com.movie.moviemicroservice.dao.MovieRequest;
import com.movie.moviemicroservice.dao.MovieResponse;
import com.movie.moviemicroservice.exception.MovieAndTheaterAlreadyTakenException;
import com.movie.moviemicroservice.feign.AuthFeign;
import com.movie.moviemicroservice.model.Movie;
import com.movie.moviemicroservice.repository.BookingRepository;
import com.movie.moviemicroservice.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {


    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    KafkaTemplate<Long,String> kafkaTemplate;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    AuthFeign userRepository;

    @BeforeEach
    void setUp() {
        movieService=new MovieService(kafkaTemplate,movieRepository,bookingRepository,userRepository);
    }

    @Test
    void addMovie() throws MovieAndTheaterAlreadyTakenException {

        MovieRequest movieRequest=new MovieRequest(1L,"PS1","PVR",250D,100,100);
        Movie movie=new Movie(1L,"PS1","PVR",250D,100,100);
        MovieResponse movieResponse=new MovieResponse(1L,"PS1","PVR",250D,100,100);
         movieService.addMovie(movieRequest);
        ArgumentCaptor<Movie> movieArgumentCaptor=ArgumentCaptor.forClass(Movie.class);

        Mockito.verify(movieRepository).save(movieArgumentCaptor.capture());

//        Assertions.assertThat(movieArgumentCaptor.getValue()).isSameAs(movie);



    }

    @Test
    void getAllList() {

        movieRepository.findAll();
        Mockito.verify(movieRepository).findAll();
    }
}