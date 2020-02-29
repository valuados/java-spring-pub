package com.springpub.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal portionPrice;

    @Column(name = "bottle_price")
    private BigDecimal bottlePrice;

    @Column(name = "strength")
    private BigDecimal strength;

    @OneToOne(mappedBy = "menuItemEntity",  fetch = FetchType.EAGER)
    private OrderedItemEntity orderedItemEntity;
}
