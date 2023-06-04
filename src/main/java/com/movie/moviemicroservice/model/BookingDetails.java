package com.movie.moviemicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BookingDetails")
@Builder
public class BookingDetails {

    @Id
    private String bookingId;

    private String userName;

    private String movieName;

    private String theaterName;

    private Integer ticketCount;
}
