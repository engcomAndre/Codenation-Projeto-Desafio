package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.validation.UserInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UserInsert
public class NovoUserDTO {
    private String email;
    private String password;
    private Set<TypeUser> perfis;
}
