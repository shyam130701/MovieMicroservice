package com.movie.moviemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.moviemicroservice.model.Movie;
import com.movie.moviemicroservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerTest {

    @MockBean
    MovieService movieService;

    @InjectMocks
    MovieController movieController;

//    @MockBean
//    JwtService jwtService;
//

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getList() throws Exception {

        List<Movie> list= List.of(new Movie(1L,"PS1","PVR",250D,100,100),
                new Movie(2L,"PS2","INOX",250D,100,100));
        Mockito.when(movieService.getAllList()).thenReturn(list);
        mockMvc.perform(get("/api/user/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieName").value("PS1"));


    }

    @Test
    void bookingApi() {
    }
}