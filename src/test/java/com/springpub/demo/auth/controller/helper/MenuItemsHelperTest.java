package com.springpub.demo.auth.controller.helper;

import com.springpub.demo.entity.MenuItemEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsHelperTest{

    public static List<MenuItemEntity> findAllMenuItems(){
        final MenuItemEntity menuItem1 = getMenuItemEntity(
                1L,
                "Heineken",
                "То самое немецкое с пенкой",
                500,
                500,
                6.50,
                6.50,
                3.8);

        final MenuItemEntity menuItem2 = getMenuItemEntity(
                2L,
                "Zubrowka",
                "Водка Зубровка",
                50, 1000,
                5.00,
                50.00,
                40.0);

        final List<MenuItemEntity> menuItems = new ArrayList<>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        return menuItems;
    }

    public static MenuItemEntity getMenuItemEntity(
            final Long id,
            final String title,
            final String description,
            final Integer portion,
            final Integer bottleVolume,
            final Double portionPrice,
            final Double bottlePrice,
            final Double strength
    ) {

        final MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setId(id);
        menuItem.setTitle(title);
        menuItem.setDescription(description);
        menuItem.setPortion(portion);
        menuItem.setBottleVolume(bottleVolume);
        menuItem.setPortionPrice(BigDecimal.valueOf(portionPrice));
        menuItem.setBottlePrice(BigDecimal.valueOf(bottlePrice));
        menuItem.setStrength(BigDecimal.valueOf(strength));
        menuItem.setOrderedItemEntity(null);
        return menuItem;
    }
}
