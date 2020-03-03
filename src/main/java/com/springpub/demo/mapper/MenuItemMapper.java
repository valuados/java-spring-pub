package com.springpub.demo.mapper;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.entity.MenuItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author valuados
 */

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="title", source="title"),
            @Mapping(target="description", source="description"),
            @Mapping(target="portion", source="portion"),
            @Mapping(target="bottleVolume", source="bottleVolume"),
            @Mapping(target="portionPrice", source="portionPrice"),
            @Mapping(target="bottlePrice", source="bottlePrice"),
            @Mapping(target="strength", source="strength")
    })
    MenuItemEntity sourceToDestination(MenuItem source);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="title", source="title"),
            @Mapping(target="description", source="description"),
            @Mapping(target="portion", source="portion"),
            @Mapping(target="bottleVolume", source="bottleVolume"),
            @Mapping(target="portionPrice", source="portionPrice"),
            @Mapping(target="bottlePrice", source="bottlePrice"),
            @Mapping(target="strength", source="strength")
    })
    MenuItem destinationToSource(MenuItemEntity destination);
}
