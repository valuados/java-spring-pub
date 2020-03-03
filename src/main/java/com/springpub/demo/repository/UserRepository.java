package com.springpub.demo.repository;

import com.springpub.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author valuados
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users  WHERE orders.email = :email AND ROWNUM = 1",
            nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);
}
