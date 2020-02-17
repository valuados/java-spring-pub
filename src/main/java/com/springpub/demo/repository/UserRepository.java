package com.springpub.demo.repository;

import com.springpub.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author valuados
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
