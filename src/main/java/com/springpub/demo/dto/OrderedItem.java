package com.springpub.demo.dto;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author valuados
 */
@Data
public class OrderedItem {
    private Long id;
    private OrderDTO orderDTO;
    private MenuItemDTO menuItemDTO;
    private Integer volume;
    private BigDecimal totalPrice;
}
