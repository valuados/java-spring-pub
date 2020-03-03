package com.springpub.demo.mapper;

import com.springpub.demo.dto.User;
import com.springpub.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="mail", source="email"),
            @Mapping(target="fio", source="fio"),
            @Mapping(target="gender", source="gender"),
            @Mapping(target="birthDate", source="birthDate"),
            @Mapping(target="userRole", source="userRole")
    })
    User destinationToSource(UserEntity destination);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="email", source="mail"),
            @Mapping(target="fio", source="fio"),
            @Mapping(target="gender", source="gender"),
            @Mapping(target="birthDate", source="birthDate"),
            @Mapping(target="userRole", source="userRole")
    })
    UserEntity sourceToDestination(User source);
}
