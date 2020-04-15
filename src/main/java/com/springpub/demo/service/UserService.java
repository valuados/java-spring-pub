package com.springpub.demo.service;

import com.springpub.demo.dto.UserDTO;
import com.springpub.demo.dto.UserSignUpRequest;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.exception.UserAlreadyExistException;
import com.springpub.demo.mapper.UserMapper;
import com.springpub.demo.mapper.UserSignUpRequestMapper;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

/**
 * @author valuados
 */

@Service
@AllArgsConstructor
public class UserService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserSignUpRequestMapper userSignUpRequestMapper;
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

    public UserDTO getClientByEmail(final String email) throws UsernameNotFoundException{
        final Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userMapper.destinationToSource(userEntity
                .orElseThrow(()-> new UsernameNotFoundException("No user found with username " + email)));
    }

    public UserEntity getUserByEmail(final String email, final OrderEntity orderEntity) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("No user found with username " + email));

        userEntity.setOrderEntityList(Collections.singletonList(orderEntity));

        return  userRepository.save(userEntity);
    }

    private void saveAuthInfo(final UserSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }


}
