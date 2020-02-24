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

    @PostConstruct
    public void init() {
        menuItemRepository.save(menuItemMapper.sourceToDestination(MenuItem.builder()
                .id(2L)
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
        if(menuItemRepository.findById(menuItemId).isEmpty()){
            throw new NoSuchMenuItemException("No menuItem with id=" + menuItemId + " was found");
        }
        menuItemRepository.deleteById(menuItemId);
    }

    public Map<String, Long> addMenuItem(final MenuItem request) throws ItemAlreadyExsists{
        if(menuItemRepository.exsistsByTitle(request.getTitle())){
            throw new ItemAlreadyExsists("Item with the title=" + request.getTitle() + " already exsists");
        }
        final MenuItemEntity menuItemEntity = menuItemMapper.sourceToDestination(request);
        final MenuItemEntity savedMenuItemEntity = menuItemRepository.save(menuItemEntity);

        Long id = savedMenuItemEntity.getId();
        Map<String, Long> menuItemMap = new HashMap<String, Long>();
        menuItemMap.put("id", id);
        return menuItemMap;

    }
}
