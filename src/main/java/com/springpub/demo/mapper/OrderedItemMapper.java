package com.springpub.demo.mapper;

import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.entity.OrderedItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author valuados
 */

@Mapper(componentModel = "spring")
public interface OrderedItemMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="order", source="orderEntity"),
            @Mapping(target="menuItem", source="menuItemEntity"),
            @Mapping(target="volume", source="volume"),
            @Mapping(target="totalPrice", source="totalPrice")
    })
    OrderedItem destinationToSource(OrderedItemEntity destination);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="orderEntity", source="order"),
            @Mapping(target="menuItemEntity", source="menuItem"),
            @Mapping(target="volume", source="volume"),
            @Mapping(target="totalPrice", source="totalPrice")
    })
    OrderedItemEntity sourceToDestination(OrderedItem source);
}
