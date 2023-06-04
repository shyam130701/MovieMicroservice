package com.movie.moviemicroservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private String bookingId;

    private String userName;

    private String movieName;

    private String theaterName;

    private Integer ticketCount;

    private String result;
}
