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


}
