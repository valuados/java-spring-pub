package com.springpub.demo.service;

import com.springpub.demo.dto.Client;
import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.exception.UserAlreadyExistException;
import com.springpub.demo.mapper.ClientMapper;
import com.springpub.demo.mapper.ClientSignUpRequestMapper;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author valuados
 */

@Service
@AllArgsConstructor
public class ClientService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ClientSignUpRequestMapper clientSignUpRequestMapper;
    private ClientMapper clientMapper;

    @Transactional
    public void signUp(final ClientSignUpRequest request) throws UserAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    public void saveUser(final ClientSignUpRequest request){
        final UserEntity userEntity = clientSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.CLIENT);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    public Client getClientByEmail(final String email) throws UsernameNotFoundException{
        final Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return clientMapper.destinationToSource(userEntity
                .orElseThrow(()-> new UsernameNotFoundException("No user found with username " + email)));
    }

    private void saveAuthInfo(final ClientSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }


}
