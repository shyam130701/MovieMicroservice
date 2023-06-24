package com.movie.moviemicroservice.feign;

import com.movie.moviemicroservice.dao.AuthRequest;
import com.movie.moviemicroservice.dao.Credentials;
import com.movie.moviemicroservice.model.UserData;
import lombok.SneakyThrows;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@FeignClient(name = "AuthorizationService", url = "http://localhost:9099/api/v1/auth")
public interface AuthFeign {


    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Credentials credentials);

    @GetMapping("/getUser")
    Optional<UserData> userData(@RequestParam("userName") String name);



}
