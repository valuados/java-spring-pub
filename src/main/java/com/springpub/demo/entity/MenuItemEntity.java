package com.springpub.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author valuados
 */
@Data
@Entity(name = "menu_item")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private Integer portion;
    private Integer bottleVolume;

    private Double portionPrice;
    private Double bottlePrice;

    private Double strength;
}
