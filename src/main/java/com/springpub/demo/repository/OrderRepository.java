package com.springpub.demo.repository;

import com.springpub.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author valuados
 */

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}