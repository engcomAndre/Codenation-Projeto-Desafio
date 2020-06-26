package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
    private String email;
    private String password;
    private Set<TypeUser> grants;
}
