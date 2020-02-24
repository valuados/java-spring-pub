package com.springpub.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author valuados
 */
@Data
@Entity(name = "menu_item")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String description;

    private Integer portion;
    private Integer bottleVolume;

    private Double portionPrice;
    private Double bottlePrice;

    private Double strength;
}
