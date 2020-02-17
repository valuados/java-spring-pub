package com.springpub.demo.service;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.exception.NoSuchMenuItemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class MenuItemsService {

    public List<MenuItem> getList(){
        return List.of(MenuItem.builder()
                .id(1L)
                .title("Zubrowka")
                .portion(50)
                .bottleVolume(1000)
                .portionPrice(5.0)
                .bottlePrice(50.0)
                .strength(40.0)
                .description("Водка Зубровка")
                .build());
    }

    public void deleteMenuItem(final Long menuItemId) throws NoSuchMenuItemException {
        //TODO prepare delete functionality
        log.info(String.format("Deleting menuItem with id (%d)", menuItemId));
        if(!menuItemId.equals((long)1)){
            throw new NoSuchMenuItemException("No menuItem with id=" + menuItemId + " was found");
        }
    }
}
