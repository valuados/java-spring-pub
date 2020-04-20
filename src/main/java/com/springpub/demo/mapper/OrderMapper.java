package com.springpub.demo.mapper;

import com.springpub.demo.dto.OrderDTO;
import com.springpub.demo.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.springpub.demo.dto.OrderStatus;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring", imports = OrderStatus.class)

public interface OrderMapper {

    @Mappings({
            @Mapping(source = "userDTO", target = "userEntity"),
            @Mapping(source = "orderedItemRequests", target = "orderedItemEntities"),
            @Mapping(
                    target = "status",
                    expression = "java(source.getStatus().name())"
            )
    })
    OrderEntity sourceToDestination(OrderDTO source);

    @Mappings({
            @Mapping(source = "userEntity", target = "userDTO"),
            @Mapping(source = "orderedItemEntities", target = "orderedItemRequests"),
            @Mapping(
                    target = "status",
                    expression = "java(OrderStatus.valueOf(destination.getStatus()))"
            )
    })
    OrderDTO destinationToSource(OrderEntity destination);
}
