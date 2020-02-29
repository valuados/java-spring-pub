package com.springpub.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author valuados
 */
@Data
public class Order {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<OrderedItemRequest> orderedItemRequests;
}
