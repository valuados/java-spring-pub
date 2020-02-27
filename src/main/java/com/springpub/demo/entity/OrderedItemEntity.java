package com.springpub.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author valuados
 */

@Data
@Entity
@Table(name = "ordered_item")
public class OrderedItemEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItemEntity menuItemEntity;

    /*@Column(name = "order_id")
    private Long orderId;*/

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

}
