package com.springpub.demo.service;


import com.springpub.demo.dto.*;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import com.springpub.demo.mapper.OrderMapper;
import com.springpub.demo.repository.OrderRepository;
import com.springpub.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class OrdersService {

    final OrderedItemService orderedItemService;
    final ClientService clientService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    //TODO User doesn't exists exception?
    public Order create(final Order order, final Authentication authentication){

        //Get user id
        /*Client client = (Client) authentication.getPrincipal();
        int userId = client.getUserId();*/
        //order.setOrderedItemRequests(orderedItemService.addList(order.getOrderedItemRequests()));

        order.setId(1L);
        order.setUserId(clientService.getClientByEmail(authentication.getName()).getId());
        order.setTotalPrice(BigDecimal.ZERO);
        order.setStatus(OrderStatus.NEW);
        order.setCreationDate(LocalDateTime.now());
        return order;
    }

    private BigDecimal calculateTotalForOrderedItems(final Order order){
        return order.getOrderedItemRequests()
                .stream()
                .map(OrderedItemRequest::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
