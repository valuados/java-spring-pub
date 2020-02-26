package com.springpub.demo.entity;

import com.springpub.demo.dto.Gender;
import com.springpub.demo.security.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author valuados
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "user_role")
    private UserRole userRole;
}
