package com.springpub.demo.mapper;

import com.springpub.demo.dto.Order;
import com.springpub.demo.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author valuados
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity sourceToDestination(Order source)   ;

    Order destinationToSource(OrderEntity destination);
}
