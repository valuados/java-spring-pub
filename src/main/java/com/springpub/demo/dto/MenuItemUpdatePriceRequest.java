package com.springpub.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author valuados
 */
@Data
@Builder
public class MenuItemUpdatePriceRequest {
    private Long id;
    private BigDecimal portionPrice;
    private BigDecimal bottlePrice;
}
