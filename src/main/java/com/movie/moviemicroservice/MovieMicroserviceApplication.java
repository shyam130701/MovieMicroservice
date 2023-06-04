package com.movie.moviemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableKafka

public class MovieMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieMicroserviceApplication.class, args);
        System.out.println(System.currentTimeMillis());
    }

}
