package com.springpub.demo.entity;

import com.springpub.demo.dto.MenuItem;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * @author valuados
 */

@Entity
@Table(name = "ordered_item")
@Data
public class OrderedItemEntity extends BaseEntity{

    /*@ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;*/

    /*@OneToOne(optional = false)
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id", nullable = false)
    private MenuItemEntity menuItemEntity;*/

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

}
