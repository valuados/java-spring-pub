package com.springpub.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valuados
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInResponse {
    private String token;
}
