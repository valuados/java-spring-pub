package com.springpub.demo.mapper;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.entity.MenuItemEntity;
import org.mapstruct.Mapper;

/**
 * @author valuados
 */

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemEntity sourceToDestination(MenuItem source);

    MenuItem destinationToSource(MenuItemEntity destination);
}