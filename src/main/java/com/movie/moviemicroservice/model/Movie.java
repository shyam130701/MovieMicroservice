package com.movie.moviemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Movie")
@Builder
public class Movie {

    private Long id;

    private String movieName;

    private String theaterName;

    private Double price;

    private Integer availableTickets;

    private Integer totalTickets;
}
