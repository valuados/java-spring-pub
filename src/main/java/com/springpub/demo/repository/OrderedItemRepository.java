package com.springpub.demo.repository;

import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.entity.OrderedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author valuados
 */

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItemEntity, Long> {
}
