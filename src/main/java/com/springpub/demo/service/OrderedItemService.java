package com.springpub.demo.service;

import com.springpub.demo.dto.Order;
import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.dto.OrderedItemRequest;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import com.springpub.demo.exception.ItemAlreadyExistsException;
import com.springpub.demo.exception.MenuItemNotFoundException;
import com.springpub.demo.mapper.MenuItemMapper;
import com.springpub.demo.mapper.OrderedItemMapper;
import com.springpub.demo.repository.MenuItemRepository;
import com.springpub.demo.repository.OrderedItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;
    private final OrderedItemMapper orderedItemMapper;

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @Transactional
    public OrderedItem add(final OrderedItem orderedItem, final Order order) throws MenuItemNotFoundException {

        if(!menuItemRepository.existsById(orderedItem.getMenuItem().getId())){
            throw new MenuItemNotFoundException("Menu Item with Id=" +
                    orderedItem.getMenuItem().getId() +
                    " was not found.");
        }
        orderedItem.setOrder(order);
        final OrderedItemEntity orderedItemEntity = orderedItemMapper.sourceToDestination(orderedItem);
        final Optional<MenuItemEntity> menuItemEntity = menuItemRepository.findById(orderedItem.getMenuItem().getId());
        menuItemEntity.ifPresent(orderedItemEntity::setMenuItemEntity);
        orderedItemEntity.setTotalPrice(calculateTotalPrice(orderedItemEntity));
        //TODO remove static order id



        orderedItemRepository.save(orderedItemEntity);
        return orderedItemMapper.destinationToSource(orderedItemEntity);
    }

    public List<OrderedItem> addList(final List<OrderedItem> orderedItems, final Order order) {
        orderedItems.replaceAll(orderedItem -> {
            try {
                return add(orderedItem, order);
            } catch (final MenuItemNotFoundException e) {
                e.printStackTrace();
            }
            return orderedItem;
        });
        return orderedItems;
    }

    /*@Transactional
    public OrderedItemEntity add(final OrderedItemEntity orderedItemEntity) throws MenuItemNotFoundException {


        final Optional<MenuItemEntity> menuItemEntity = menuItemRepository.findById(orderedItem.getMenuItem().getId());
        menuItemEntity.ifPresent(orderedItemEntity::setMenuItemEntity);
        orderedItemEntity.setTotalPrice(calculateTotalPrice(orderedItemEntity));
        //TODO remove static order id



        orderedItemRepository.save(orderedItemEntity);
        return orderedItemMapper.destinationToSource(orderedItemEntity);
    }*/

    private BigDecimal calculateTotalPrice(final OrderedItemEntity orderedItemEntity){
        return orderedItemEntity.getMenuItemPortionPrice().multiply(BigDecimal.valueOf(
                orderedItemEntity.getVolume() /
                        orderedItemEntity.getMenuItemPortion()));
    }
}
