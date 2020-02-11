package com.springpub.demo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author valuados
 */
@Data
@Builder
public class MenuItemDeleteRequest {
    private Long id;
}
