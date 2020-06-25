package com.desafio.codenation.domain.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListDTO {
    private Long id;
    private String email;
}
