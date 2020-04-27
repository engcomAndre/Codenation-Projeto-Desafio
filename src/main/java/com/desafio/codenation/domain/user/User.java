package com.desafio.codenation.domain.user;

import com.desafio.codenation.domain.user.enums.TypeUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Um email deve ser informado")
    private String email;

    @NotEmpty(message = "Uma senha deve ser informada")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, Set<TypeUser> perfis) {
        this.email = email;
        this.password = password;
        perfis.addAll(perfis);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void addPerfil(TypeUser perfil) {
        this.perfis.add(perfil.getCod());
    }

    public Set<TypeUser> getPerfis() {
        return perfis.stream().map(TypeUser::toEnum).collect(Collectors.toSet());
    }

    public void setPerfis(Set<TypeUser> perfis) {
        this.perfis = perfis.stream().map(TypeUser::getCod).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
