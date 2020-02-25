package com.springpub.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author valuados
 */

@Entity
@Table(name="order")
@Data
public class OrderEntity extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    /*@OneToMany*/

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "orderEntity",
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderedItemEntity> orderedItemEntities = new ArrayList<>();
}
