package com.springpub.demo.mapper;

import com.springpub.demo.dto.UserDTO;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity sourceToDestination(UserDTO source);

    UserDTO destinationToSource(UserEntity destination);
}
