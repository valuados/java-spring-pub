package com.springpub.demo.mapper;

import com.springpub.demo.dto.Client;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    UserEntity sourceToDestination(Client source);

    Client destinationToSource(UserEntity destination);
}
