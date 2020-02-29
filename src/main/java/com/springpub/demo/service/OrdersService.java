package com.springpub.demo.service;


import com.springpub.demo.dto.Order;
import com.springpub.demo.dto.OrderStatus;
import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.dto.OrderedItemRequest;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import com.springpub.demo.mapper.OrderMapper;
import com.springpub.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class OrdersService {

    final OrderedItemService orderedItemService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    //TODO User doesn't exists exception?
    public Order create(final Order order, final String email){

        order.setOrderedItemRequests(orderedItemService.addList(order.getOrderedItemRequests()));
        order.setId(1L);
        order.setUserId(1L);
        order.setTotalPrice(calculateTotalForOrderedItems(order));
        order.setStatus(OrderStatus.NEW);
        return order;

    }

    private BigDecimal calculateTotalForOrderedItems(final Order order){
        return order.getOrderedItemRequests()
                .stream()
                .map(OrderedItemRequest::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
