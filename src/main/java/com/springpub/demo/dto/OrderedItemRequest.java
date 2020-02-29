package com.springpub.demo.dto;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author valuados
 */
@Data
public class OrderedItemRequest {
    private Long menuItemId;
    private Integer volume;
    private BigDecimal totalPrice;
}
