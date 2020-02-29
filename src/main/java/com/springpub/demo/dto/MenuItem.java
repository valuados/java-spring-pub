package com.springpub.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author valuados
 */
@Data
@Builder
public class MenuItem {
    private Long id;
    private String title;
    private String description;

    private Integer portion;
    private Integer bottleVolume;

    private BigDecimal portionPrice;
    private BigDecimal bottlePrice;

    private BigDecimal strength;
}
