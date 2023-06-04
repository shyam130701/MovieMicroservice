package com.movie.moviemicroservice.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MovieTopic {


    @Bean
    public NewTopic topic()
    {
        return TopicBuilder.name("movie-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
