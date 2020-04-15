package com.springpub.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springpub.demo.security.UserRole;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * @author valuados
 */
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String fio;
    private Gender gender;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate birthDate;
    private UserRole userRole;

}
