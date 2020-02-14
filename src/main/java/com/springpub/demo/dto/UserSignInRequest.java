package com.springpub.demo.dto;

import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
public class UserSignInRequest {

	private final String email;
    private final String password;
}
