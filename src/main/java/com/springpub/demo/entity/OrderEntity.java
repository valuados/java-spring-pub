package com.springpub.demo.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @author valuados
 */

@Data
@Entity
@Table(name="order")
public class OrderEntity extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    private String status;

    /*@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "orderEntity",
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderedItemEntity> orderedItemEntities = new ArrayList<>();*/
}
