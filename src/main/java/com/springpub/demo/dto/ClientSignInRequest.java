package com.springpub.demo.dto;

import lombok.Data;

/**
 * @author valuados
 */
@Data
public class ClientSignInRequest {
    private String email;
    private String password;
}