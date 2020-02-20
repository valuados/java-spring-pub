package com.springpub.demo.repository;

import com.springpub.demo.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author valuados
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

}
