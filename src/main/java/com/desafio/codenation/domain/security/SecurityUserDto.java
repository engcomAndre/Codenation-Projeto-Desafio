package com.desafio.codenation.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SecurityUserDto {
    private String email;
    private String password;
}
