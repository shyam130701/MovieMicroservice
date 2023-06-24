package com.movie.moviemicroservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MovieConsumer {




    @KafkaListener(topics = "register-topic",groupId = "movie-groupId")
    public void processMessage1(ConsumerRecord<Long,String> record){
        System.out.println("Registration : " + record.value());
    }
}
