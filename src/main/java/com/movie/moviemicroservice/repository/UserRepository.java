package com.movie.moviemicroservice.repository;

import com.movie.moviemicroservice.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends MongoRepository<UserData,Long> {

    Optional<UserData> findByEmail(String email);
}
