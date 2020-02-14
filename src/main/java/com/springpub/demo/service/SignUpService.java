package com.springpub.demo.service;

import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.exception.UserAlreadyExistException;
import com.springpub.demo.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author valuados
 */

@Service
@AllArgsConstructor
public class SignUpService {

    private final LoadUserDetailService loadUserDetailService;

    public void signUp(ClientSignUpRequest request) throws UserAlreadyExistException {
        try{
            if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
                throw new UserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
            }
        } catch (final UsernameNotFoundException e){
            loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
        }
    }
}
