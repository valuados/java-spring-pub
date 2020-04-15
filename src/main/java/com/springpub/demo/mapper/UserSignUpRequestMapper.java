package com.springpub.demo.mapper;

import com.springpub.demo.dto.UserSignUpRequest;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface UserSignUpRequestMapper {

    UserEntity sourceToDestination(UserSignUpRequest source);

    UserSignUpRequest destinationToSource(UserEntity destination);
}
