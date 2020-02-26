package com.springpub.demo.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @author valuados
 */
@Data
@Entity
@Table(name = "menu_item")
public class MenuItemEntity extends BaseEntity {

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "portion", nullable = false)
    private Integer portion;

    @Column(name = "bottle_volume")
    private Integer bottleVolume;

    @Column(name = "portion_price", nullable = false)
    private Double portionPrice;

    @Column(name = "bottle_price")
    private Double bottlePrice;

    @Column(name = "strength")
    private Double strength;

    @OneToOne(mappedBy = "menuItemEntity",  fetch = FetchType.EAGER)
    private OrderedItemEntity orderedItemEntity;
}
