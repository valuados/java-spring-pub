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
    public OrderDTO create(final String email){

        //Get user id
        /*Client client = (Client) authentication.getPrincipal();
        int userId = client.//getUserId();*/
        //order.setOrderedItemRequests(orderedItemService.addList(order.getOrderedItemRequests()));

       final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setTotalPrice(BigDecimal.ZERO);
        orderDTO.setStatus(OrderStatus.NEW);
        /*orderDTO.setCreationDate(LocalDateTime.now(clock));*/
        orderDTO.setUserDTO(userService.getUserByEmail(email));


        final OrderEntity orderEntity = orderMapper.sourceToDestination(orderDTO);

        final OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        return orderMapper.destinationToSource(savedOrderEntity);
    }

    private BigDecimal calculateTotalForOrderedItems(final OrderDTO orderDTO){
        return orderDTO.getOrderedItemRequests()
                .stream()
                .map(OrderedItemRequest::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
