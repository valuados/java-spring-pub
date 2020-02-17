package com.springpub.demo.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Wladimir Litvinov
 */
@Data
@Entity(name = "auth_info")
public class AuthInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
