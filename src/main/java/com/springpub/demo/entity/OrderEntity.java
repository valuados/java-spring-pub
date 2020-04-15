package com.springpub.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author valuados
 */

@Data
@Entity
@Table(name="orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "orderEntity"
    )
    private List<OrderedItemEntity> orderedItemEntities = new ArrayList<>();
}
