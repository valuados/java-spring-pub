package com.springpub.demo.service;

import com.springpub.demo.dto.User;
import com.springpub.demo.dto.UserSignUpRequest;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.exception.UserAlreadyExistException;
import com.springpub.demo.exception.UserNotFoundException;
import com.springpub.demo.mapper.UserMapper;
import com.springpub.demo.mapper.UserSignUpRequestMapper;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author valuados
 */

@Service
@AllArgsConstructor
public class UserService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private UserSignUpRequestMapper userSignUpRequestMapper;
    private UserMapper userMapper;

    @Transactional
    public void signUp(final UserSignUpRequest request) throws UserAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    public void saveUser(final UserSignUpRequest request){
        final UserEntity userEntity = userSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.CLIENT);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final UserSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

    public User findByEmail(final String email) throws UserNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(email);
        if(userRepository.findByEmail(email) != null){
            throw new UserNotFoundException("User with the email " +email + " was not found");
        }
        return userMapper.destinationToSource(userEntity);

    }


}
