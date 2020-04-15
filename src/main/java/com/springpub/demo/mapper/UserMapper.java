package com.springpub.demo.mapper;

import com.springpub.demo.dto.UserDTO;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    UserEntity sourceToDestination(UserDTO source);

    UserDTO destinationToSource(UserEntity destination);
}
