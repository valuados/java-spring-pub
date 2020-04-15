package com.springpub.demo.service;


import com.springpub.demo.dto.*;
import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.mapper.OrderMapper;
import com.springpub.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author valuados
 */
@Log
@Service
@RequiredArgsConstructor
public class OrdersService {

    private final Clock clock;

    final OrderedItemService orderedItemService;
    final UserService userService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    //TODO User doesn't exists exception?
    public Order create(final String email){

        //Get user id
        /*Client client = (Client) authentication.getPrincipal();
        int userId = client.//getUserId();*/
        //order.setOrderedItemRequests(orderedItemService.addList(order.getOrderedItemRequests()));

        //order.setUserId(clientService.getClientByEmail(authentication.getName()).getId());
       final Order order = new Order();

        order.setTotalPrice(BigDecimal.ZERO);
        order.setStatus(OrderStatus.NEW);
        order.setCreationDate(LocalDateTime.now(clock));

        final OrderEntity orderEntity = orderMapper.sourceToDestination(order);

        orderEntity.setUserEntity(userService.getUserByEmail(email, orderEntity));

        final OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        return orderMapper.destinationToSource(savedOrderEntity);
    }

    private BigDecimal calculateTotalForOrderedItems(final Order order){
        return order.getOrderedItemRequests()
                .stream()
                .map(OrderedItemRequest::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
