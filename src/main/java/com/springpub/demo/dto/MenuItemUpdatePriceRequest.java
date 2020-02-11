package com.springpub.demo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author valuados
 */
@Data
@Builder
public class MenuItemUpdatePriceRequest {
    private Long id;
    private Double portionPrice;
    private Double bottlePrice;
}
