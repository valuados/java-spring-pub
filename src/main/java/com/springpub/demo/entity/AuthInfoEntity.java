package com.springpub.demo.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author valuados
 */
@Data
@Entity(name = "auth_info")
public class AuthInfoEntity extends BaseEntity {


    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
