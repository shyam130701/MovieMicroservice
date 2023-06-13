package com.movie.moviemicroservice.service;

import com.movie.moviemicroservice.dao.BookingRequest;
import com.movie.moviemicroservice.dao.BookingResponse;
import com.movie.moviemicroservice.dao.MovieRequest;
import com.movie.moviemicroservice.dao.MovieResponse;
import com.movie.moviemicroservice.exception.*;
import com.movie.moviemicroservice.model.BookingDetails;
import com.movie.moviemicroservice.model.Movie;
import com.movie.moviemicroservice.repository.BookingRepository;
import com.movie.moviemicroservice.repository.MovieRepository;
import com.movie.moviemicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService implements MovieImpl {


//    private KafkaTemplate<Long,String> kafkaTemplate;


    private MovieRepository movieRepository;

    private BookingRepository bookingRepository;

    private UserRepository userRepository;

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) throws MovieAndTheaterAlreadyTakenException {
        Optional<Movie> optionalMovie = movieRepository.findMovieByMovieNameAndTheaterName(
                movieRequest.getMovieName(),
                movieRequest.getTheaterName()
        );
        if (optionalMovie.isPresent()) {
            throw new MovieAndTheaterAlreadyTakenException("Movie {} already Added to the Theater {}" + movieRequest.getMovieName() + movieRequest.getTheaterName());
        }
        Movie movie = Movie.builder()
                .id(movieRequest.getId())
                .movieName(movieRequest.getMovieName())
                .theaterName(movieRequest.getTheaterName())
                .price(movieRequest.getPrice())
                .availableTickets(100)
                .totalTickets(100)
                .build();
        movieRepository.save(movie);

//        kafkaTemplate.send("movie-topics",movieRequest.getId(),movieRequest.getMovieName());
//        log.info("The movie {} is added to theater {}",movieRequest.getMovieName(),movieRequest.getTheaterName());

        return MovieResponse.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .theaterName(movie.getTheaterName())
                .price(movie.getPrice())
                .availableTickets(100)
                .totalTickets(100)
                .build();
    }

    @Override
    public List<Movie> getAllList() {

        return movieRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public List<Movie> getByMovieName(String name) {

        return movieRepository.findMovieByMovieName(name)
                .stream().toList();
    }

    @Override
    public BookingResponse bookingTickets(BookingRequest bookingRequest) throws BookingTicketException, UserNameNotFoundException {
        Optional<Movie> movie = movieRepository.findMovieByMovieNameAndTheaterName(bookingRequest.getMovieName(), bookingRequest.getTheaterName());
        if (movie.isEmpty()) {
            throw new BookingTicketException("Booking Failed");
        }
        if (userRepository.findByEmail(bookingRequest.getUserName()).isEmpty()) {
            throw new UserNameNotFoundException("UserName Not Found");
        }
        long count = bookingRepository.count() + 1;
        //custom Id
        LocalDateTime now = LocalDateTime.now();
        String bookingId = bookingRequest.getUserName() + count + now ;
        Movie getCounts=movieRepository.availabilityCount(bookingRequest.getMovieName(), bookingRequest.getTheaterName());
        if (bookingRequest.getTicketCount()>getCounts.getAvailableTickets()) {
            throw new BookingTicketException("Booking Failed Ticket Not available");
        }

//        kafkaTemplate.send();
        BookingDetails bookingDetails = BookingDetails.builder()
                .bookingId(bookingId)
                .movieName(bookingRequest.getMovieName())
                .theaterName(bookingRequest.getTheaterName())
                .userName(bookingRequest.getUserName())
                .ticketCount(bookingRequest.getTicketCount())
                .build();
        int reduceTickets = movie.get().getAvailableTickets() - bookingRequest.getTicketCount();
        movie.get().setAvailableTickets(reduceTickets);
        movieRepository.save(movie.get());
        bookingRepository.save(bookingDetails);

        return BookingResponse.builder()
                .bookingId(bookingDetails.getBookingId())
                .movieName(bookingDetails.getMovieName())
                .theaterName(bookingDetails.getTheaterName())
                .userName(bookingDetails.getUserName())
                .ticketCount(bookingRequest.getTicketCount())
                .result("Ticket Booked Successfully")
                .build();
    }

    @Override
    public String deleteMovie(String movieName, String theaterName) throws MovieTheaterNotFoundException, MovieInProgressException {
        Optional<Movie> movie=movieRepository.findMovieByMovieNameAndTheaterName(movieName,theaterName);
        if(movie.isEmpty())
        {
            throw new MovieTheaterNotFoundException("Movie and theater Not found");
        }

        if(movie.get().getAvailableTickets()<100)
        {
            throw new MovieInProgressException("Movie is in progress");
        }

        movieRepository.deleteMovieByMovieNameAndTheaterName(movieName,theaterName);
        return "Movie Deleted Successfully "+movieName;
    }

    @Override
    public String addTicketToMovie(String movieName, String theaterName) throws MovieTheaterNotFoundException {
        Optional<Movie> movie=movieRepository.findMovieByMovieNameAndTheaterName(movieName,theaterName);
        if(movie.isEmpty())
        {
            throw new MovieTheaterNotFoundException("Movie and theater Not found");
        }
        movie.get().setAvailableTickets(100);
        movieRepository.save(movie.get());
        return "Admin Successfully added the ticket count";
    }

    @Override
    public List<BookingDetails> getListOfBooking() {
        return bookingRepository.findAll();
    }


}
