package com.movie.moviemicroservice.service;

import com.movie.moviemicroservice.config.JwtService;
import com.movie.moviemicroservice.dao.AuthRequest;
import com.movie.moviemicroservice.dao.AuthResponse;
import com.movie.moviemicroservice.dao.Credentials;
import com.movie.moviemicroservice.exception.UserNameNotFoundException;
import com.movie.moviemicroservice.model.ForgotPassword;
import com.movie.moviemicroservice.model.Roles;
import com.movie.moviemicroservice.model.UserData;
import com.movie.moviemicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {


    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;


    private JwtService jwtService;


    private AuthenticationManager authenticationManager;



    public AuthResponse registerUser(AuthRequest authRequest)
    {
        var userData= UserData.builder()
                .id(authRequest.getId())
                .name(authRequest.getName())
                .email(authRequest.getEmail())
                .age(authRequest.getAge())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Roles.USER)
                .build();
        userRepository.save(userData);
        var jwtToken=jwtService.generateToken(userData);

        return AuthResponse.builder()
                .name(userData.getName())
                .email(userData.getEmail())
                .age(userData.getAge())
                .password(userData.getPassword())
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticateUser(Credentials credentials)
    {
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                        credentials.getUserName(),
                credentials.getPassword()
        ));

        var userData=userRepository.findByEmail(credentials.getUserName())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(userData);

        return AuthResponse.builder()
                .name(userData.getName())
                .email(userData.getEmail())
                .age(userData.getAge())
                .password(userData.getPassword())
                .token(jwtToken)
                .build();
    }


    public List<UserData> userDataList()
    {
         return userRepository.findAll()
                 .stream()
                 .toList();
    }
    public String deleteAllUser(Long id)
    {
        userRepository.deleteById(id);
        return "Deleted";
    }


    public Optional<UserData> updatePassword(ForgotPassword forgotPassword) throws UserNameNotFoundException {
        Optional<UserData> userData=userRepository.findByEmail(forgotPassword.getUserName());
        if(userData.isEmpty())
        {
            throw  new UserNameNotFoundException("User not Found");
        }
        userData.get().setPassword(forgotPassword.getNewPassword());

        return userData;

    }


}
