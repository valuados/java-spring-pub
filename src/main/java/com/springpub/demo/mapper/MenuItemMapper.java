package com.springpub.demo.mapper;

import com.springpub.demo.dto.MenuItemDTO;
import com.springpub.demo.entity.MenuItemEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemEntity sourceToDestination(MenuItemDTO source);

    MenuItemDTO destinationToSource(MenuItemEntity destination);
}
