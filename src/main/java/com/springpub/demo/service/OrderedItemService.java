package com.springpub.demo.service;

import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.dto.OrderedItemRequest;
import com.springpub.demo.entity.MenuItemEntity;
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
    public OrderedItemRequest add(final OrderedItemRequest orderedItemRequest) throws MenuItemNotFoundException {

        if(!menuItemRepository.existsById(orderedItemRequest.getMenuItemId())){
            throw new MenuItemNotFoundException("Menu Item with Id=" + orderedItemRequest.getMenuItemId() + " was not found.");
        }
        final OrderedItemEntity orderedItemEntity = new OrderedItemEntity();
        orderedItemEntity.setVolume(orderedItemRequest.getVolume());
        final Optional<MenuItemEntity> menuItemEntity = menuItemRepository.findById(orderedItemRequest.getMenuItemId());
        menuItemEntity.ifPresent(orderedItemEntity::setMenuItemEntity);
        orderedItemEntity.setTotalPrice(calculateTotalPrice(orderedItemEntity));



        orderedItemRepository.save(orderedItemEntity);
        return orderedItemRequest;
    }

    public List<OrderedItemRequest> addList(final List<OrderedItemRequest> orderedItemRequests) {
        orderedItemRequests.replaceAll(orderedItem -> {
            try {
                return add(orderedItem);
            } catch (final MenuItemNotFoundException e) {
                e.printStackTrace();
            }
            return orderedItem;
        });
        return orderedItemRequests;
    }

    private BigDecimal calculateTotalPrice(final OrderedItemEntity orderedItemEntity){
        return orderedItemEntity.getMenuItemEntity().getPortionPrice().multiply(BigDecimal.valueOf(
                orderedItemEntity.getVolume() /
                        orderedItemEntity.getMenuItemEntity().getPortion()));
    }
}
