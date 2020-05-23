package com.desafio.codenation.dto;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;

import java.util.HashSet;
import java.util.Set;

public class UserDtoExpose {

    private Long id;

    private String email;

    private Set<TypeUser> perfis = new HashSet<>();

    public UserDtoExpose(String email, String password, Set<TypeUser> perfis) {
        this.email = email;
        this.perfis.addAll(perfis);
    }

    public UserDtoExpose(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.perfis = user.getPerfis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TypeUser> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<TypeUser> perfis) {
        this.perfis = perfis;
    }


}
