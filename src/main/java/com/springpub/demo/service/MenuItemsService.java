package com.springpub.demo.service;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.exception.ItemAlreadyExsists;
import com.springpub.demo.exception.NoSuchMenuItemException;
import com.springpub.demo.mapper.MenuItemMapper;
import com.springpub.demo.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<MenuItem> getList() {
        return menuItemRepository
                .findAll()
                .stream()
                .map(menuItemMapper::destinationToSource)
                .sorted(Comparator.comparing(MenuItem::getTitle))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMenuItem(final Long menuItemId) throws NoSuchMenuItemException {
        //TODO prepare delete functionality
        log.info(String.format("Deleting menuItem with id (%d)", menuItemId));
        if (menuItemRepository.findById(menuItemId).isEmpty()) {
            throw new NoSuchMenuItemException("No menuItem with id=" + menuItemId + " was found");
        }
        menuItemRepository.deleteById(menuItemId);
    }

    @Transactional
    public Map<String, Long> addMenuItem(final MenuItem request) throws ItemAlreadyExsists {
        if (menuItemRepository.countAllByTitle(request.getTitle()) > 0) {
            throw new ItemAlreadyExsists("Item with the title=" + request.getTitle() + " already exsists");
        }
        final MenuItemEntity menuItemEntity = menuItemMapper.sourceToDestination(request);
        final Long id = menuItemRepository.save(menuItemEntity).getId();

        final Map<String, Long> menuItemMap = new HashMap<String, Long>();
        menuItemMap.put("id", id);
        return menuItemMap;

    }

    @Transactional
    public Long changeMenuItem(final Long id, final MenuItem request) throws NoSuchMenuItemException {
        final String newTitle = request.getTitle();
        final MenuItemEntity menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchMenuItemException("Item with the id=" + id + " was not found"));
        menuItem.setTitle(newTitle);
        final MenuItemEntity changedMenuItem = menuItemRepository.save(menuItem);
        return changedMenuItem.getId();
    }
}
