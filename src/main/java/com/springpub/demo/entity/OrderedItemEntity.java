package com.springpub.demo.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author valuados
 */

@Data
@Entity
@Table(name = "ordered_item")
public class OrderedItemEntity extends BaseEntity{

    /*@ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;*/

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItemEntity menuItemEntity;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

}
