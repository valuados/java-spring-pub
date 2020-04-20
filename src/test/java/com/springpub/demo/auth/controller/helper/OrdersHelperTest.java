package com.springpub.demo.auth.controller.helper;

import com.springpub.demo.entity.OrderEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import com.springpub.demo.entity.UserEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrdersHelperTest{

    public static OrderEntity getOrderEntity(final UserEntity userentity,
                                         final String status,
                                         final BigDecimal totalPrice,
                                         final List<OrderedItemEntity> orderedItemEntityList){
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserEntity(userentity);
        orderEntity.setStatus(status);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setOrderedItemEntities(orderedItemEntityList);
        return orderEntity;
    }
}
