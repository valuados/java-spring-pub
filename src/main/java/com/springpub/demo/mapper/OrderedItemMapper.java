package com.springpub.demo.mapper;

import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.entity.OrderedItemEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */

@Mapper(componentModel = "spring")
public interface OrderedItemMapper {
    OrderedItemEntity sourceToDestination(OrderedItem source);

    OrderedItem destinationToSource(OrderedItemEntity destination);
}
