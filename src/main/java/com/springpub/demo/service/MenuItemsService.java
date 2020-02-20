package com.springpub.demo.service;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.exception.NoSuchMenuItemException;
import com.springpub.demo.mapper.MenuItemMapper;
import com.springpub.demo.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class MenuItemsService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @PostConstruct
    public void init() {
        menuItemRepository.save(menuItemMapper.sourceToDestination(MenuItem.builder()
                .id(1L)
                .title("Zubrowka")
                .description("Водка Зубровка")
                .portion(50)
                .bottleVolume(1000)
                .portionPrice(5.00)
                .bottlePrice(50.00)
                .strength(40.0)
                .build()));
    }

    public List<MenuItem> getList(){
        return menuItemRepository.findAll().stream().map(menuItemMapper::destinationToSource).collect(Collectors.toList());
    }

    public void deleteMenuItem(final Long menuItemId) throws NoSuchMenuItemException {
        //TODO prepare delete functionality
        log.info(String.format("Deleting menuItem with id (%d)", menuItemId));
        if(!menuItemId.equals((long)1)){
            throw new NoSuchMenuItemException("No menuItem with id=" + menuItemId + " was found");
        }
    }
}
