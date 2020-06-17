package com.desafio.codenation.services;

import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityEntityService implements UserDetailsService {

    @Autowired
    private UserRepositorie userRepositorie;

    public static SecurityEntity authenticated() {
        try {
            return (SecurityEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositorie.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email ou Identificador inválido"));

        return SecurityEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .perfis(user.getPerfis())
                .build();
    }


}