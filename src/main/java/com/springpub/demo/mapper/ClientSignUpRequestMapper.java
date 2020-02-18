package com.springpub.demo.mapper;

import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface ClientSignUpRequestMapper {

    UserEntity sourceToDestination(ClientSignUpRequest source);

    ClientSignUpRequest destinationToSource(UserEntity destination);
}
