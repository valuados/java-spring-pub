package com.springpub.demo.repository;

import com.springpub.demo.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author valuados
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

    @Query("SELECT case when count(e)> 0 then true else false end from menu_item e where lower(e.title) like lower(:title)")
    boolean exsistsByTitle(@Param("title") String title);
}
