package com.springpub.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author valuados
 */
@Data
@Builder
public class MenuItem {
    private Long id;
    private String title;
    private Integer portion;
    private Integer bottleVolume;
    private Double portionPrice;
    private Double bottlePrice;
    private Double strength;
    private String description;
}
