package com.movie.moviemicroservice.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequest {

    private Long id;

    private String movieName;

    private String theaterName;

    private Double price;

    private Integer availableTickets;

    private Integer totalTickets;

}
