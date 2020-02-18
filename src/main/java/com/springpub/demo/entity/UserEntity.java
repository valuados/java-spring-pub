package com.springpub.demo.entity;

import com.springpub.demo.dto.Gender;
import com.springpub.demo.security.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author valuados
 */
@Data
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String fio;
    private Gender gender;
    private LocalDate birthDate;

    private UserRole userRole;
}
