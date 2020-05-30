package com.desafio.codenation.security;

import com.desafio.codenation.domain.user.enums.TypeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class OriginSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String identificador;
    private String chave;

    public OriginSS() {
    }

    public OriginSS(Long id, String identificador, String chave) {
        super();
        this.id = id;
        this.identificador = identificador;
        this.chave = chave;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return chave;
    }

    @Override
    public String getUsername() {
        return identificador;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(TypeUser typeUser) {
        return true;
    }


}