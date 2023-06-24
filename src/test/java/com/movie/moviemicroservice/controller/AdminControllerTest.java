//package com.movie.moviemicroservice.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.movie.moviemicroservice.config.JwtService;
//import com.movie.moviemicroservice.consumer.MovieConsumer;
//import com.movie.moviemicroservice.dao.MovieRequest;
//import com.movie.moviemicroservice.dao.MovieResponse;
//import com.movie.moviemicroservice.exception.MovieAndTheaterAlreadyTakenException;
//import com.movie.moviemicroservice.model.Movie;
//import com.movie.moviemicroservice.repository.MovieRepository;
//import com.movie.moviemicroservice.service.MovieService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@ExtendWith(MockitoExtension.class)
//@WebMvcTest(AdminController.class)
//@AutoConfigureMockMvc(addFilters = false)
//class AdminControllerTest {
//
//
//
//    @InjectMocks
//    private AdminController adminController;
//
//    @MockBean
//    private MovieService movieService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @MockBean
//    MovieConsumer movieConsumer;
//
//    @MockBean
//    KafkaTemplate<Long,String> kafkaTemplate;
//
//    @MockBean
//    JwtService jwtService;
//
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @BeforeEach
//    void setUp()
//    {
//        adminController=new AdminController(movieService);
//    }
//    @Test
//    void createMovie() throws Exception {
//        MovieRequest movieRequest=new MovieRequest(1L,"PS2","INOX",200D,100,100);
//
//        Movie movie = Movie.builder()
//                .id(movieRequest.getId())
//                .movieName(movieRequest.getMovieName())
//                .theaterName(movieRequest.getTheaterName())
//                .price(movieRequest.getPrice())
//                .availableTickets(100)
//                .totalTickets(100)
//                .build();
//
//        MovieResponse movieResponse=MovieResponse.builder()
//                .id(movie.getId())
//                .movieName(movie.getMovieName())
//                .theaterName(movie.getTheaterName())
//                .price(movie.getPrice())
//                .availableTickets(100)
//                .totalTickets(100)
//                .build();
//
//        Mockito.when(movieService.addMovie(movieRequest)).thenReturn(movieResponse);
//
//
//        mockMvc.perform(post("/api/admin/addMovie")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(movie))
//
//        ).andExpect(status().isCreated());
//
//
//
//    }
//}