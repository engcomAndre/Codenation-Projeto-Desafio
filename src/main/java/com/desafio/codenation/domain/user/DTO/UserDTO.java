package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.user.enums.TypeUser;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String email;
    private Set<TypeUser> perfis = new HashSet<>();
}



