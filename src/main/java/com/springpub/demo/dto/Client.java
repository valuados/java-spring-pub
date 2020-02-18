package com.springpub.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

/**
 * @author valuados
 */
@Data
public class Client {
    private Long id;
    private String mail;
    private String password;
    private String fio;
    private String phoneNumber;
    private Gender gender;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate birthDate;
    private Integer discount;
}
