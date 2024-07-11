package com.banksolution.ebank.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String mail;
    private String password;
}
