package com.springpub.demo.service;


import com.springpub.demo.dto.*;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import com.springpub.demo.exception.UserNotFoundException;
import com.springpub.demo.mapper.OrderMapper;
import com.springpub.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class OrdersService {

    final OrderedItemService orderedItemService;
    final MenuItemsService menuItemsService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final UserService userService;

    //TODO User doesn't exists exception?
    public Order create(final Order order, final String email) throws UserNotFoundException {

        order.setUser(userService.findByEmail(email));
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(BigDecimal.ZERO);
        order.getOrderedItems().forEach(orderedItem -> {
            orderedItem.setMenuItem(menuItemsService.getMenuItemById(orderedItem.getMenuItem().getId()));
        });

        order.setOrderedItems(orderedItemService.addList(order.getOrderedItems(), order));
        final OrderEntity orderEntity = orderMapper.sourceToDestination(order);

        orderEntity.getOrderedItemEntities().forEach(orderedItemEntity -> {
            orderedItemEntity.setOrderEntity(orderEntity);
/*
            orderedItemEntity.setMenuItemEntity(orderedItemService.add(orderedItemEntity));
*/
        });

        final OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        final Order savedOrder = orderMapper.destinationToSource(savedOrderEntity);
/*
        savedOrder.setOrderedItems(orderedItemService.addList(orderedItems, savedOrder));
*/
/*
        order.setTotalPrice(calculateTotalForOrderedItems(order));
*/
        return savedOrder;

    }

    public Optional<OrderEntity> getOrderEntityById(Long id){
        return orderRepository.findById(id);
    }

    private BigDecimal calculateTotalForOrderedItems(final Order order){
        return order.getOrderedItems()
                .stream()
                .map(OrderedItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
