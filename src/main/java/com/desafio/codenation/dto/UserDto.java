package com.desafio.codenation.dto;

import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Um email deve ser informado")
    private String email;

    @NotEmpty(message = "Uma senha deve ser informada")
    private String password;

    private Set<TypeUser> perfis = new HashSet<>();

    public UserDto(String email, String password, Set<TypeUser> perfis) {
        this.email = email;
        this.password = password;
        this.perfis.addAll(perfis);
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.perfis.addAll(user.getPerfis());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
