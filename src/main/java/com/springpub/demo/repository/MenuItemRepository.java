package com.springpub.demo.repository;

import com.springpub.demo.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author valuados
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

    @Query(value = "SELECT count(*) FROM menu_item  WHERE menu_item.title = :title",
            nativeQuery = true)
    Long countAllByTitle(@Param("title") String title);
}
