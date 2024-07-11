package com.banksolution.ebank.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {
    private String mail;
    private String password;
}