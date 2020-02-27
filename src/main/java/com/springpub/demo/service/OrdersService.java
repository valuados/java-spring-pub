package com.springpub.demo.service;


import com.springpub.demo.dto.Order;
import com.springpub.demo.dto.OrderStatus;
import com.springpub.demo.dto.OrderedItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
    public Order create(final Order order){
        order.setId(1L);
        order.setUserId(1L);
        order.setTotalPrice(BigDecimal.valueOf(150.00));
        order.setStatus(OrderStatus.NEW);
        return order;

    }
}
