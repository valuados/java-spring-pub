package com.springpub.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ClientSignUpRequest {

        private String email;
        private String password;
        private String fio;
        private String phoneNumber;
        private Gender gender;
        @JsonFormat(pattern="dd.MM.yyyy")
        private LocalDate birthDate;
}
