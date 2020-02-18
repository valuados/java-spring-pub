package com.springpub.demo.security;

import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.repository.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author valuados
 */

@Service
@RequiredArgsConstructor
public class LoadUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> inMemoryUsers = new HashMap<>();

    private final AuthInfoRepository authInfoRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException{
        final Optional<AuthInfoEntity> authInfoEntity = authInfoRepository.findByLogin(username);
        if (authInfoEntity.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        } else {
            final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
                    "ROLE_" + authInfoEntity.get().getUser().getUserRole().name());
            return new User(username, authInfoEntity.get().getPassword(), List.of(authority));
        }
    }
}
