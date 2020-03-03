package com.springpub.demo.mapper;

import com.springpub.demo.dto.Order;
import com.springpub.demo.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="totalPrice", source="totalPrice"),
            @Mapping(target="status", source="status"),
            @Mapping(target="user", source="userEntity"),
            @Mapping(target="orderedItems", source="orderedItemEntities")
    })
    Order destinationToSource(OrderEntity destination);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="totalPrice", source="totalPrice"),
            @Mapping(target="status", source="status"),
            @Mapping(target="userEntity", source="user"),
            @Mapping(target="orderedItemEntities", source="orderedItems")
    })
    OrderEntity sourceToDestination(Order source);
}
