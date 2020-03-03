package com.springpub.demo.controller;

import com.springpub.demo.dto.UserSignUpRequest;
import com.springpub.demo.dto.UserSignInRequest;
import com.springpub.demo.dto.UserSignInResponse;
import com.springpub.demo.exception.UserAlreadyExistException;
import com.springpub.demo.security.JwtUtil;
import com.springpub.demo.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author valuados
 */
@Api()
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserService signUpService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponse singUp(@RequestBody final UserSignUpRequest request)
            throws UserAlreadyExistException {
        signUpService.signUp(request);
        return signIn(new UserSignInRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponse signIn(@RequestBody final UserSignInRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return new UserSignInResponse(
                jwtUtil.generateToken(
                        new User(request.getEmail(),
                                request.getPassword(),
                                List.of(new SimpleGrantedAuthority("CLIENT"),
                                        new SimpleGrantedAuthority("MANAGER")))
                )
        );
    }
}
