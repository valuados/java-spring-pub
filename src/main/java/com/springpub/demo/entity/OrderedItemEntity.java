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

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;

    @OneToOne(optional = false)
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItemEntity;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price")
    @Generated(GenerationTime.ALWAYS)
    @Formula("SELECT oi.quantity, mi.portion_price, (oi.quantity * mi.portion_price) as 'total_price'" +
            " FROM ordered_item oi INNER JOIN menu_item mi ON oi.item_id=mi.id WHERE ROWNUM = 1")
    private Double totalPrice;

    public void setTotalPrice(){
        this.totalPrice = this.quantity * this.menuItemEntity.getPortionPrice();
    }

    public Double getTotalPrice(){
        return this.totalPrice;
    }
}
