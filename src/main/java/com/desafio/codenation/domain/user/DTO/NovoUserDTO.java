package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;

import java.util.HashSet;
import java.util.Set;

public class NovoUserDTO {
    private Long id;
    private String email;
    private String password;
    private Set<TypeUser> perfis = new HashSet<>();
}
