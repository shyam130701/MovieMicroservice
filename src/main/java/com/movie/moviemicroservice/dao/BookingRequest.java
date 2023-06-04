package com.movie.moviemicroservice.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    private String userName;

    private String movieName;

    private String theaterName;

    private Integer ticketCount;
}
