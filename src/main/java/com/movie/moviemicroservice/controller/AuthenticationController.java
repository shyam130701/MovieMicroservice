package com.movie.moviemicroservice.controller;

import com.movie.moviemicroservice.dao.AuthRequest;
import com.movie.moviemicroservice.dao.AuthResponse;
import com.movie.moviemicroservice.dao.Credentials;
import com.movie.moviemicroservice.exception.UserNameNotFoundException;
import com.movie.moviemicroservice.model.ForgotPassword;
import com.movie.moviemicroservice.model.UserData;
import com.movie.moviemicroservice.service.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest)
    {
        AuthResponse authResponse=authenticationService.registerUser(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials)
    {
        String authResponse=authenticationService.authenticateUser(credentials);
        return  new ResponseEntity<>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<UserData>> update(@RequestBody ForgotPassword forgotPassword) throws UserNameNotFoundException {
        Optional<UserData> userData=authenticationService.updatePassword(forgotPassword);
        return new ResponseEntity<>(userData,HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    @SneakyThrows
    public Optional<UserData> userData(@RequestParam("userName") String name)
    {
        return authenticationService.getUserByName(name);
    }
}
