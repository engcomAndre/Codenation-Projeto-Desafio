package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.repositories.OrigemRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityEntityService implements UserDetailsService {

    private final UserRepositorie userRepositorie;

    private final OrigemRepositorie origemRepositorie;


    public SecurityEntityService(UserRepositorie userRepositorie, OrigemRepositorie origemRepositorie) {
        this.userRepositorie = userRepositorie;
        this.origemRepositorie = origemRepositorie;
    }

    public static SecurityEntity authenticated() {
        try {
            return (SecurityEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorie.findByEmail(username).orElse(null);
        if (user != null) {
            return SecurityEntity.builder()
                    .id(user.getId())
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .perfis(user.getPerfis())
                    .build();

        }
        Origem origem = origemRepositorie.findByChave(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encotrado para o parâmetros informados."));

        return SecurityEntity.builder()
                .id(origem.getId())
                .username(origem.getChave())
                .password(origem.getPassword())
                .perfis(origem.getPerfis())
                .build();

    }


}