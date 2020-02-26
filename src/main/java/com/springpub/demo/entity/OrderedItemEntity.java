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

    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "menu_item")
    @JoinColumn(name = "menu_item_id")*/
    /*private MenuItemEntity menuItemEntity;*/

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

}
