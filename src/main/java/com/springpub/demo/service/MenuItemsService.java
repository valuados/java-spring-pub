package com.springpub.demo.service;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.exception.ItemAlreadyExistsException;
import com.springpub.demo.exception.MenuItemNotFoundException;
import com.springpub.demo.mapper.MenuItemMapper;
import com.springpub.demo.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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

    public List<MenuItem> getList(){
        return menuItemRepository
                .findAll()
                .stream()
                .map(menuItemMapper::destinationToSource)
                .sorted(Comparator.comparing(MenuItem::getTitle))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMenuItem(final Long menuItemId) throws MenuItemNotFoundException {
        //TODO prepare delete functionality
        log.info(String.format("Deleting menuItem with id (%d)", menuItemId));
        if(menuItemRepository.findById(menuItemId).isEmpty()){
            throw new MenuItemNotFoundException("No menuItem with id=" + menuItemId + " was found");
        }
        menuItemRepository.deleteById(menuItemId);
    }

    @Transactional
    public Map<String, Long> addMenuItem(final MenuItem request) throws ItemAlreadyExistsException {
        if(menuItemRepository.countAllByTitle(request.getTitle()) > 0){
            throw new ItemAlreadyExistsException("Item with the title=" + request.getTitle() + " already exsists");
        }
        final MenuItemEntity menuItemEntity = menuItemMapper.sourceToDestination(request);
        final Long id = menuItemRepository.save(menuItemEntity).getId();

        final Map<String, Long> menuItemMap = new HashMap<>();
        menuItemMap.put("id", id);
        return menuItemMap;

    }

    public MenuItem getMenuItemById(Long id) {
        final Optional<MenuItemEntity> menuItemEntity = menuItemRepository.findById(id);
        return menuItemMapper.destinationToSource(menuItemEntity.orElse(null));
    }
}
